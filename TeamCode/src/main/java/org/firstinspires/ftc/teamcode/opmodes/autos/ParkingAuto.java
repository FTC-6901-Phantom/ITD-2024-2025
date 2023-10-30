package org.firstinspires.ftc.teamcode.opmodes.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Vision;
import org.firstinspires.ftc.teamcode.subsystems.drive.TankDrive;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous
public class ParkingAuto extends LinearOpMode {

    Pose2d startPose = new Pose2d(0, 0, (Math.toRadians(0)));

    @Override
    public void runOpMode() {
        TankDrive drive = new TankDrive(gamepad1, hardwareMap);

        drive.setPoseEstimate(startPose);
        while (!isStarted() && !isStopRequested()) {
            telemetry.update();
        }
        waitForStart();
        TrajectorySequence start = drive.trajectorySequenceBuilder(startPose)
                .forward(10)
                .build();

        drive.followTrajectorySequence(start);

        PoseStorage.currentPose = drive.getPoseEstimate();
    }

}
