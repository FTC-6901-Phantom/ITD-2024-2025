package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class slide {

    public static double POWER = 1;
    public static int High = 1850;
    public static int Mid = 1450;
    public static int Low = 1100;
    public static int RESET = 0;
    public static int MANUAL_MOVE_SPEED = 20;
    private int position = 0;

    private final DcMotor slideLeft;
    private final DcMotor slideRight;

    private final HardwareMap hardwareMap;
    private final Gamepad Driver2;
    private final Gamepad Driver1;
    private final Telemetry telemetry;

    public slide(OpMode opMode) {
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

    public void teleOp(){
        if(Driver1.y) moveHigh();
        else if (Driver1.x) moveMid();
        else if (Driver1.b) moveLow();
        else if(Driver1.a) Reset();
        else if (Driver1.dpad_up) moveMotors(position + MANUAL_MOVE_SPEED);
        else if (Driver1.dpad_down) moveMotors(position - MANUAL_MOVE_SPEED);
        telemetry.addData("SlidePos",slideLeft.getCurrentPosition());
        telemetry.addData("SlidePos",slideRight.getCurrentPosition());
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
}

