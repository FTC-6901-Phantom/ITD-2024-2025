package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.DriverCentricDrive;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;

@TeleOp
public class DriverCentricTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Slide slide = new Slide(this);
        DriverCentricDrive driverCentricDrive= new DriverCentricDrive(this);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        Wrist wrist = new Wrist(this);
        waitForStart();
        while (opModeIsActive()) {
            driverCentricDrive.driverCentric();
            claw.teleOp();
            slide.teleOp();
            Arm.teleOp();
            wrist.teleOp();
            if (slide.slideLeft.getCurrentPosition()>3992){
                wrist.wristServo(wrist.outtake);}
            }
        }
    }
