package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.TankDrive;
import org.firstinspires.ftc.teamcode.util.PoseStorage;

@TeleOp
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        TankDrive drive = new TankDrive(gamepad1,hardwareMap);
        Intake intake = new Intake(this);
        MecanumDrive mech = new MecanumDrive(this);

        drive.setPoseEstimate(PoseStorage.currentPose);

        waitForStart();
        while (opModeIsActive()) {
            mech.mechDrive();
            claw.teleOpCommand();
            drive.robotDrive();
            arm.teleOpCommand();
            intake.teleOpCommand();
            telemetry.addData("IMU:", -drive.getRawExternalHeading());
        }
    }
}