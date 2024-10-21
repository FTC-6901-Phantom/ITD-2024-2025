package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Rotator {
    private static Servo rotatorServo;
    private static Gamepad Driver1;
    private  static Gamepad Driver2;

    public  final double vertical = 0.253; //hypothetical values
    public final double horizontal = 0;
    public final double angleleft = 0.380; //hypothetical values
    public final double angleright = 0.126; //hypothetical values

    int count = 0;
    static boolean InitPosOne;
    static boolean InitPosTwo;

    public Rotator(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        rotatorServo = (Servo) opMode.hardwareMap.get("rotator");
        rotatorServo.setDirection(Servo.Direction.REVERSE);
        wristServo(vertical);

    }

    public  void teleOp() {
        if (count>40){
            if (Driver2.right_bumper){
                if(InitPosOne){
                    rotatorServo.setPosition(vertical);
                    InitPosOne = false;
                    count =0;
                } else {
                    rotatorServo.setPosition(horizontal);
                    InitPosOne = true;
                    count =0;
                }
            }
        } else{count++;}

        if (count>40){
            if (Driver2.left_bumper){
                if(InitPosTwo){
                    rotatorServo.setPosition(angleleft);
                    InitPosOne = false;
                    count =0;
                } else {
                    rotatorServo.setPosition(angleright);
                    InitPosTwo = true;
                    count =0;
                }
            }
        } else{count++;}
    }

    public  void wristServo(double setPositionLeft) {
        rotatorServo.setPosition(setPositionLeft);
    }
    public void clawVertical(){
        rotatorServo.setPosition(vertical);
    }
    public void clawHorizonal(){
        rotatorServo.setPosition(horizontal);
    }
    public void clawLeft(){rotatorServo.setPosition(angleleft);}
    public void clawRight(){rotatorServo.setPosition(angleright);}
}