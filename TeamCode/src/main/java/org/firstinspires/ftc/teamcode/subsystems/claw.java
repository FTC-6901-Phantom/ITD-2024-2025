package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
public class claw {
    private final double openClaw = 0;
    private static Servo clawServo;
    private static Gamepad Driver1;
    private static Gamepad Driver2;
    private  final double closeClaw= 0.622;

    public claw(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        clawServo = (Servo) opMode.hardwareMap.get("claw");
        clawServo.setDirection(Servo.Direction.FORWARD);
        clawServo(closeClaw, closeClaw);
    }

    public  void teleOp() {
        if (Driver2.left_trigger >= 0.1){
            clawServo.setPosition(openClaw);}

        if (Driver2.right_trigger >= 0.1) {
            clawServo(closeClaw, closeClaw);
        }
    }
    public  void clawServo(double setPositionRight, double setPositionLeft) {
        clawServo.setPosition(setPositionLeft);
    }}
