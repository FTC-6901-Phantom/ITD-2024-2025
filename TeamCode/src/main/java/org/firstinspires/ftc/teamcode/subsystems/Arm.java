package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    //set positions
    public static double HIGH = 0.55;
    public static double MID = 0;
    public static double LOW = 0.5;
    public static double RESET = 1;
    public static double INITIAL_POSITION = 0;
    public static double CHANGE_POS = 0.0003;

    private double arm_left_position = INITIAL_POSITION;
    
    private double arm_right_position = INITIAL_POSITION;


    //create hardware variables
//    private final Servo armLeft;
//    private final Servo armRight;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    private final Telemetry telemetry;


    //    //Constructer
    public Arm(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        gamepad2 = opMode.gamepad2;
        gamepad1 = opMode.gamepad1;
        telemetry = opMode.telemetry;

//        armLeft = hardwareMap.get(Servo.class, "armLeft");
//        armRight = hardwareMap.get(Servo.class, "armRight");
//        armLeft.setDirection(Servo.Direction.FORWARD);
//        armRight.setDirection(Servo.Direction.REVERSE);
    }

    //teleOp
    public void teleOpCommand() {
        //up
        if (gamepad2.dpad_up) armHigh();
        if (gamepad2.dpad_left) armLow();
        if (gamepad2.dpad_right) armMid();
//        if (gamepad2.dpad_down) armReset();
    }

    //positions
    public void armHigh(){
//        armLeft.setPosition(HIGH);
//        armRight.setPosition(HIGH);
    }
    public void armMid(){
//        armLeft.setPosition(MID);
//        armRight.setPosition(MID);
    }
    public void armLow(){
//        armLeft.setPosition(LOW);
//        armRight.setPosition(LOW);
    }
//    public void armReset(){
//        armLeft.setPosition(RESET);
//    }

    public void testCommand() {

        if (gamepad1.a) {
            arm_left_position += CHANGE_POS;
        } else if (gamepad1.b) {
            arm_left_position -= CHANGE_POS;
        }
        if (gamepad1.x) {
            arm_right_position += CHANGE_POS;
        } else if (gamepad1.y) {
            arm_right_position -= CHANGE_POS;
        }
//        armLeft.setPosition(arm_left_position);
//        armRight.setPosition(arm_right_position);
        telemetry.addData("left claw position", arm_left_position);
        telemetry.addData("right claw position", arm_right_position);
    }
}

