package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private static Servo leftServo;
    private static Servo rightServo;
    private static Gamepad Driver1;
    private static Gamepad Driver2;

    private static final double scorePosition = .9;
    private static final double intakePosition = 0.05;
    private static final double rest = 0.58;
    private static final double detection = 0.32;
    private static final double intake2 = 0.14;

    public static boolean ArmIsUp;
    private static boolean previousButtonState = false; // Tracks the previous state of the 'a' button

    public Arm(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        leftServo = opMode.hardwareMap.get(Servo.class, "leftArm");
        rightServo = opMode.hardwareMap.get(Servo.class, "rightArm");

        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.REVERSE);
        armServo(rest, rest);
        ArmIsUp = true;
    }

    public static void teleOp() {
        // Debounce for toggle on Driver2.a button
        boolean currentButtonState = Driver2.a;
        if (currentButtonState && !previousButtonState) {
            toggleArmPosition();
        }
        previousButtonState = currentButtonState;

        // Direct D-pad controls
        if (Driver2.dpad_down) {
            armServo(rest, rest);
        }
    }

    private static void toggleArmPosition() {
        if (ArmIsUp) {
            armServo(intakePosition, intakePosition);
            ArmIsUp = false;
        } else {
            armServo(rest, rest);
            ArmIsUp = true;
        }
    }

    private static void armServo(double setPositionRight, double setPositionLeft) {
        rightServo.setPosition(setPositionRight);
        leftServo.setPosition(setPositionLeft);
    }

    // Methods for direct control if needed elsewhere
    public void ArmScore() {
        armServo(scorePosition, scorePosition);
        ArmIsUp = true;
    }

    public void ArmIntake() {
        armServo(intakePosition, intakePosition);
        ArmIsUp = false;
    }

    public void ArmRest() {
        armServo(rest, rest);
    }

    public void ArmIntake2() {
        armServo(intake2, intake2);
    }
    public void Detect() {
        armServo(detection, detection);
    }

}