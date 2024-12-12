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
@Autonomous(name = "RightAuto", group = "Autonomous")
public final class RightAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(-10, 63, Math.toRadians(270));

        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        Slide slides = new Slide(this);
        Wrist wrist = new Wrist(this);
        Rotator rotator = new Rotator(this);

        while (!opModeIsActive() && !isStopRequested()) {
            claw.setClawClosed();
            arm.ArmRest();
            wrist.setIntakePosition();
            rotator.moveToHorizontal();
        }

        waitForStart();

        if (opModeIsActive()) {
            // Preload
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .lineToYConstantHeading(38)
                            .waitSeconds(0.5)
                            .build()
            );

            // Grab First
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-10, 38, Math.toRadians(270)))
                            .strafeTo(new Vector2d(-48, 42))
                            .waitSeconds(0.3)
                            .build()
            );

            // Drop Off 1
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-48, 42, Math.toRadians(270)))
                            .setTangent(Math.toRadians(90))
                            .lineToYConstantHeading(-48)
                            .waitSeconds(0.3)
                            .build()
            );

            // Grab Second
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-48, 50, Math.toRadians(90)))
                            .strafeTo(new Vector2d(-58, 42))
                            .waitSeconds(0.3)
                            .build()
            );

            // Drop Off 2
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-58, 42, Math.toRadians(90)))
                            .setTangent(Math.toRadians(90))
                            .lineToYConstantHeading(50)
                            .waitSeconds(0.3)
                            .build()
            );

            // Grab 2
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-58, 50, Math.toRadians(90)))
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                            .waitSeconds(0.5)
                            .build()
            );

            // Score 2nd
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-36, 56, Math.toRadians(90)))
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(-6, 38, Math.toRadians(-90)), Math.toRadians(135))
                            .waitSeconds(0.5)
                            .build()
            );

            // Grab 3rd
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-6, 38, Math.toRadians(-90)))
                            .setTangent(90)
                            .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                            .waitSeconds(0.5)
                            .build()
            );

            //Score 3rd
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-58, 50, Math.toRadians(90)))
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                            .waitSeconds(0.5)
                            .build()
            );

            // Grab 4th
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-6, 38, Math.toRadians(-90)))
                            .setTangent(90)
                            .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                            .waitSeconds(0.5)
                            .build()
            );
            //Score 4th
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-58, 50, Math.toRadians(90)))
                            .setTangent(0)
                            .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                            .waitSeconds(0.5)
                            .build()
            );
        }}}