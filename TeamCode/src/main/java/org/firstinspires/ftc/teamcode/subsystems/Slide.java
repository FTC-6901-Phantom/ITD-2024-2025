package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public static double POWER = 1;
    public static int HighBasket =1785;
    public static int SpecimenIntake = 250;
    public static int HighRung = 450;
    public static int RESET = 0;
    public static int MANUAL_MOVE_SPEED = 10;
    private int position = 0;

    public final DcMotor slideLeft;
    public final DcMotor slideRight;

    private final HardwareMap hardwareMap;
    private final Gamepad Driver2;
    private final Gamepad Driver1;
    public final Telemetry telemetry;

    public Slide(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        slideLeft = hardwareMap.get(DcMotor.class,"leftSlide");
        slideLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideRight = hardwareMap.get(DcMotor.class, "rightSlide");
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideLeft.setTargetPosition(0);
        slideRight.setTargetPosition(0);

        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }
    public void moveHighBasket(){
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(HighBasket);

        slideRight.setPower(1);
        slideRight.setTargetPosition(HighBasket);
    }
    public void moveToWall() {
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(SpecimenIntake);

        slideRight.setPower(1);
        slideRight.setTargetPosition(SpecimenIntake);

    }
    public void Reset(){
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(RESET);

        slideRight.setPower(1);
        slideRight.setTargetPosition(RESET);
    }
    public void HighRung(){
        slideLeft.setPower(1);
        slideLeft.setTargetPosition(HighRung);

        slideRight.setPower(1);
        slideRight.setTargetPosition(HighRung);
    }
    public void moveMotors(int position){
        this.position = position;
        slideLeft.setTargetPosition(position);
        slideRight.setTargetPosition(position);
        slideLeft.setPower(POWER);
        slideRight.setPower(POWER);

    }

    }