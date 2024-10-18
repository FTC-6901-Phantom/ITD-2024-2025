package org.firstinspires.ftc.teamcode.autos;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;

// Non-RR imports
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;

@Config
@Autonomous(name = "LeftBlue", group = "Autonomous")
public class LeftBlue extends LinearOpMode {

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(33, 63, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(this);
        Slide slides = new Slide(this);
        Wrist  wrist = new Wrist(this);
        Arm arm = new Arm(this);

        waitForStart();
        while (!isStarted() && !isStopRequested()) {
        Action trajectoryAction1;
        trajectoryAction1 = drive.actionBuilder(drive.pose)
                .setTangent(180)
                .splineToConstantHeading(new Vector2d(8, 40), -Math.PI / 2)
                .waitSeconds(2)
                .strafeTo(new Vector2d(48, 42))
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(52,52, Math.toRadians(45)), -Math.PI/2)
                .waitSeconds(2)
                .splineTo(new Vector2d(58, 42), -Math.PI / 2)
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(52,52, Math.toRadians(45)), -Math.PI/2)
                .waitSeconds(2)
                .setTangent(Math.toRadians(270))
                .lineToYLinearHeading (26,Math.toRadians(0))
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(52,52, Math.toRadians(45)), -Math.PI/2)
                .waitSeconds(2)
                .setTangent(Math.toRadians(175))
                .lineToXLinearHeading (-36,Math.toRadians(270))
                .build();}

//        // actions that need to happen on init; for instance, a claw tightening.
//        Actions.runBlocking(claw.setcloseClaw());
//
//                    Actions.runBlocking(
//                            new SequentialAction(
//                                    slides.SlidesHigh(),
//                                    claw.setOpenClaw(),
//                                    slides.ResetSlides()
//                            )
//                    );
    }
}