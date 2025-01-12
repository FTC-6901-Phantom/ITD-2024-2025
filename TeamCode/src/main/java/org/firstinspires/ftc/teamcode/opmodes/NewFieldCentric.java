package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SleepAction;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.subsystems.Rotator;
import org.firstinspires.ftc.teamcode.subsystems.Climb;
import org.firstinspires.ftc.teamcode.subsystems.drive.FieldCentricDrive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TeleOp(name = "TeleOp2")
public class NewFieldCentric extends OpMode {

    private final FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    // Subsystems
    private Slide slide;
    private FieldCentricDrive fieldCentricDrive;
    private Claw claw;
    private Arm arm;
    private Wrist wrist;
    private Rotator rotator;
    private Climb climb;

    @Override
    public void init() {
        // Initialize subsystems
        slide = new Slide(this);
        fieldCentricDrive = new FieldCentricDrive(this);
        claw = new Claw(this);
        arm = new Arm(this);
        wrist = new Wrist(this);
        rotator = new Rotator(this);
        climb = new Climb(this);
    }
    private boolean wasLeftBumperPressed = false;
    @Override
    public void loop() {

        if (gamepad2.left_bumper) {
            fieldCentricDrive.setSpeed(0.5); // Reduce speed while left bumper is held
        } else if (slide.isAtHighPosition()) {
            fieldCentricDrive.setSpeed(0.5); // Reduce speed when slides are at high position
        } else {
            fieldCentricDrive.setSpeed(1.0); // Default speed
        }
        // Standard operations
        fieldCentricDrive.fieldCentric();
        claw.teleOp();
        rotator.teleOp();
        climb.teleop();

        // Check for left bumper actions
        boolean isLeftBumperPressed = gamepad2.left_bumper;

        if (isLeftBumperPressed && wasLeftBumperPressed) {
            // Actions triggered on the initial press of the left bumper
            runningActions.add(new SequentialAction(Arrays.asList(
                    new InstantAction(slide::Reset),
                    new InstantAction(arm::ArmIntake2),
                    new SleepAction(0.1), // 0.05-second delay
                    new InstantAction(claw::setClawOpen),
                    new SleepAction(0.1), // 0.05-second delay
                    new InstantAction(wrist::setIntakePosition),
                    new SleepAction(0.1))));
                    // Reset slide last)));
        } else if (!isLeftBumperPressed && wasLeftBumperPressed) {
            // Actions triggered on the release of the left bumper
            runningActions.add(new SequentialAction(Arrays.asList(
                    new InstantAction(arm::ArmIntake),
                    new SleepAction(0.2), // Wait 0.2 seconds
                    new InstantAction(claw::setClawClosed),
                    new SleepAction(0.4), // Wait 0.4 seconds
                    new InstantAction(wrist::setScorePosition),
                    new SleepAction(1),
                    new InstantAction(arm::ArmRest),
                    new InstantAction(wrist::setIntakePosition)
            )));}
        // Check if the claw is open and slides are at the high position

        if (claw.isOpen() && slide.isAtHighPosition()) {
            runningActions.add(new SequentialAction(
                    new SleepAction(.3),
                    new InstantAction(arm::ArmRest),
                    new SleepAction(.3),
                    new InstantAction(slide::Reset),
                    new InstantAction(rotator::moveToHorizontal)
            ));
        }
        // Update the previous state of the left bumper
        wasLeftBumperPressed = isLeftBumperPressed;
        // Check for dpad actions
        if (gamepad2.dpad_up) {
            runningActions.add(new SequentialAction(
                    new InstantAction(slide::moveHighBasket),
                    new InstantAction(arm::ArmRest),
                    new InstantAction(rotator::moveToHorizontal),
                    new SleepAction(1),
                    new InstantAction(arm::ArmScore),
                    new SleepAction(.5),
                    new InstantAction(wrist::setScorePosition)));}

        if (gamepad2.dpad_right) {
            runningActions.add(new SequentialAction(
                    new InstantAction(arm::ArmRest),
                    new SleepAction(.1),
                    new InstantAction(rotator::FullRotate),
                    new InstantAction(slide::HighRung),
                    new SleepAction(.4),
                    new InstantAction(wrist::setScorePosition),
                    new SleepAction(.1),
                    new InstantAction(arm::ArmScore),
                    new SleepAction(.4),
                    new InstantAction(claw::setClawOpen)
            ));}

        if (gamepad2.dpad_left) {
            runningActions.add(new SequentialAction(
                    new InstantAction(arm::ArmIntake),
                    new SleepAction(.1),
                    new InstantAction(rotator::moveToHorizontal),
                    new InstantAction(slide::moveToWall),
                    new SleepAction(.2),
                    new InstantAction(wrist::setScorePosition)));}

        if (gamepad2.dpad_down) {
            runningActions.add(new SequentialAction(
                    new InstantAction(arm::ArmRest),
                    new InstantAction(rotator::moveToHorizontal),
                    new SleepAction(.3),
                    new InstantAction(slide::Reset)
            ));}

        // Run the queued sequential actions
        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            TelemetryPacket packet = new TelemetryPacket();
            action.preview(packet.fieldOverlay());
            if (!action.run(packet)) {
                continue;
            }
            newActions.add(action);
            dash.sendTelemetryPacket(packet);
        }
        runningActions = newActions;
    }}