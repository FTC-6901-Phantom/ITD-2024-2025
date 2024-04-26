package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public static double POWER = 1;
    public static int High = 1500;
    public static int Mid = 1000;
    public static int Low = 500;
    public static int RESET = 0;
    public static int MANUAL_MOVE_SPEED = 10;
    private int position = 0;

    private final DcMotor slideLeft;
    private final DcMotor slideRight;

    private final HardwareMap hardwareMap;
    private final Gamepad Driver2;
    private final Gamepad Driver1;
    private final Telemetry telemetry;

    public Slide(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
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

    public void teleOp() {
        if(Driver2.dpad_up) moveHigh();
        else if (Driver2.dpad_left) moveMid();
        else if (Driver2.dpad_right) moveLow();
        else if(Driver2.dpad_down) Reset();
        else if (Driver2.right_stick_y < -0.3) moveMotors(position + MANUAL_MOVE_SPEED);
        else if (Driver2.right_stick_y > 0.3) moveMotors(position - MANUAL_MOVE_SPEED);

        telemetry.addData("slide position", position);

    }

    public void moveHigh(){
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(High);

        slideRight.setPower(1);
        slideRight.setTargetPosition(High);
    }
    public void moveMid() {
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(Mid);

        slideRight.setPower(1);
        slideRight.setTargetPosition(Mid);

    }
    public void moveLow() {
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(Low);

        slideRight.setPower(1);
        slideRight.setTargetPosition(Low);

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
        if (Driver1.right_stick_y < -0.3) moveMotors(position + MANUAL_MOVE_SPEED);

        if (Driver1.right_stick_y > 0.3) moveMotors(position - MANUAL_MOVE_SPEED);
        telemetry.addData("slide position", position);
    }


}
