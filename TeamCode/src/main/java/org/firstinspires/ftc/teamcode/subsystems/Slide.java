package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public static double POWER = 0.8;

    //Junctions
    public static int HIGH = 3270;
    public static int MID = 2318 ;
    public static int LOW = 582;
    public static int RESET = 0;
    public static int LIL_UP = 200;
    public static int LESS_HIGH = 2900;

    //cone stack
    public static int ONE = 520;
    public static int TWO = 428;
    public static int THREE = 276;

    private int position = 0;

    private final DcMotor slide;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad2;
    private final Telemetry telemetry;

    public Slide(OpMode opMode) {
        gamepad2 = opMode.gamepad2;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        slide = hardwareMap.get(DcMotor.class, "slide");

        slide.setDirection(DcMotorSimple.Direction.REVERSE);

        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slide.setTargetPosition(0);

        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void teleOpCommand() {
        //stack
        if(gamepad2.y) moveOne();

        if(gamepad2.x) moveTwo();

        if(gamepad2.a) moveThree();

        //junction
        if(gamepad2.b) moveReset();

        if(gamepad2.dpad_down) moveLow();

        if(gamepad2.dpad_left) moveMid();

        else if (gamepad2.dpad_up) moveHigh();

        if (gamepad2.right_stick_y < -0.3) moveMoters(position + 1);

        if (gamepad2.right_stick_y > 0.3) moveMoters(position - 1);

        telemetry.addData("slide position", position);

    }
    public void moveMoters(int position){
        this.position = position;
        slide.setTargetPosition(position);
        slide.setPower(POWER);
    }
    //POSITIONS
    //junctions
    public void moveHigh() {
        moveMoters(HIGH);
    }
    public void moveMid(){
        moveMoters(MID);
    }
    public void moveLow(){
        moveMoters(LOW);
    }
    public void moveReset(){
        moveMoters(RESET);
    }
    public void moveUp(){
        moveMoters(LIL_UP);
    }
    public void moveHighLess(){
        moveMoters(LESS_HIGH);
    }

    //cone stack
    public void moveOne() {
        moveMoters(ONE);
    }
    public void moveTwo(){
        moveMoters(TWO);
    }
    public void moveThree(){
        moveMoters(THREE);
    }
}
