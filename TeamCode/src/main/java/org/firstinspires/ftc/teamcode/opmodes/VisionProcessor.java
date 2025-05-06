package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.subsystems.drive.FieldCentricDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Vision.Vision;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group = "TeleOp", name = "VisionTest")
public class VisionProcessor extends LinearOpMode {
    Vision vision;
    MecanumDrive drive;
    FieldCentricDrive fieldCentricDrive;
    // Initialize subsystems
    Claw claw;
    Arm arm;
    Slide slide;
    Wrist wrist;
    Rotator rotator;

    Pose2d targetPose;

    @Override
    public void runOpMode() {
        // Initialize vision system
        vision = new Vision(hardwareMap, telemetry);

        // Initialize Mecanum drive with an initial pose
        Pose2d startPose = new Pose2d(-37, -65, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, startPose);

        // Initialize subsystems
        claw = new Claw(this);
        arm = new Arm(this);
        slide = new Slide(this);
        wrist = new Wrist(this);
        rotator = new Rotator(this);
        fieldCentricDrive = new FieldCentricDrive(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Pre-start setup
        while (!opModeIsActive() && !isStopRequested()) {
            claw.setClawOpen();
            arm.ArmIntake();
            wrist.setScorePosition();
            rotator.moveToVertical();
            slide.moveToWall();
        }
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            fieldCentricDrive.fieldCentric();
            if (gamepad1.a) { // When Driver 1 presses "A", run vision-based movement
                runVisionProcessing();
            }
            if (gamepad1.dpad_down){
                claw.setClawOpen();
                arm.ArmIntake();
                wrist.setScorePosition();
                rotator.moveToVertical();
                slide.moveToWall();
            }
            // Add any additional driving or actions here
        }
    }

    public void runVisionProcessing() {
        long startTime = System.currentTimeMillis();
        long timeout = 5000; // 5-second timeout

        while (opModeIsActive() && (System.currentTimeMillis() - startTime) < timeout) {
            vision.periodic();  // Update vision processing
            targetPose = vision.getAlignedPose(drive.localizer.getPose());
            String bestLabel = vision.getBestLabel();

            if (!bestLabel.equals("yellow")) {
                telemetry.addLine("No yellow object detected, skipping movement...");
                telemetry.update();
                return; // Exit if no yellow object is found
            }

            if (vision.isVisionComplete()) {
                telemetry.addData("Vision Status", "Yellow object detected, moving to target");
                Actions.runBlocking(
                        drive.actionBuilder(drive.localizer.getPose())
                                .setTangent(45)
                                .splineToLinearHeading(targetPose, Math.toRadians(45))
                                .build());

                wrist.setIntakePosition();
                sleep(500);
                slide.Reset();
                sleep(200);
                claw.setClawClosed();
                sleep(550);
                arm.ArmRest();
                sleep(100);
                return; // Exit after movement
            } else {
                telemetry.addLine("Waiting for vision completion...");
            }

            telemetry.addData("Best Label", bestLabel);
            telemetry.update();

            telemetry.addData("Pos", drive.localizer.getPose());
            telemetry.update();
        }

        telemetry.addLine("Vision timeout! Proceeding with last known position.");
        telemetry.update();
    }
}
