package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw {
    private final double openClaw = 0;
    private static Servo clawServo;
    private static Gamepad Driver1;
    private static Gamepad Driver2;
    private  final double closeClaw= 0.59;
    static boolean ClawIsOpen;
    int count = 0;

    public Claw(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        clawServo = (Servo) opMode.hardwareMap.get("claw");
        clawServo.setDirection(Servo.Direction.FORWARD);
        clawServo.setPosition(closeClaw);
        ClawIsOpen=false;
    }

    public void teleOp() {
        if (count>40){
            if (Driver2.right_trigger>=0.1){
                if(ClawIsOpen){
                    clawServo.setPosition(closeClaw);
                    ClawIsOpen = false;
                    count =0;
                } else {
                    clawServo.setPosition(openClaw);
                    ClawIsOpen = true;
                    count =0;
                }
            }
        } else{count++;}}

//        if (count>40){
//            if (Driver1.right_trigger>=0.1){
//                if(ClawIsOpen){
//                    clawServo.setPosition(closeClaw);
//                    ClawIsOpen = false;
//                    count =0;
//                } else {
//                    clawServo.setPosition(openClaw);
//                    ClawIsOpen = true;
//                    count =0;
//                }
//            }
//        } else{count++;}

    public void setcloseClaw(){
        clawServo.setPosition(closeClaw);
    }
    public void setOpenClaw(){
        clawServo.setPosition(openClaw);
    }
}
