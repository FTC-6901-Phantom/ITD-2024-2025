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
@Autonomous(name = "LeftAuto2", group = "Autonomous")
public final class AutoRedone extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize starting pose
        Pose2d currentPose = new Pose2d(33, 63, Math.toRadians(270));

        // Initialize subsystems
        MecanumDrive drive = new MecanumDrive(hardwareMap, currentPose);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        Slide slides = new Slide(this);
        Wrist wrist = new Wrist(this);
        Rotator rotator = new Rotator(this);

        // Pre-match setup
        while (!opModeIsActive() && !isStopRequested()) {
            claw.setClawClosed();
            arm.ArmRest();
            wrist.setIntakePosition();
            rotator.moveToHorizontal();
        }
        rotator.moveToHorizontal();
        waitForStart();

        if (opModeIsActive()) {
            // Preload scoring
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(225)), -Math.PI / 2)
                            .afterTime(-2, () -> {  // Begin actions 2s before arrival
                                slides.moveHighBasket();
                                arm.ArmScore();
                            })
                            .build()
            );
            currentPose = new Pose2d(53, 53, Math.toRadians(225));

            wrist.setScorePosition();
            sleep(500);
            claw.setClawOpen();
            sleep(450);
            arm.ArmRest();
            slides.Reset();

            // First Sample trajectory
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .splineTo(new Vector2d(48, 42), -Math.PI / 2)
                            .afterTime(-2, () -> {
                                wrist.setIntakePosition();
                                arm.ArmIntake();
                            })
                            .build()
            );
            currentPose = new Pose2d(48, 42, Math.toRadians(270));

            claw.setClawClosed();
            sleep(500);
            arm.ArmRest();

            // First Sample scoring
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(225)), Math.toRadians(270))
                            .afterTime(-2, () -> {
                                slides.moveHighBasket();
                                arm.ArmScore();
                            })
                            .build()
            );
            currentPose = new Pose2d(53, 53, Math.toRadians(225));

            wrist.setIntakePosition();
            claw.setClawOpen();
            sleep(500);
            arm.ArmRest();
            slides.Reset();

            // Second Sample trajectory
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .splineTo(new Vector2d(58, 42), -Math.PI / 2)
                            .afterTime(-2, () -> {
                                wrist.setScorePosition();
                                arm.ArmIntake();
                            })
                            .build()
            );
            currentPose = new Pose2d(58, 42, Math.toRadians(270));

            claw.setClawClosed();
            sleep(500);
            arm.ArmRest();

            // Second Sample scoring
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(225)), Math.toRadians(270))
                            .afterTime(-2, () -> {
                                slides.moveHighBasket();
                                arm.ArmScore();
                            })
                            .build()
            );
            currentPose = new Pose2d(53, 53, Math.toRadians(225));

            wrist.setScorePosition();
            claw.setClawOpen();
            sleep(500);
            arm.ArmRest();
            slides.Reset();

            // Third Sample trajectory
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .lineToYLinearHeading(26, Math.toRadians(0))
                            .afterTime(-2, () -> {
                                rotator.moveToVertical();
                                arm.ArmIntake();
                                wrist.setIntakePosition();
                            })
                            .build()
            );
            currentPose = new Pose2d(52, 26, Math.toRadians(0));

            claw.setClawClosed();
            sleep(500);

            // Third Sample scoring
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(225)), -Math.PI / 2)
                            .afterTime(-2, () -> {
                                slides.moveHighBasket();
                                wrist.setScorePosition();
                            })
                            .build()
            );
            currentPose = new Pose2d(53, 53, Math.toRadians(225));

            claw.setClawOpen();
            sleep(500);
            slides.Reset();

            // Final Parking
            Actions.runBlocking(
                    drive.actionBuilder(currentPose)
                            .lineToYLinearHeading(0, Math.toRadians(270))
                            .build()
            );
        }
    }
}