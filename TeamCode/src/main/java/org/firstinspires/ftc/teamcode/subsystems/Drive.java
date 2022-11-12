package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive {

    public static double SLOW_SPEED = 0.3;
    public static double NORMAL_SPEED= 1;

    private final DcMotor leftFront;
    private final DcMotor leftRear;
    private final DcMotor rightFront;
    private final DcMotor rightRear;

    private double speed = NORMAL_SPEED;

    private final Gamepad gamepad1;
    private final HardwareMap hardwareMap;


    public Drive(OpMode opMode){
        gamepad1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void teleOpCommand() {
        if (gamepad1.right_bumper) {
            speed = SLOW_SPEED;
        } else {
            speed = NORMAL_SPEED;
        }
        leftFront.setPower((-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x) * speed);
        leftRear.setPower((-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x) * speed);
        rightRear.setPower((-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x) * speed);
        rightFront.setPower((-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x) * speed);
    }
}
