package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Wrist {
    private static Servo wristServo;
    private static Gamepad Driver2;

    public final double score = 0.4961;
    public final double intake = 0.9244;

    private boolean isScorePosition = true;
    private boolean previousButtonState = false; // Tracks the previous state of the button

    public Wrist(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        wristServo = (Servo) opMode.hardwareMap.get("Wrist");
        wristServo.setDirection(Servo.Direction.FORWARD);
        wristServo.setPosition(score);
    }

    public void teleOp() {
        boolean currentButtonState = Driver2.b; // Current state of the 'b' button

        // Toggle position on button press only, not while button is held down
        if (currentButtonState && !previousButtonState) {
            togglePosition();
        }

        // Update previous button state for the next loop
        previousButtonState = currentButtonState;
    }

    private void togglePosition() {
        if (isScorePosition) {
            wristServo.setPosition(intake);
        } else {
            wristServo.setPosition(score);
        }
        isScorePosition = !isScorePosition;
    }

    // Methods for direct control if needed elsewhere
    public void Score() {
        wristServo.setPosition(score);
        isScorePosition = true;
    }

    public void Intake() {
        wristServo.setPosition(intake);
        isScorePosition = false;
    }
}