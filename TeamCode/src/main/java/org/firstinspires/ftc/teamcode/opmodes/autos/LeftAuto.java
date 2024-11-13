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
import org.firstinspires.ftc.teamcode.subsystems.Rotator;

@Config
@Autonomous(name = "LeftAuto", group = "Autonomous")
public final class LeftAuto extends LinearOpMode {

    // Declare subsystem variables

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the pose
        Pose2d beginPose = new Pose2d(33, 63, Math.toRadians(270));
        Pose2d specimenPos = new Pose2d(8, 40, Math.toRadians(270));
        Pose2d firstsample = new Pose2d(48, 42, Math.toRadians(270));
        Pose2d secondsample = new Pose2d(58, 42, Math.toRadians(270));
        Pose2d Basket = new Pose2d(53, 53, Math.toRadians(225));
        Pose2d thirdSample = new Pose2d(52, 26, Math.toRadians(0));

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
            arm.ArmRest();
            wrist.setIntakePosition();}
            rotator.moveToHorizontal();

        waitForStart();

        if (opModeIsActive()) {
           //preload
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(225)), -Math.PI / 2)
                            .build());

            slides.moveHighBasket();
            sleep(2000);
            arm.ArmScore();
            sleep(500);
            wrist.setScorePosition();
            sleep(400);
            claw.setClawOpen();
            sleep(450);
            arm.ArmRest();
            sleep(250);
            slides.Reset();
            sleep(1500);

            // Go to First Sample
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .splineToLinearHeading(new Pose2d(48, 42, Math.toRadians(270)), -Math.PI / 2)
                            .build());

            // Grabbing First Sample
            wrist.setIntakePosition();
            sleep(500);
            arm.ArmIntake();
            sleep(500);
            claw.setClawClosed();
            sleep(450);
            arm.ArmRest();
            sleep(500);

            // Going to scoring
            Actions.runBlocking(
                    drive.actionBuilder(firstsample)
                            .splineToLinearHeading(new Pose2d(53,53, Math.toRadians(225)), Math.toRadians(270))
                    .build());

            // Scoring First Sample Score
            slides.moveHighBasket();
            sleep(2000);
            arm.ArmScore();
            sleep(500);
            wrist.setScorePosition();
            sleep(400);
            claw.setClawOpen();
            sleep(450);
            arm.ArmRest();
            sleep(250);
            slides.Reset();
            sleep(1500);

            // Driving to Second Sample
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .splineTo(new Vector2d(58, 42), -Math.PI / 2)
                    .build());

            // Picking Up Second Commands
            wrist.setScorePosition();
            sleep(500);
            arm.ArmIntake();
            sleep(500);
            claw.setClawClosed();
            sleep(450);
            arm.ArmScore();
            sleep(500);

            //Going to Basket Second Time
            Actions.runBlocking(
                    drive.actionBuilder(secondsample)
                            .splineToLinearHeading(new Pose2d(53,53, Math.toRadians(225)), Math.toRadians(270))
                    .build());

            // Scoring Second Commands
            slides.moveHighBasket();
            sleep(2000);
            arm.ArmScore();
            sleep(500);
            wrist.setScorePosition();
            sleep(400);
            claw.setClawOpen();
            sleep(450);
            arm.ArmRest();
            sleep(250);
            slides.Reset();
            sleep(1500);

            //Going To Grabbing Third
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .setTangent(Math.toRadians(270))
                            .lineToYLinearHeading (26,Math.toRadians(0))
                    .build());

            //Grabbing Third Commands
            rotator.moveToVertical();
            sleep(1000);
            arm.ArmIntake();
            sleep(500);
            wrist.setIntakePosition();

            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                            .setTangent(Math.toRadians(0))
                            .lineToXLinearHeading (54,Math.toRadians(0))
                            .build());

            claw.setClawClosed();
            sleep(450);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(54, 26, Math.toRadians(0)))
                            .setTangent(Math.toRadians(0))
                            .lineToXLinearHeading (52,Math.toRadians(0))
                            .build());

            arm.ArmRest();
            sleep(500);

            //Going to last Basket
            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                    .splineToLinearHeading(new Pose2d(53,53, Math.toRadians(225)), -Math.PI/2)
                    .build());

            // Scoring Third Commands
            slides.moveHighBasket();
            sleep(2000);
            rotator.moveToVertical();
            wrist.setScorePosition();
            sleep(500);
            claw.setClawOpen();
            sleep(500);
            wrist.setScorePosition();
            sleep(250);
            slides.Reset();
            sleep(1500);

            //Park
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                    .setTangent(Math.toRadians(245))
                    .lineToYLinearHeading(0, Math.toRadians(270))
                    .build());
}}}