package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.util.PoseStorage;

@Autonomous
public class ParkingAuto extends LinearOpMode {

    Pose2d startPose = new Pose2d(0, 0, (Math.toRadians(0)));

    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(this);
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


        switch (tagNum) {
            case 1: {
                //
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(startPose)
                        .strafeLeft(6)
                        .forward(5)
                        .build());
                break;
            }
            case 2: {

                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(startPose)
                        .forward(5)
                        .build());
                break;
            }
            default: {
                drive.followTrajectorySequence(drive.trajectorySequenceBuilder(startPose)
                        .strafeRight(6)
                        .forward(5)
                        .build());
                break;
            }

        }
        PoseStorage.currentPose = drive.getPoseEstimate();
    }

}
