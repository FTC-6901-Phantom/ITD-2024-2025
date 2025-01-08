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
@Autonomous(name = "NewLeft", group = "Autonomous")
public final class NewLeft extends LinearOpMode {

    // Declare subsystem variables

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the pose
        Pose2d beginPose = new Pose2d(-33, -63, Math.toRadians(0));

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
            wrist.setScorePosition();}
        rotator.moveToHorizontal();

        waitForStart();

        if (opModeIsActive()) {
            // Trajectory updates
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeToConstantHeading(new Vector2d(-53, -63))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-53, -63, 0))
                            .strafeToConstantHeading(new Vector2d(-20, -63))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-20, -63, 0))
                            .strafeToConstantHeading(new Vector2d(-53, -63))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-53, -63, 0))
                            .strafeToLinearHeading(new Pose2d(-48, -42, Math.toRadians(90)).component1(), Math.toRadians(90))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-48, -42, Math.toRadians(90)))
                            .strafeToConstantHeading(new Vector2d(-61, -52))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-61, -52, 90))
                            .strafeToConstantHeading(new Vector2d(-59, -42))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-59, -42, 90))
                            .strafeToConstantHeading(new Vector2d(-61, -52))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-61, -52, 90))
                            .strafeToLinearHeading(new Pose2d(-48, -26, Math.toRadians(45)).component1(), Math.toRadians(180))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-48, -26, Math.toRadians(45)))
                            .strafeToConstantHeading(new Vector2d(-54, -26))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-54, -26, 45))
                            .strafeToConstantHeading(new Vector2d(-48, -26))
                            .build());
            sleep(2000);

            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-48, -26, 45))
                            .strafeToLinearHeading(new Pose2d(-61, -52, Math.toRadians(45)).component1(), Math.toRadians(90))
                            .build());
        }
    }
}
