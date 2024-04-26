package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.drive.FieldCentricDrive;

@TeleOp
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
      //  Slide slide = new Slide(this);
       FieldCentricDrive fieldCentricDrive= new FieldCentricDrive(this);
     //   Claw claw = new Claw(this);
       // Arm arm = new Arm(this);
        waitForStart();
        while (opModeIsActive()) {
           fieldCentricDrive.fieldCentric();
        //    Claw.teleOp();
            //slide.teleOp();
           // Arm.teleOp();

        }
    }
}