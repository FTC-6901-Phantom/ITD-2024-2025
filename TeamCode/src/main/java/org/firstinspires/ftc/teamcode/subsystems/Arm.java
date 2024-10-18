package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private static Servo leftServo;
    private static Servo rightServo;
    private static Gamepad Driver1;
    private static Gamepad Driver2;
    private static double scorePosition = 0.50;
    private static double intakePosition = 0.03;
    static boolean ArmIsUp;
    static int Count = 0;

    public Arm(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        leftServo = (Servo) opMode.hardwareMap.get("leftArm");
        rightServo = (Servo) opMode.hardwareMap.get("rightArm");


        leftServo.setDirection(Servo.Direction.FORWARD);
        rightServo.setDirection(Servo.Direction.REVERSE);
        armServo(scorePosition, scorePosition);
        ArmIsUp=true;
    //        opMode.time
    }

    public static void teleOp() throws InterruptedException {
        if (Count>40){
            if (Driver2.a){
                if(ArmIsUp){
                    armServo(intakePosition,intakePosition);
                    ArmIsUp = false;
                    Count =0;
                } else {
                    armServo(scorePosition,scorePosition);
                    ArmIsUp = true;
                    Count =0;
                }
            }
        } else{Count++;}}



    public static void armServo(double setPositionRight, double setPositionLeft) {
        rightServo.setPosition(setPositionRight);
        leftServo.setPosition(setPositionLeft);
    }
    public void ArmUp(){
        armServo(intakePosition,intakePosition);
    }
    public void ArmDown(){
        armServo(scorePosition,scorePosition);
    }
}