package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
    private final double kpForward = 0.05;
    private final double forwardOffset = .2;
    private final double clawYOffset = -.2;
    private final double tolerance = 0.4;

    @Override
    public void runOpMode() {
        try {
            limelight = hardwareMap.get(Limelight3A.class, "limelight1");
            limelight.pipelineSwitch(9);
            limelight.start(); // Start polling data
        } catch (Exception e) {
            telemetry.addLine("ERROR: Limelight not found in hardware map!");
            telemetry.update();
            return;
        }

        // Initialize drive with starting pose
        Pose2d initialPose = new Pose2d(-37, -65, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, initialPose);

        // Initialize subsystems
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        Slide slide = new Slide(this);
        Wrist wrist = new Wrist(this);
        Rotator rotator = new Rotator(this);

        telemetry.addLine("Auto Align Ready");
        telemetry.update();

        while (!opModeIsActive() && !isStopRequested()) {
            claw.setClawClosed();
            arm.Detect();
            wrist.setScorePosition();
            rotator.moveToVertical();
        }

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            alignClaw();
            telemetry.update();
        }
        limelight.stop(); // Stop polling when done
    }

    private void alignClaw() {
        if (limelight == null) {
            telemetry.addLine("ERROR: Limelight not initialized!");
            return;
        }

        LLResult llResult = limelight.getLatestResult();

        if (llResult == null || !llResult.isValid()) {
            telemetry.addLine("No valid target detected");
            return;
        }

        Pose2d
                currentPose = drive.localizer.getPose();
        double tx = llResult.getTx();
        double ty = llResult.getTy();

        telemetry.addData("Tx", tx);
        telemetry.addData("Ty", ty);

        if (Math.abs(tx) < tolerance && Math.abs(ty) < tolerance) {
            telemetry.addLine("Aligned!");
            return; // No movement needed
        }

        double lateralAdjustment = kpLateral * tx;
        double totalForwardAdjustment = (kpForward * ty) - forwardOffset + clawYOffset;

        double targetX = currentPose.position.x + lateralAdjustment;
        double targetY = currentPose.position.y + totalForwardAdjustment;

        telemetry.addData("Target X", targetX);
        telemetry.addData("Target Y", targetY);

        Actions.runBlocking(
        drive.actionBuilder(currentPose)
                .strafeTo(new Vector2d(targetX, targetY))
                .build());
    }
}
