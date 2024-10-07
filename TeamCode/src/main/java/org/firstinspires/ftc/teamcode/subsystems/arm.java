package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
public class arm {
    private static Servo leftServo;
    private static Servo rightServo;
    private static Gamepad Driver1;
    private static Gamepad Driver2;
    private static double scorePosition = 0.50;
    private static double intakePosition = 0.04;

    public arm(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        leftServo = (Servo) opMode.hardwareMap.get("leftArm");
        rightServo = (Servo) opMode.hardwareMap.get("rightArm");


        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.REVERSE);
        armServo(scorePosition, scorePosition);
//        opMode.time
    }

    public static void teleOp() throws InterruptedException {
        if (Driver2.a) armServo(intakePosition, intakePosition);
        else if (Driver2.x) armServo(scorePosition,scorePosition);

        //for(int i=0; i<=7;i++){
        // wait();
    }



    public static void armServo(double setPositionRight, double setPositionLeft) {
        rightServo.setPosition(setPositionRight);
        leftServo.setPosition(setPositionLeft);
    }
}