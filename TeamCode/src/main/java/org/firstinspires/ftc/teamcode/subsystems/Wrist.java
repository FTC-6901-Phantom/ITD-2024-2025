package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Wrist {
    private static Servo wristServo;
    private static Gamepad driver2;
    private static final double SCORE_POSITION = 0.63;
    private static final double INTAKE_POSITION = 0.186;
    private static final double SPECIMEN_POSITION = 0;
    private boolean isScorePosition = true;
    private int debounceCounter = 0;
    private static final int DEBOUNCE_THRESHOLD = 40;
    public Wrist(OpMode opMode) {
        driver2 = opMode.gamepad2;
        wristServo = opMode.hardwareMap.get(Servo.class, "Wrist");
        wristServo.setDirection(Servo.Direction.REVERSE);
        setScorePosition();}
    public void teleOp() {
        handleToggle();
    }
    private void handleToggle() {
        if (debounceCounter > DEBOUNCE_THRESHOLD) {
            if (driver2.b) {
                if (isScorePosition) {
                    setIntakePosition();}
                else {setScorePosition();}
                debounceCounter = 0; // Reset debounce counter after action
            }} else {debounceCounter++;}}
    public void setScorePosition() {
        wristServo.setPosition(SCORE_POSITION);
        isScorePosition = true;}
    public void setIntakePosition() {
        wristServo.setPosition(INTAKE_POSITION);
        isScorePosition = false;}}