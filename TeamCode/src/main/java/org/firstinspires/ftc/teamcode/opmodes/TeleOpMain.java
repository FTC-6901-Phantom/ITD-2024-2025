package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.DroneLaunch;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;

@TeleOp
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        Slide slide = new Slide(this);
        Intake intake = new Intake(this);
        MecanumDrive mech = new MecanumDrive(this);
        DroneLaunch drone = new DroneLaunch(this);

        waitForStart();
        while (opModeIsActive()) {
            mech.fieldCentric();
            claw.teleOpCommand();
            arm.teleOpCommand();
            slide.teleOpCommand();
            intake.teleOpCommand();
            drone.teleOpCommand();
        }

        /* For me :3
        Subsystems:
        Drive Train
        Slides 2M
        Intake 1CRS
        Arm 2S
        Claw 1S
        Drone Launcher 1S
         */
    }
}