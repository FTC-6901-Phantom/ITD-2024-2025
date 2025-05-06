package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.InstantAction;
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
import org.firstinspires.ftc.teamcode.subsystems.Rotator;

@Config
@Autonomous(name = "0+5", group = "Autonomous")
public final class ZenithAuto extends LinearOpMode {

    // Declare subsystem variables
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the pose
        Pose2d beginPose = new Pose2d(33, 63, Math.toRadians(270));
        Pose2d firstsample = new Pose2d(50.25, 41, Math.toRadians(270));
        Pose2d secondsample = new Pose2d(61, 39.5, Math.toRadians(273));
        Pose2d Basket = new Pose2d(61, 61, Math.toRadians(195));
        Pose2d thirdSample = new Pose2d(52, 22, Math.toRadians(0));
        Pose2d fourthSample = new Pose2d(14, 58, Math.toRadians(180));

        // Initialize the drivetrain
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        // Initialize other subsystems
        Claw claw = new Claw(this);  // Assuming Claw class exists
        Arm arm = new Arm(this);    // Assuming Arm class exists
        Slide slides = new Slide(this); // Assuming Slides class exists
        Wrist wrist = new Wrist(this);
        Rotator rotator = new Rotator(this);

        while (!opModeIsActive()&&!isStopRequested()){
            claw.setClawClosed();
            arm.ArmScore();
            wrist.setIntakePosition();}
        rotator.moveToVertical();

        waitForStart();

        if (opModeIsActive()) {
            //preload

            arm.ArmRest();
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .afterTime(.5, new InstantAction(slides::moveHighBasket))
                            .strafeToLinearHeading(new Vector2d(63, 57), Math.toRadians(215))
                            .build());

            rotator.moveToHorizontal();
            sleep(250);
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
                            .splineToLinearHeading(new Pose2d(51.25, 39.75, Math.toRadians(270)), Math.toRadians(225))
                            .build());

            // Grabbing First Sample
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake2();
            sleep(400);
            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(250);
            arm.ArmRest();

            // Going to scoring
            Actions.runBlocking(
                    drive.actionBuilder(firstsample)
                            .afterTime(.6, new InstantAction(slides::moveHighBasket))
                            .setTangent(45)
                            .splineToLinearHeading(new Pose2d(63,57, Math.toRadians(215)), Math.toRadians(45))
                            .build());

            // Scoring First Sample Score
            rotator.moveToHorizontal();
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
                            .splineToLinearHeading(new Pose2d(61, 40,Math.toRadians(273)), -Math.PI / 2)
                            .build());

            // Picking Up Second Commands
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake2();
            sleep(400);
            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(250);
            arm.ArmRest();
            //Going to Basket Second Time
            Actions.runBlocking(
                    drive.actionBuilder(secondsample)
                            .afterTime(.6, new InstantAction(slides::moveHighBasket))
                            .splineToLinearHeading(new Pose2d(60,55, Math.toRadians(215)), Math.toRadians(270))
                            .build());

            // Scoring Second Commands
            rotator.moveToHorizontal();
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

            //Going To Grabbing Third
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .setTangent(Math.toRadians(270))
                            //.lineToYLinearHeading(25.5 , Math.toRadians(0))
                            .strafeToLinearHeading(new Vector2d(56,26),Math.toRadians(0))
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
                            .strafeToLinearHeading(new Vector2d(57,26),Math.toRadians(0))
                            .build());

            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(250);
            arm.ArmRest();

            //Going to last Basket
            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                            .afterTime(1.65, new InstantAction(slides::moveHighBasket))
                            .splineToLinearHeading(new Pose2d(65,56, Math.toRadians(215)), Math.toRadians(270))
                            .build());

            // Scoring Third Commands
            rotator.moveToHorizontal( );
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

//            Actions.runBlocking(
//                    drive.actionBuilder(Basket)
//                            .setTangent(Math.toRadians(180))
//                            .splineToLinearHeading(new Pose2d(22.5,58, Math.toRadians(180)), Math.toRadians(180))
//                            .build());

//            wrist.setIntakePosition();
//            sleep(200);
//            arm.ArmIntake2();
//            sleep(400);
//            arm.ArmIntake();
//            sleep(200);
//            claw.setClawClosed();
//            sleep(250);
//            arm.ArmRest();
//
//            Actions.runBlocking(
//                    drive.actionBuilder(fourthSample)
//                            .afterTime(1.65, new InstantAction(slides::moveHighBasket))
//                            .setTangent(0)
//                            .splineToLinearHeading(new Pose2d(64,56, Math.toRadians(215)), Math.toRadians(0))
//                            .build());
//
//            rotator.moveToHorizontal();
//            arm.ArmScore();
//            sleep(200);
//            wrist.setScorePosition();
//            sleep(300);
//            claw.setClawOpen();
//            sleep(400);
//            wrist.setScorePosition();
//            arm.ArmRest();
//            sleep(200);
//            slides.Reset();
            //Park
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .setTangent(Math.toRadians(239.5))
                            .lineToYLinearHeading(-15, Math.toRadians(180))
                            .build());

        }}}