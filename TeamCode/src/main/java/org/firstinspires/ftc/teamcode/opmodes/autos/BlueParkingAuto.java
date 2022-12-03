package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class BlueParkingAuto extends LinearOpMode {

    Pose2d startPose = new Pose2d(0, 0, (Math.toRadians(0)));

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(this);
        Vision vision = new Vision(hardwareMap, "Webcam 1", telemetry);

        Slide slide = new Slide(this);
        Claw claw = new Claw(this);

        drive.setPoseEstimate(startPose);
        while (!isStarted() && !isStopRequested()) {
            vision.updateTagOfInterest();
            vision.tagToTelemetry();
            telemetry.update();
        }
        waitForStart();
        int tagNum = vision.getTag();

        TrajectorySequence myTrajectory;
        switch (tagNum) {
            case 1: {
                //
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(new Pose2d())
                        .strafeRight(12)
                        .turn(Math.toRadians(180))
                        .addDisplacementMarker(slide::moveMid)
                        .waitSeconds(1)
                        .addDisplacementMarker(claw::openClaw)
                        .waitSeconds(2)
                        .strafeRight(4)
                        .back(6)
                        .build());
                return;
            }
            case 2: {

                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(new Pose2d())
                        .strafeRight(12)
                        .turn(Math.toRadians(180))
                        .addDisplacementMarker(slide::moveMid)
                        .waitSeconds(1)
                        .addDisplacementMarker(claw::openClaw)
                        .waitSeconds(2)
                        .strafeRight(4)
                        .build());
                return;
            }
            default: {
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(new Pose2d())
                        .strafeRight(12)
                        .turn(Math.toRadians(180))
                        .addDisplacementMarker(slide::moveMid)
                        .waitSeconds(1)
                        .addDisplacementMarker(claw::openClaw)
                        .waitSeconds(2)
                        .strafeRight(4)
                        .forward(6)
                        .build());
                return;
            }

        }
    }

}
