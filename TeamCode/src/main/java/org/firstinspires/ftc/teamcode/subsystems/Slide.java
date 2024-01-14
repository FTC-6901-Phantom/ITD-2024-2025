package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public static double POWER = 1;
    public static int SCORE = 1778;
    public static int RESET = 0;
    public static int MANUAL_MOVE_SPEED = 7;
    private int position = 0;

    private final DcMotor slideLeft;
    private final DcMotor slideRight;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Gamepad gamepad2;
    private final Telemetry telemetry;

    public Slide(OpMode opMode) {
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        slideLeft = hardwareMap.get(DcMotor.class,"leftSlide");
        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideRight = hardwareMap.get(DcMotor.class, "rightSlide");
        slideRight.setDirection(DcMotorSimple.Direction.FORWARD);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideLeft.setTargetPosition(0);
        slideRight.setTargetPosition(0);

        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    public void teleOpCommand() {
        if(gamepad2.dpad_up) Score();

        if(gamepad2.dpad_down) Reset();

//        if (gamepad2.y) moveMotors(position + MANUAL_MOVE_SPEED);
//        if (gamepad1.a) moveMotors(position - MANUAL_MOVE_SPEED);

        if (gamepad2.right_stick_y < -0.3) moveMotors(position + MANUAL_MOVE_SPEED);

        if (gamepad2.right_stick_y > 0.3) moveMotors(position - MANUAL_MOVE_SPEED);

        telemetry.addData("slide position", position);

    }

    public void Score(){
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(SCORE);

        slideRight.setPower(1);
        slideRight.setTargetPosition(SCORE);
    }

    public void Reset(){
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(RESET);

        slideRight.setPower(1);
        slideRight.setTargetPosition(RESET);
    }
    public void moveMotors(int position){
        this.position = position;
        slideLeft.setTargetPosition(position);
        slideRight.setTargetPosition(position);
        slideLeft.setPower(POWER);
        slideRight.setPower(POWER);
    }

    public void testCommand(){
        if (gamepad2.right_stick_y < -0.3) moveMotors(position + MANUAL_MOVE_SPEED);

        if (gamepad2.right_stick_y > 0.3) moveMotors(position - MANUAL_MOVE_SPEED);
        telemetry.addData("slide position", position);
    }


}
