package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.PoseStorage;

@TeleOp
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Claw claw = new Claw(this);
        MecanumDrive drive = new MecanumDrive(this);
        Slide slide = new Slide(this);

        drive.setPoseEstimate(PoseStorage.currentPose);

        waitForStart();
        while (opModeIsActive()) {
            claw.teleOpCommand();
            drive.mechDrive();
            slide.teleOpCommand();
            telemetry.addData("IMU:", -drive.getRawExternalHeading());
        }
    }
}