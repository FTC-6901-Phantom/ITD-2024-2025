package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
public class Arm {
    private static Servo LeftServo;
    private static Servo RightServo;
    private final Gamepad Driver2;
    private static Gamepad Driver1 = null;
    private static double scorePosition = 0;
    private static double intakePosition = 0.22;

    public Arm(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
        LeftServo = (Servo) opMode.hardwareMap.get("LeftClaw");
        RightServo = (Servo) opMode.hardwareMap.get("RightClaw");


        LeftServo.setDirection(Servo.Direction.FORWARD);
        RightServo.setDirection(Servo.Direction.REVERSE);
        clawServo(scorePosition, scorePosition);
//        opMode.time
    }

    public static void teleOp() throws InterruptedException {
        if (Driver1.b) clawServo(scorePosition, scorePosition);
        else if (Driver1.x) clawServo(intakePosition, intakePosition);

        //for(int i=0; i<=7;i++){
           // wait();
        }



    public static void clawServo(double setPositionRight, double setPositionLeft) {
        RightServo.setPosition(setPositionRight);
        LeftServo.setPosition(setPositionLeft);
    }
}
