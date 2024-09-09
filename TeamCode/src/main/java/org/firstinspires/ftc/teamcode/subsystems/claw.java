package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class claw {
    public final double openClaw = 0;
    private static Servo clawServo;
    private static Gamepad Driver1;
    public  final double closeClaw= 0.14;

    public claw(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        clawServo = (Servo) opMode.hardwareMap.get("leftClaw");
        clawServo.setDirection(Servo.Direction.REVERSE);
        clawServo(closeClaw, closeClaw);
    }

    public  void teleOp() {
        if (Driver1.left_trigger >= 0.1){
            clawServo.setPosition(openClaw);}

        if (Driver1.right_trigger >= 0.1) {
            clawServo(closeClaw, closeClaw);
        }
    }
    public  void clawServo(double setPositionRight, double setPositionLeft) {
        clawServo.setPosition(setPositionLeft);
    }}
