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
        Pose2d firstsample = new Pose2d(50, 41, Math.toRadians(270));
        Pose2d secondsample = new Pose2d(60, 40, Math.toRadians(270));
        Pose2d Basket = new Pose2d(60, 60, Math.toRadians(225));
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
                            .setTangent(Math.toRadians(350))
                            .splineToLinearHeading(new Pose2d(60, 60, Math.toRadians(225)), Math.toRadians(350))
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
            wrist.setScorePosition();
            arm.ArmRest();
            sleep(200);
            slides.Reset();

            // Go to First Sample
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .splineToLinearHeading(new Pose2d(50, 41, Math.toRadians(270)), Math.toRadians(225))
                            .build());

            // Grabbing First Sample
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(350);
            arm.ArmRest();
            sleep(100);

            // Going to scoring
            Actions.runBlocking(
                    drive.actionBuilder(firstsample)
                            .setTangent(45)
                            .splineToLinearHeading(new Pose2d(60,60, Math.toRadians(225)), Math.toRadians(45))
                    .build());

            // Scoring First Sample Score
            rotator.moveToHorizontal();
            slides.moveHighBasket();
            sleep(1000);
            arm.ArmScore();
            sleep(200);
            wrist.setScorePosition();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            wrist.setScorePosition();
            arm.ArmRest();
            sleep(200);
            slides.Reset();
            // Driving to Second Sample
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .splineTo(new Vector2d(60, 40), -Math.PI / 2)
                    .build());

            // Picking Up Second Commands
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake();
            sleep(200);
            claw.setClawClosed();
            sleep(350);
            arm.ArmRest();
            sleep(100);
            //Going to Basket Second Time
            Actions.runBlocking(
                    drive.actionBuilder(secondsample)
                            .splineToLinearHeading(new Pose2d(60,60, Math.toRadians(225)), Math.toRadians(270))
                    .build());

            // Scoring Second Commands
            rotator.moveToHorizontal();
            slides.moveHighBasket();
            sleep(1000);
            arm.ArmScore();
            sleep(200);
            wrist.setScorePosition();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            wrist.setScorePosition();
            arm.ArmRest();
            sleep(200);
            slides.Reset();
            sleep(500);

            //Going To Grabbing Third
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                            .setTangent(Math.toRadians(250))
                            .lineToYLinearHeading(26, Math.toRadians(0))
                    .build());

            //Grabbing Third Commands
            rotator.moveToVertical();
            sleep(200);
            wrist.setIntakePosition();
            sleep(200);
            arm.ArmIntake();

            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                            .setTangent(Math.toRadians(0))
                            .lineToXLinearHeading(54, Math.toRadians(0))
                            .build());

            claw.setClawClosed();
            sleep(250);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(54, 26, Math.toRadians(0)))
                            .setTangent(Math.toRadians(0))
                            .lineToXLinearHeading (50,Math.toRadians(0))
                            .build());

            arm.ArmRest();

            //Going to last Basket
            Actions.runBlocking(
                    drive.actionBuilder(thirdSample)
                    .splineToLinearHeading(new Pose2d(62,62, Math.toRadians(225)), Math.toRadians(270))
                    .build());

            // Scoring Third Commands
            rotator.moveToHorizontal();
            slides.moveHighBasket();
            sleep(1000);
            arm.ArmScore();
            sleep(200);
            wrist.setScorePosition();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            wrist.setScorePosition();
            arm.ArmRest();
            sleep(200);
            slides.Reset();
            //Park
            Actions.runBlocking(
                    drive.actionBuilder(Basket)
                    .setTangent(Math.toRadians(245))
                    .lineToYLinearHeading(0, Math.toRadians(270))
                    .build());
}}}