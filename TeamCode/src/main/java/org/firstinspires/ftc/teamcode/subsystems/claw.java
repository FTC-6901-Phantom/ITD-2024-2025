package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com .qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class claw {
    private static final double openClaw = 0;
    private static Servo leftClaw;
    private static Servo rightClaw;
    public static ColorRangeSensor leftSensor;
    public static ColorRangeSensor rightSensor;
    private static Gamepad Driver1;
    static boolean sensorWork;

    public claw(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        leftClaw = (Servo) opMode.hardwareMap.get("leftClaw");
        rightClaw = (Servo) opMode.hardwareMap.get("rightClaw");
        leftSensor = (ColorRangeSensor) opMode.hardwareMap.get("leftSensor");
        rightSensor = (ColorRangeSensor) opMode.hardwareMap.get("rightSensor");
        leftClaw.setDirection(Servo.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.FORWARD);
        clawServo(openClaw, openClaw);
    }

    public static void TeleOp() {
        if (Driver1.right_trigger >= 0.1) {
            rightClaw.setPosition(openClaw);
            sensorWork = false;
        }
        else if (Driver1.left_trigger >= 0.1){
            leftClaw.setPosition(openClaw);
            sensorWork=false;
        } else {
            sensorWork=true;}

        double closeClaw = 0.14;
        if (Driver1.left_bumper) {
            clawServo(closeClaw, closeClaw);
        }
        if (sensorWork){
            double distanceThreshold = 3;
            if(leftSensor.getDistance(DistanceUnit.CM) < distanceThreshold) {
                leftClaw.setPosition(closeClaw);}
            if(rightSensor.getDistance(DistanceUnit.CM) < distanceThreshold) {
                rightClaw.setPosition(closeClaw);}
        }
    }
    public static void clawServo(double setPositionRight, double setPositionLeft) {
        rightClaw.setPosition(setPositionRight);
        leftClaw.setPosition(setPositionLeft);
    }}