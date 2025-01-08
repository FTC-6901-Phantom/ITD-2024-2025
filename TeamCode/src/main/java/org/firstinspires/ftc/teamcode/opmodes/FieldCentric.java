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
import java.util.List;
@TeleOp(name = "TeleOp")
public class FieldCentric extends OpMode {
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
        climb = new Climb(this);}
    @Override
    public void loop() {
        // Standard operations
        fieldCentricDrive.fieldCentric();
        claw.teleOp();
        arm.teleOp();
        wrist.teleOp();
        rotator.teleOp();
        climb.teleop();

        // Check for sequential actions
        if (gamepad2.dpad_up) {
            runningActions.add(new SequentialAction(
                    new InstantAction(slide::moveHighBasket),
                    new InstantAction(arm::ArmRest),
                    new SleepAction(1),
                    new InstantAction(arm::ArmScore),
                    new InstantAction(rotator::moveToHorizontal),
                    new SleepAction(.5),
                    new InstantAction(wrist::setScorePosition)
            ));}
        if (gamepad2.dpad_down) {
            runningActions.add(new SequentialAction(
                    new InstantAction(arm::ArmRest),
                    new InstantAction(rotator::moveToHorizontal),
                    new SleepAction(.3),
                    new InstantAction(slide::Reset)
            ));}
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
                    new InstantAction(slide::moveToWall)));}

        // Run the queued sequential actions
        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            TelemetryPacket packet = new TelemetryPacket();
            action.preview(packet.fieldOverlay());
            if (!action.run(packet)) {
                continue;}
            newActions.add(action);
            dash.sendTelemetryPacket(packet);}
        runningActions = newActions;
    }}