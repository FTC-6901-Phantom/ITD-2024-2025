package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public static double POWER = 1;
    public static int SCORE = 500;
    public static int RESET = 0;
    public static int MANUAL_MOVE_SPEED = 7;
    private int position = 0;

    private final DcMotor slideOne;
    private final DcMotor slideTwo;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Gamepad gamepad2;
    private final Telemetry telemetry;

    public Slide(OpMode opMode) {
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        slideOne = hardwareMap.get(DcMotor.class,"slideOne");
        slideOne.setDirection(DcMotorSimple.Direction.FORWARD);
        slideOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideTwo = hardwareMap.get(DcMotor.class, "slideTwo");
        slideTwo.setDirection(DcMotorSimple.Direction.FORWARD);
        slideTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideOne.setTargetPosition(0);
        slideTwo.setTargetPosition(0);

        slideOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    public void teleOpCommand() {
        if(gamepad2.x) Score();

        if(gamepad2.y) Reset();
    }

    public void Score(){
        slideOne.setPower(1);
        slideOne.setTargetPosition(SCORE);

        slideTwo.setPower(1);
        slideTwo.setTargetPosition(SCORE);
    }

    public void Reset(){
        slideOne.setPower(1);
        slideOne.setTargetPosition(RESET);

        slideTwo.setPower(1);
        slideTwo.setTargetPosition(RESET);
    }
    public void moveMotors(int position){
        this.position = position;
        slideOne.setTargetPosition(position);
        slideTwo.setTargetPosition(position);
        slideOne.setPower(POWER);
        slideTwo.setPower(POWER);
        }

}
