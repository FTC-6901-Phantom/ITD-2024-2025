package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Rotator {
    private static Servo rotatorServo;
    private static Gamepad driver1;
    private static Gamepad driver2;

    private static final double VERTICAL_POSITION = 0.0; // Adjust to correct value
    private static final double HORIZONTAL_POSITION = 0.5444; // Adjust to correct value

    private boolean isVertical = true;
    private int debounceCounter = 0;
    private static final int DEBOUNCE_THRESHOLD = 40;

    public Rotator(OpMode opMode) {
        driver1 = opMode.gamepad1;
        driver2 = opMode.gamepad2;
        rotatorServo = opMode.hardwareMap.get(Servo.class, "rotator");

        rotatorServo.setDirection(Servo.Direction.FORWARD);
        setVertical(); // Initialize to vertical position
    }

    public void teleOp() {
        handleToggle();

        // Additional teleOp functionality can be added here if needed
    }

    private void handleToggle() {
        if (debounceCounter > DEBOUNCE_THRESHOLD) {
            if (driver2.right_bumper) {
                if (isVertical) {
                    setHorizontal();
                } else {
                    setVertical();
                }
                debounceCounter = 0; // Reset debounce counter after action
            }
        } else {
            debounceCounter++;
        }
    }

    private void setVertical() {
        rotatorServo.setPosition(VERTICAL_POSITION);
        isVertical = true;
    }

    private void setHorizontal() {
        rotatorServo.setPosition(HORIZONTAL_POSITION);
        isVertical = false;
    }

    // Additional utility methods if you need direct access to these positions
    public void moveToVertical() {
        setVertical();
    }

    public void moveToHorizontal() {
        setHorizontal();
    }
}