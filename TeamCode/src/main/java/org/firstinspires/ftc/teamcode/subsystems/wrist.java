package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class wrist {
    public final double intakePos = 0;
    private static Servo wristServo;
    private static Gamepad Driver1;
    public  final double specimenIntake = 0.14;

    public wrist(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        wristServo = (Servo) opMode.hardwareMap.get("Wrist");
        wristServo.setDirection(Servo.Direction.REVERSE);
        wristServo(specimenIntake, specimenIntake);
    }

    public  void teleOp() {
        if (Driver1.left_bumper){
            wristServo.setPosition(intakePos);}

        if (Driver1.right_bumper) {
            wristServo(specimenIntake, specimenIntake);
        }
    }
    public  void wristServo(double setPositionRight, double setPositionLeft) {
        wristServo.setPosition(setPositionLeft);
    }}
