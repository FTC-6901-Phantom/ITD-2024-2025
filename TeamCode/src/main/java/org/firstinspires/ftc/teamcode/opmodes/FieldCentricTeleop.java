package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.FieldCentricDrive;
import org.firstinspires.ftc.teamcode.subsystems.arm;
import org.firstinspires.ftc.teamcode.subsystems.claw;
import org.firstinspires.ftc.teamcode.subsystems.shooter;
import org.firstinspires.ftc.teamcode.subsystems.slide;

@TeleOp
public class FieldCentricTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        slide slide = new slide(this);
    FieldCentricDrive fieldCentricDrive= new FieldCentricDrive(this);
    claw claw = new claw(this);
    arm arm = new arm(this);
        waitForStart();
        while (opModeIsActive()) {
    fieldCentricDrive.fieldCentric();
     org.firstinspires.ftc.teamcode.subsystems.claw.TeleOp();
     slide.teleOp();
     org.firstinspires.ftc.teamcode.subsystems.arm.teleOp();
     shooter.teleOp();
     }
  }
}