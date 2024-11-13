package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Claw {
    private static final double OPEN_POSITION = 0.0;
    private static final double CLOSED_POSITION = 0.6;

    private static Servo clawServo;
    private static ColorRangeSensor colorSensor;
    private static Gamepad driver1;
    private static Gamepad driver2;

    public static boolean isClawOpen = false;
    private static boolean isSensorActive = false;

    private int debounceCounter = 0;
    private static final int DEBOUNCE_THRESHOLD = 40;

    public Claw(OpMode opMode) {
        driver1 = opMode.gamepad1;
        driver2 = opMode.gamepad2;
            colorSensor = opMode.hardwareMap.get(ColorRangeSensor.class, "sensor");
        clawServo = opMode.hardwareMap.get(Servo.class, "claw");

        clawServo.setDirection(Servo.Direction.FORWARD);
        clawServo.setPosition(CLOSED_POSITION);
    }

    public void teleOp() {
        handleToggle();

        // Automatic closing if sensor detects object within 1 cm and claw is open
        if (isSensorActive && colorSensor.getDistance(DistanceUnit.CM) < 4) {
            setClawClosed();
        }
    }

    private void handleToggle() {
        if (debounceCounter > DEBOUNCE_THRESHOLD) {
            if (driver2.right_trigger >= 0.1) {
                toggleClaw();
                debounceCounter = 0;
                isSensorActive=false;
            }
        } else {
            debounceCounter++;
            isSensorActive=true;
        }
        while (driver2.right_trigger>=0.1){
            isSensorActive=false;
        }
    }

    private void toggleClaw() {
        if (isClawOpen) {
            setClawClosed();
            isSensorActive = false;
        } else {
            setClawOpen();
            isSensorActive = true;
        }
    }

    public void setClawClosed() {
        clawServo.setPosition(CLOSED_POSITION);
        isClawOpen = false;
    }

    public void setClawOpen() {
        clawServo.setPosition(OPEN_POSITION);
        isClawOpen = true;
    }
}
