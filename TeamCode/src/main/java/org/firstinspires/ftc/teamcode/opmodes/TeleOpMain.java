package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.drive.TankDrive;
import org.firstinspires.ftc.teamcode.util.PoseStorage;

@TeleOp
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        TankDrive drive = new TankDrive(gamepad1,hardwareMap);

        drive.setPoseEstimate(PoseStorage.currentPose);

        waitForStart();
        while (opModeIsActive()) {
            claw.teleOpCommand();
            drive.robotDrive();
            arm.teleOpCommand();
            telemetry.addData("IMU:", -drive.getRawExternalHeading());
        }
    }
}