package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

@TeleOp
public class CustomTeleOp extends LinearOpMode {

    private FtcDashboard dash = FtcDashboard.getInstance();
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
    public void runOpMode() throws InterruptedException {
        // Initialize subsystems
        slide = new Slide(this);
        fieldCentricDrive = new FieldCentricDrive(this);
        claw = new Claw(this);
        arm = new Arm(this);
        wrist = new Wrist(this);
        rotator = new Rotator(this);
        climb = new Climb(this);

        waitForStart();

        while (opModeIsActive()) {
            // Standard operations
            fieldCentricDrive.fieldCentric();
            claw.teleOp();
            slide.teleOp();
            arm.teleOp();
            wrist.teleOp();
            rotator.teleOp();
            climb.teleOp(); // Add the climb teleop method

            // Check for sequential action (e.g., pressing the 'A' button)
            if (gamepad2.dpad_up) {
                runningActions.add(new SequentialAction(
                        new InstantAction(slide::moveHighBasket), // Move slide to high basket
                        new SleepAction(1.5), // Wait for 2 seconds
                        new InstantAction(wrist::Score)// Adjust wrist position to score
                ));
                if (gamepad2.dpad_down) {
                    runningActions.add(new SequentialAction(
                        new InstantAction(wrist::Intake),
                        new SleepAction(1.5),
                        new InstantAction(slide::Reset)// Wait for 2 seconds
                    ));
                }
                if (gamepad2.a) {
                    runningActions.add(new SequentialAction(
                            new InstantAction(wrist::Intake),
                            new SleepAction(.25),
                            new InstantAction(arm::ArmIntake)));

                // Run the queued sequential actions
                List<Action> newActions = new ArrayList<>();
                for (Action action : runningActions) {
                    TelemetryPacket packet = new TelemetryPacket();
                    action.preview(packet.fieldOverlay());
                    if (action.run(packet)) {
                        newActions.add(action);
                    }
                    dash.sendTelemetryPacket(packet);
                }
                runningActions = newActions;
            }}}}}