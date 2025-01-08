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
        Pose2d beginPose = new Pose2d(-12, 63, Math.toRadians(90));
        Pose2d WallIntake = new Pose2d(-37, 50, Math.toRadians(90));
        Pose2d Score1 = new Pose2d(-2, 27, Math.toRadians(90));
        Pose2d Score2 = new Pose2d(-2.5, 27, Math.toRadians(90));
        Pose2d Score3 = new Pose2d(-3, 27, Math.toRadians(90));
        Pose2d Score4 = new Pose2d(-3.5, 27, Math.toRadians(90));
        Pose2d Score5 = new Pose2d(-4, 27, Math.toRadians(90));



        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);
        Slide slide = new Slide(this);
        Wrist wrist = new Wrist(this);
        Rotator rotator = new Rotator(this);

        while (!opModeIsActive() && !isStopRequested()) {
            claw.setClawClosed();
            arm.ArmRest();
            wrist.setScorePosition();
            rotator.FullRotate();
        }

        waitForStart();

        if (opModeIsActive()) {
            // Preload
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeTo(new Vector2d(-2,32))
                            .build()
            );
            slide.HighRung();
            sleep(100);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            arm.ArmRest();
            slide.Reset();
            sleep(50);
            rotator.moveToHorizontal();

            Actions.runBlocking(
                    drive.actionBuilder(Score1)
                            .splineToLinearHeading(new Pose2d(-38,40,Math.toRadians(90)),Math.toRadians(270))
                            .strafeTo(new Vector2d(-38,10))
                            .strafeTo(new Vector2d(-46,10))
                            .strafeTo(new Vector2d(-46,56))
                            .strafeTo(new Vector2d(-46,10))
                            .strafeTo(new Vector2d(-54,10))
                            .strafeTo(new Vector2d(-54,56))
                            .strafeTo(new Vector2d(-54,10))
                            .strafeTo(new Vector2d(-62,10))
                            .strafeTo(new Vector2d(-62,56))
                            .strafeTo(new Vector2d(-37,50))
                            .build());

            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(100);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(-2.5,32))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(100);
            wrist.setScorePosition();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            arm.ArmRest();
            slide.Reset();
            sleep(50);
            rotator.moveToHorizontal();

            Actions.runBlocking(drive.actionBuilder(Score2)
                            .strafeTo(new Vector2d(-37,50))
                            .build());
            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(100);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(-3,32))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(100);
            wrist.setScorePosition();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            arm.ArmRest();
            slide.Reset();
            sleep(50);
            rotator.moveToHorizontal();

            Actions.runBlocking(drive.actionBuilder(Score3)
                    .strafeTo(new Vector2d(-37,50))
                    .build());
            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(100);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(-3.5,32))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(100);
            wrist.setScorePosition();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            arm.ArmRest();
            slide.Reset();
            sleep(50);
            rotator.moveToHorizontal();

            Actions.runBlocking(drive.actionBuilder(Score4)
                    .strafeTo(new Vector2d(-37,50))
                    .build());
            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(100);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(-4,32))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(100);
            wrist.setScorePosition();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            arm.ArmRest();
            slide.Reset();
            sleep(50);
            rotator.moveToHorizontal();

            Actions.runBlocking(drive.actionBuilder(Score4)
                    .strafeTo(new Vector2d(-37,50))
                    .build());
        }}}