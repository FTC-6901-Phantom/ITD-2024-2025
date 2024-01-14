package org.firstinspires.ftc.teamcode.subsystems;

import android.graphics.Color;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com .qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Claw {
    private static double rightcloseclaw = 0.169;
    private static double leftcloseclaw = 0.19;
    private static double openClaw = 0;
    private static double distanceThreshold = 3;
    private static Servo LeftClaw;
    private static Servo RightClaw;
    private static ColorRangeSensor leftSensor;
    private static ColorRangeSensor rightSensor;
    private final Gamepad Driver2;
    private static Gamepad Driver1;

    public Claw(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
        LeftClaw = (Servo) opMode.hardwareMap.get("LeftClaw");
        RightClaw = (Servo) opMode.hardwareMap.get("RightClaw");
        leftSensor = (ColorRangeSensor) opMode.hardwareMap.get("LeftSensor");
        rightSensor = (ColorRangeSensor) opMode.hardwareMap.get("rightSensor");
        LeftClaw.setDirection(Servo.Direction.REVERSE);
        RightClaw.setDirection(Servo.Direction.FORWARD);
        clawServo(openClaw, openClaw);
    }

    public static void teleOp() {
        if (Driver1.right_trigger >= 0.1){ clawServo(rightcloseclaw, leftcloseclaw);}
        else if (Driver1.left_trigger >= 0.1) clawServo(openClaw, openClaw);

        if (leftSensor.getDistance(DistanceUnit.MM) < distanceThreshold) {
            LeftClaw.setPosition(leftcloseclaw);
        } else {
            LeftClaw.setPosition(openClaw);
        }

        if (rightSensor.getDistance(DistanceUnit.MM) < distanceThreshold) {
            RightClaw.setPosition(rightcloseclaw);
        } else {
            RightClaw.setPosition(openClaw);
        }
    }

    public static void clawServo(double setPositionRight, double setPositionLeft) {
        RightClaw.setPosition(setPositionRight);
        LeftClaw.setPosition(setPositionLeft);
    }
}