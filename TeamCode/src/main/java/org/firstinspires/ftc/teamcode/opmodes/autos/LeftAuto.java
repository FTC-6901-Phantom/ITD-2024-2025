package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;

@Config
@Autonomous(name = "LeftAuto", group = "Autonomous")
public final class LeftAuto extends LinearOpMode {

    // Declare subsystem variables

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the pose
        Pose2d beginPose = new Pose2d(33, 63, Math.toRadians(270));
        Pose2d specimenPos = new Pose2d(8, 40, Math.toRadians(270));
        Pose2d firstsample = new Pose2d(52, 42, Math.toRadians(270));
        Pose2d secondsample = new Pose2d(60, 42, Math.toRadians(270));
        Pose2d Basket = new Pose2d(52, 52, Math.toRadians(25));
        Pose2d thirdSample = new Pose2d(52, 26, Math.toRadians(0));





        // Initialize the drivetrain
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        // Initialize other subsystems
        Claw claw = new Claw(this);  // Assuming Claw class exists
        Arm arm = new Arm(this);    // Assuming Arm class exists
        Slide slides = new Slide(this); // Assuming Slides class exists
        Wrist wrist = new Wrist(this);

        while (!opModeIsActive()&&!isStopRequested()){
            claw.setOpenClaw();
            arm.ArmUp();
            wrist.Specimen();}

        waitForStart();

        if (opModeIsActive()) {
            //Scoring Specimen Path
//            Actions.runBlocking(
//                    drive.actionBuilder(beginPose)
//                    .setTangent(180)
//                    .splineToConstantHeading(new Vector2d(8, 40), -Math.PI / 2)
//                    .build());
//
//            // Scoring Commands
//            slides.SlidesHigh();  // Example slides action
//            sleep(2000);
//            wrist.WristScore();
//            sleep(500);
//            claw.setOpenClaw();
//            sleep(250);
//            wrist.Specimen();
//            sleep(1000);
//            slides.ResetSlides();

            // Strafe to First Sample
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                    .strafeTo(new Vector2d(52, 42))
                    .build());

            // Grabbing First Sample
            wrist.WristScore();
            arm.ArmDown();
            sleep(1000);
            claw.setcloseClaw();
            sleep(500);
            arm.ArmUp();
            wrist.Specimen();

            // Going to scoring
            Actions.runBlocking(
                    drive.actionBuilder(firstsample)
                    .splineToLinearHeading(new Pose2d(52, 52, Math.toRadians(25)), -Math.PI / 2)
                    .build());

            // Scoring First Sample Score
            slides.SlidesHigh();
            sleep(2000);
            wrist.WristScore();
            sleep(500);
            claw.setOpenClaw();
            sleep(500);
            wrist.Specimen();
            sleep(450);
            slides.ResetSlides();
            sleep(1500);

            // Driving to Second Sample
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                    .splineTo(new Vector2d(60, 42), -Math.PI / 2)
                    .build());

            // Picking Up Second Commands
            arm.ArmDown();
            sleep(500);
            wrist.WristScore();
            sleep(500);
            claw.setcloseClaw();
            sleep(450);
            arm.ArmUp();
            wrist.Specimen();

            //Going to Basket Second Time
            Actions.runBlocking(
                    drive.actionBuilder(secondsample)
                    .splineToLinearHeading(new Pose2d(54, 54, Math.toRadians(35)), -Math.PI / 2)
                    .build());

            // Scoring Second Commands
            slides.SlidesHigh();
            sleep(2000);
            wrist.WristScore();
            sleep(500);
            claw.setOpenClaw();
            sleep(500);
            wrist.Specimen();
            slides.ResetSlides();
            sleep(1500);

            //Going To Grabbing Third
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(54, 54, Math.toRadians(35)))
                    .setTangent(Math.toRadians(270))
                    .lineToYLinearHeading(26, Math.toRadians(0))
                    .build());

            //Grabbing Third Commands
            arm.ArmDown();
            sleep(500);
            wrist.WristScore();
            sleep(500);
            claw.setcloseClaw();
            sleep(450);
            arm.ArmUp();
            wrist.Specimen();

            //Going to last Basket
            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                    .splineToLinearHeading(new Pose2d(52, 52, Math.toRadians(25)), -Math.PI / 2)
                    .build());

            // Scoring Third Commands
            slides.SlidesHigh();
            sleep(2000);
            wrist.WristScore();
            sleep(500);
            claw.setOpenClaw();
            sleep(500);
            wrist.Specimen();
            slides.ResetSlides();
            sleep(1500);

            //Park
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                    .setTangent(Math.toRadians(175))
                    .lineToXLinearHeading(-36, Math.toRadians(270))
                    .build());
}}}