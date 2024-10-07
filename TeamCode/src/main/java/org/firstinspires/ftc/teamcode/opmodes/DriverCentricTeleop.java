package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.autos.LeftRed;
import org.firstinspires.ftc.teamcode.drive.DriverCentricDrive;
import org.firstinspires.ftc.teamcode.subsystems.arm;
import org.firstinspires.ftc.teamcode.subsystems.claw;
import org.firstinspires.ftc.teamcode.subsystems.slide;
import org.firstinspires.ftc.teamcode.subsystems.wrist;

@TeleOp
public class DriverCentricTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        slide slide = new slide(this);
        DriverCentricDrive driverCentricDrive= new DriverCentricDrive(this);
        claw claw = new claw(this);
        arm arm = new arm(this);
        wrist wrist = new wrist(this);
        waitForStart();
        while (opModeIsActive()) {
            driverCentricDrive.driverCentric();
            claw.teleOp();
            slide.teleOp();
            org.firstinspires.ftc.teamcode.subsystems.arm.teleOp();
            wrist.teleOp();
        }
    }
}
