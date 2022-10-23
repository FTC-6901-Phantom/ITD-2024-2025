package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Slide;

@TeleOp (name = "TeleOp6901")
public class TeleOp6901 extends OpMode {

    //declare subsystems
    Arm arm;
    Claw claw;
    Drive drive;
    Slide slide;

    @Override
    public void init() {
        // create hardwares subsystems using their constructors and initalize motors and servos
        arm = new Arm(this);
        claw = new Claw(this);
        drive = new Drive(this);
        slide = new Slide(this);
    }

    @Override
    public void loop() {
        //run subsystem loop comamnd
        arm.teleOpCommand();
        claw.teleOpCommand();
        drive.teleOpCommand();
        slide.teleOpCommand();
    }
}
