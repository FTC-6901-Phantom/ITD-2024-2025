package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.FieldCentricDrive;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.drive.DriverCentricDrive;

@TeleOp
public class DriverCentricTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Slide slide = new Slide(this);
        DriverCentricDrive driverCentricDrive= new DriverCentricDrive(this);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        waitForStart();
        while (opModeIsActive()) {
            driverCentricDrive.driverCentric();
            Claw.TeleOp();
            slide.teleOp();
            Arm.teleOp();
        }
    }
}