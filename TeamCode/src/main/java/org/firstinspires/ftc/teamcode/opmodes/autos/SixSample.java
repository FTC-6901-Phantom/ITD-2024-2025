package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmodes.VisionProcessor;
import org.firstinspires.ftc.teamcode.subsystems.Vision.Vision;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.subsystems.Rotator;

@Config
@Autonomous(name = "0+6", group = "Autonomous")
public final class SixSample extends LinearOpMode {
    MecanumDrive drive;
    Claw claw;
    Arm arm;
    Slide slides;
    Wrist wrist;
    Rotator rotator;
    Vision vision;
    VisionProcessor processor;
    Pose2d targetpose;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the pose
        Pose2d beginPose = new Pose2d(33, 63, Math.toRadians(270));
        Pose2d firstsample = new Pose2d(50.25, 41, Math.toRadians(270));
        Pose2d secondsample = new Pose2d(61, 39.5, Math.toRadians(273));
        Pose2d Basket = new Pose2d(61, 61, Math.toRadians(195));
        Pose2d thirdSample = new Pose2d(52, 22, Math.toRadians(0));

        vision = new Vision(hardwareMap, telemetry);
        targetpose = vision.getAlignedPose(drive.localizer.getPose());
        drive = new MecanumDrive(hardwareMap, beginPose);

        while (!opModeIsActive()&&!isStopRequested()){
            claw.setClawClosed();
            arm.ArmRest();
            wrist.setIntakePosition();}
        rotator.moveToHorizontal();

        waitForStart();

        if (opModeIsActive()) {
            //preload
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeToLinearHeading(new Vector2d(63, 59), Math.toRadians(195))
                            .build());

            rotator.moveToHorizontal();
            slides.moveHighBasket();
            sleep(1000);
            arm.ArmScore();
            sleep(200);
            wrist.setScorePosition();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            arm.ArmRest();
            sleep(200);
            slides.Reset();

            // Go to First Sample
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .splineToLinearHeading(new Pose2d(51, 39, Math.toRadians(275)), Math.toRadians(225))
                            .build());

            // Grabbing First Sample
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake2();
            sleep(400);
            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(550);
            arm.ArmRest();
            sleep(100);

            // Going to scoring
            Actions.runBlocking(
                    drive.actionBuilder(firstsample)
                            .setTangent(45)
                            .splineToLinearHeading(new Pose2d(61,61, Math.toRadians(195)), Math.toRadians(45))
                            .build());

            // Scoring First Sample Score
            rotator.moveToHorizontal();
            slides.moveHighBasket();
            sleep(1000);
            arm.ArmScore();
            sleep(200);
            wrist.setScorePosition();
            sleep(300);
            claw.setClawOpen();
            sleep(400);
            wrist.setScorePosition();
            arm.ArmRest();
            sleep(200);
            slides.Reset();

            // Driving to Second Sample
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .splineToLinearHeading(new Pose2d(62, 40,Math.toRadians(273)), -Math.PI / 2)
                            .build());

            // Intake Second
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake2();
            sleep(400);
            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(550);
            arm.ArmRest();
            sleep(100);
            //Going to Basket Second Time
            Actions.runBlocking(
                    drive.actionBuilder(secondsample)
                            .splineToLinearHeading(new Pose2d(61,61, Math.toRadians(195)), Math.toRadians(270))
                            .build());

            // Scoring Second Commands
            rotator.moveToHorizontal();
            slides.moveHighBasket();
            sleep(1000);
            arm.ArmScore();
            sleep(200);
            wrist.setScorePosition();
            sleep(300);
            claw.setClawOpen();
            sleep(400);
            wrist.setScorePosition();
            arm.ArmRest();
            sleep(200);
            slides.Reset();
            sleep(500);

            //Going To Grabbing Third
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .setTangent(Math.toRadians(270))
                            //.lineToYLinearHeading(25.5 , Math.toRadians(0))
                            .strafeToLinearHeading(new Vector2d(56,25.5),Math.toRadians(0))
                            .build());

            //Grabbing Third Commands
            rotator.moveToVertical();
            sleep(200);
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake2();

            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                            .setTangent(Math.toRadians(0))
                            .strafeToLinearHeading(new Vector2d(61,25.5),Math.toRadians(0))
                            .build());

            sleep(300);
            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

//            Actions.runBlocking(
//                    drive.actionBuilder(new Pose2d(54, 26, Math.toRadians(0)))
//                            .setTangent(Math.toRadians(0))
//                            .lineToXLinearHeading (50,Math.toRadians(0))
//                            .build());

            //arm.ArmRest();

            //Going to last Basket
            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                            .splineToLinearHeading(new Pose2d(61,59, Math.toRadians(195)), Math.toRadians(270))
                            .build());

            // Scoring Third Commands
            rotator.moveToHorizontal( );
            slides.moveHighBasket();
            sleep(1000);
            arm.ArmScore();
            sleep(200);
            wrist.setScorePosition();
            sleep(300);
            claw.setClawOpen();
            sleep(400);
            wrist.setScorePosition();
            arm.ArmRest();
            sleep(200);
            slides.Reset();
            claw.setClawClosed();
            //Submersible Grab 1st
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .setTangent(Math.toRadians(238.5))
                            .lineToYLinearHeading(-15, Math.toRadians(180))
                            .build());

                    processor.runVisionProcessing();
        }}}