package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.subsystems.slide;

@Config
public class wrist {
    public final double Outtake = 0.74;
    private static Servo wristServo;
    private static Gamepad Driver1;
    private  static Gamepad Driver2;
    public  final double specimenIntake = 0.215;

    public wrist(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        wristServo = (Servo) opMode.hardwareMap.get("Wrist");
        wristServo.setDirection(Servo.Direction.REVERSE);
        wristServo(specimenIntake, specimenIntake);
    }

    public  void teleOp() {
        if (Driver2.b){
            wristServo.setPosition(Outtake);}

        if (Driver2.y) {
            wristServo(specimenIntake, specimenIntake);}
    }
    public  void wristServo(double setPositionRight, double setPositionLeft) {
        wristServo.setPosition(setPositionLeft);
    }}
