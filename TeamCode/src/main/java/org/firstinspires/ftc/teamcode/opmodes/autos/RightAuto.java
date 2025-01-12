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
        Pose2d beginPose = new Pose2d(-12, 62.25, Math.toRadians(90));
        Pose2d WallIntake = new Pose2d(-37, 48, Math.toRadians(90));
        Pose2d Score1 = new Pose2d(1.5, 27.75, Math.toRadians(90));
        Pose2d Score2 = new Pose2d(.5, 27.75, Math.toRadians(90));
        Pose2d Score3 = new Pose2d(-1, 27.75, Math.toRadians(90));
        Pose2d Score4 = new Pose2d(-4.5, 27.75, Math.toRadians(90));
        Pose2d Score5 = new Pose2d(-6.25, 27.75, Math.toRadians(90));

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
                            .strafeTo(new Vector2d(1.5,27.75))
                            .build()
            );
            slide.HighRung();
            sleep(300);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            slide.Reset();

            Actions.runBlocking(
                    drive.actionBuilder(Score1)
                            .splineToLinearHeading(new Pose2d(-38,40,Math.toRadians(90)),Math.toRadians(270))
                            .strafeTo(new Vector2d(-38,10))
                            .strafeTo(new Vector2d(-52,10))
                            .strafeTo(new Vector2d(-52,56))
                            .strafeTo(new Vector2d(-52,10))
                            .strafeTo(new Vector2d(-58,10))
                            .strafeTo(new Vector2d(-58,56))
                            .strafeTo(new Vector2d(-58,10))
                            .strafeTo(new Vector2d(-66,10))
                            .strafeTo(new Vector2d(-66,56))
                            .strafeTo(new Vector2d(-37,47.75))
                            .build());

            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(750);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(.5,27.75))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();

            Actions.runBlocking(drive.actionBuilder(Score2)
                            .strafeTo(new Vector2d(-37,48))
                            .build());
            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(750);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(-1,27.75))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);

            Actions.runBlocking(drive.actionBuilder(Score3)
                    .strafeTo(new Vector2d(-37,48.25))
                    .build());
            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(750);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(-4.5,27.75))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();

            Actions.runBlocking(drive.actionBuilder(Score4)
                    .strafeTo(new Vector2d(-37,48))
                    .build());
            rotator.moveToHorizontal();
            sleep(200);
            arm.ArmIntake();
            slide.moveToWall();
            sleep(750);
            claw.setClawClosed();
            sleep(200);
            arm.ArmRest();

            Actions.runBlocking(
                    drive.actionBuilder(WallIntake)
                            .strafeTo(new Vector2d(-6.25,28))
                            .build());
            rotator.FullRotate();
            slide.HighRung();
            sleep(200);
            arm.ArmScore();
            sleep(200);
            claw.setClawOpen();
            sleep(200);
            rotator.moveToHorizontal();
            slide.Reset();

            Actions.runBlocking(drive.actionBuilder(Score5)
                    .strafeTo(new Vector2d(-37,48))
                    .build());
        }}}