package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com .qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class Claw {
    private static double closeClaw = 0.169;
    private static double openClaw = 0;
    private static double distanceThreshold = 1;
    private static Servo leftClaw;
    private static Servo rightClaw;
    private static ColorRangeSensor leftSensor;
    private static ColorRangeSensor rightSensor;
    private final Gamepad Driver2;
    private static Gamepad Driver1;
    private static OpMode opMode;

    public Claw(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
        leftClaw = (Servo) opMode.hardwareMap.get("leftClaw");
        rightClaw = (Servo) opMode.hardwareMap.get("rightClaw");
        leftSensor = (ColorRangeSensor) opMode.hardwareMap.get("leftSensor");
        rightSensor = (ColorRangeSensor) opMode.hardwareMap.get("rightSensor");
        leftClaw.setDirection(Servo.Direction.FORWARD);
        rightClaw.setDirection(Servo.Direction.REVERSE);
        clawServo(openClaw, openClaw);
        Claw.opMode = opMode;
    }

    public static void teleOp() {
        opMode.telemetry.addData("leftSensor", leftSensor.getDistance(DistanceUnit.CM));
        opMode.telemetry.addData("rightSensor", rightSensor.getDistance(DistanceUnit.CM));
        if (Driver1.right_trigger >= 0.1){ clawServo(closeClaw, closeClaw);}
        else if (Driver1.left_trigger >= 0.1) clawServo(openClaw, openClaw);

        if (leftSensor.getDistance(DistanceUnit.CM) < distanceThreshold) {
            leftClaw.setPosition(closeClaw);
        }

        if (rightSensor.getDistance(DistanceUnit.CM) < distanceThreshold) {
            rightClaw.setPosition(closeClaw);
        }
    }
    public static void clawServo(double setPositionRight, double setPositionLeft) {
        rightClaw.setPosition(setPositionRight);
        leftClaw.setPosition(setPositionLeft);
    }

}