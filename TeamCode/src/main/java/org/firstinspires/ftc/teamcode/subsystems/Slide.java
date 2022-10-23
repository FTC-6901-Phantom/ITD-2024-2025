package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {
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
    }

    public void teleOpCommand(){
        if (gamepad1.dpad_down) {
            leftSlide.setPower(.5);
            leftSlide.setTargetPosition(0);
            rightSlide.setPower(.5);
            rightSlide.setTargetPosition(0);
        }
        //down
        else if (gamepad1.dpad_up) {
            leftSlide.setPower(-.5);
            leftSlide.setTargetPosition(150);
            rightSlide.setPower(-.5);
            rightSlide.setTargetPosition(150);
        }
        else  {
            leftSlide.setPower(0);
            rightSlide.setPower(0);
        }
    }
}
