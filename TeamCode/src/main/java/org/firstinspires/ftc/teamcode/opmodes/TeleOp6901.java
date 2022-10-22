package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SlideSubsystem;

@TeleOp (name = "TeleOp6901")
public class TeleOp6901 extends OpMode {

    //declare subsystems
    ArmSubsystem arm = new ArmSubsystem(this);
    ClawSubsystem claw = new ClawSubsystem(this);
    DriveSubsystem drive = new DriveSubsystem(this);
    SlideSubsystem slide = new SlideSubsystem(this);

    @Override
    public void init() {
        //init subsystems
        arm.init();
        claw.init();
        drive.init();
        slide.init();
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
