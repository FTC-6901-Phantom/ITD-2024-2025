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
    public static double LOW = 0;
    public static double RESET = 1;
    public static double INITIAL_POSITION = 0;
    public static double CHANGE_POS = 0.0003;

    private double arm_left_position = INITIAL_POSITION;
    private double arm_rigth_position = INITIAL_POSITION;


    //create hardware variables
    private final Servo armLeft;
    private final Servo armRight;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    private final Telemetry telemetry;


    //Constructer
    public Arm(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        gamepad2 = opMode.gamepad2;
        gamepad1 = opMode.gamepad1;
        telemetry = opMode.telemetry;

        armLeft = hardwareMap.get(Servo.class, "armLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");
        armLeft.setDirection(Servo.Direction.FORWARD);
        armRight.setDirection(Servo.Direction.REVERSE);
    }

    public void teleOpCommand() {
        //up
        if (gamepad2.dpad_up){
            armLeft.setPosition(HIGH);
            armRight.setPosition(HIGH);
        }
        if (gamepad2.dpad_down){
            armLeft.setPosition(RESET);
            armRight.setPosition(RESET);
        }
        if (gamepad2.dpad_left){
            armLeft.setPosition(LOW);
            armRight.setPosition(LOW);
        }
        if (gamepad2.dpad_right){
            armLeft.setPosition(MID);
            armLeft.setPosition(MID);
        }
    }

    public void testCommand() {

        if(gamepad1.a){
            arm_left_position += CHANGE_POS;
        }
        else if(gamepad1.b){
            arm_left_position -= CHANGE_POS;
        }
        if(gamepad1.x){
            arm_rigth_position += CHANGE_POS;
        }
        else if(gamepad1.y){
            arm_rigth_position -= CHANGE_POS;
        }
        armLeft.setPosition(arm_left_position);
        armRight.setPosition(arm_rigth_position);
        telemetry.addData("left claw position", arm_left_position);
        telemetry.addData("right claw position", arm_rigth_position);
    }

    public void init() {
    }
}
