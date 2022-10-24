package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {

    public static double POWER = 0.5;
    public static int UP = 150;
    public static int DOWN = 0;

    private final DcMotor leftSlide;
    private final DcMotor rightSlide;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;

    public Slide(OpMode opMode){
        gamepad1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;

        leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");

        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void teleOpCommand(){
        if (gamepad1.dpad_down) {
           moveMoters(DOWN);
        }
        else if (gamepad1.dpad_up) {
           moveMoters(UP);
        }
    }

    public void moveMoters(int position){
        leftSlide.setTargetPosition(position);
        rightSlide.setTargetPosition(position);
        leftSlide.setPower(POWER);
        rightSlide.setPower(POWER);
    }
}
