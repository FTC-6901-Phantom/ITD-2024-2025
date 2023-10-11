package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.PoseStorage;

@Autonomous
public class LeftParkingAuto extends LinearOpMode {

    Pose2d startPose = new Pose2d(0, -62, Math.toRadians(0));

    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(this);
        Vision vision = new Vision(hardwareMap, "Webcam 1", telemetry);

        Arm slide = new Arm(this);
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
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(startPose)
                        .addDisplacementMarker(claw::closeClaw)
                        .waitSeconds(1)
                        .strafeRight(8)
                        .addDisplacementMarker(slide::moveHigh)
                        .forward(4)
                        .strafeRight(4.5)
                        .forward(0.5)
                        .addDisplacementMarker(claw::openClaw)
                        .waitSeconds(2)
                        .back(1)
                        //parking
                        .strafeLeft(10)
                        .forward(5)
                        .strafeLeft(5)
                        .build());
                break;
            }
            case 2: {

                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(startPose)
                        .addDisplacementMarker(claw::closeClaw)
                        .waitSeconds(1)
                        .strafeRight(8)
                        .addDisplacementMarker(slide::moveHigh)
                        .forward(4)
                        .strafeRight(4.5)
                        .forward(0.5)
                        .addDisplacementMarker(claw::openClaw)
                        .waitSeconds(2)
                        .back(1)
                        //parking
                                .strafeLeft(10)
                        .build());
                break;
            }
            default: {
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(startPose)
                        .addDisplacementMarker(claw::closeClaw)
                        .waitSeconds(1)
                        .strafeRight(8)
                        .addDisplacementMarker(slide::moveHigh)
                        .forward(4)
                        .strafeRight(4.5)
                        .forward(0.5)
                        .addDisplacementMarker(claw::openClaw)
                        .waitSeconds(2)
                        .back(1)
                        //parking
                                .strafeLeft(5)
                        .build());
                break;
            }

        }
        PoseStorage.currentPose = drive.getPoseEstimate();

    }
}