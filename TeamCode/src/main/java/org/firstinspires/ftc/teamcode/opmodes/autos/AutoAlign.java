package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Rotator;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;

@Autonomous(name = "Auto Align", group = "Autos")
public class AutoAlign extends LinearOpMode {
    private Limelight3A limelight;
    private MecanumDrive drive;

    // Alignment Constants
    private final double kpLateral = 0.05;
    private final double kpForward = 0.1;
    private final double forwardOffset = 5.0;
    private final double clawYOffset = -8.0;
    private final double tolerance = 0.5;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        // Initialize drive with starting pose
        Pose2d initialPose = new Pose2d(-37, -65, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        Slide slide = new Slide(this);
        Wrist wrist = new Wrist(this);
        Rotator rotator = new Rotator(this);

        telemetry.addLine("Auto Align Ready");
        telemetry.update();

        while (!opModeIsActive()&&!isStopRequested()){
            claw.setClawClosed();
            arm.Detect();
            wrist.setScorePosition();}
        rotator.moveToVertical();

        waitForStart();
        while (opModeIsActive()) {
            alignClaw();
            telemetry.update();
        }
    }

    private void alignClaw() {
        LLResult llResult = limelight.getLatestResult();
        Pose2d currentPose = drive.localizer.getPose();

        double tx = llResult.getTx();
        double ty = llResult.getTy();

        double currentX = currentPose.position.x;
        double currentY = currentPose.position.y;

        telemetry.addData("Tx", tx);
        telemetry.addData("Ty", ty);

        if (!(Math.abs(tx) < tolerance && Math.abs(ty) < tolerance)) {
            double lateralAdjustment = kpLateral * tx;
            double totalForwardAdjustment = (kpForward * ty) - forwardOffset + clawYOffset;

            double targetX = currentX + lateralAdjustment;
            double targetY = currentY + totalForwardAdjustment;

            telemetry.addData("Target X", targetX);
            telemetry.addData("Target Y", targetY);

            drive.actionBuilder(currentPose)
                    .strafeTo(new Vector2d(targetX, targetY))
                    .build();
        } else {
            telemetry.addLine("Aligned!");
        }
    }
}