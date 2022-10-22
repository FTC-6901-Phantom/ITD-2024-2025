package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {

    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;

    private final Gamepad gamepad1;
    private final HardwareMap hardwareMap;


    public DriveSubsystem(OpMode opMode){
        gamepad1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;

    }
    public void init(){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void teleOpCommand(){
        leftFront.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x);
        leftRear.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x);
        rightRear.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x);
        rightFront.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);
    }
}
