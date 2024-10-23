package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public static double POWER = 1;
    public static int HighBasket =3900;
    public static int LowBasket = 2200;
    public static int HighRung = 1000;
    public static int RESET = 0;
    public static int MANUAL_MOVE_SPEED = 10;
    private int position = 0;

    public final DcMotor slideLeft;
    public final DcMotor slideRight;

    PIDFController Controller = new PIDFController(0,0,0,0); //tune kp

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

        Controller.setTolerance(2);
    }
    public void setsetpoint(double setpoint){
        Controller.setSetPoint(setpoint);
    }

    public void teleOp(){
        if(Driver2.dpad_up) setsetpoint(HighBasket);
        else if (Driver2.dpad_left) setsetpoint(LowBasket);
        else if (Driver2.dpad_right) setsetpoint(HighRung);
        else if(Driver2.dpad_down) setsetpoint(RESET);
//        else if (Driver2.left_bumper) moveMotors(position + MANUAL_MOVE_SPEED);
//        else if (Driver2.right_bumper) moveMotors(position - MANUAL_MOVE_SPEED);

        slideLeft.setPower(Controller.calculate(slideLeft.getCurrentPosition()));
        slideRight.setPower(-Controller.calculate(slideRight.getCurrentPosition()));

        // Add telemetry data
        telemetry.addData("Slide Position", slideLeft.getCurrentPosition());
        telemetry.update();

//        if (Driver2.share) {
//            slideLeft.rese
//        }
    }
    public void moveMotors(int position){
        this.position = position;
        slideLeft.setTargetPosition(position);
        slideRight.setTargetPosition(position);
        slideLeft.setPower(Controller.calculate(slideLeft.getCurrentPosition()));
        slideRight.setPower(-Controller.calculate(slideLeft.getCurrentPosition()));

    }
    public void SlidesHigh() {
        setsetpoint(HighBasket);
        slideLeft.setPower(Controller.calculate(slideLeft.getCurrentPosition()));
        slideRight.setPower(-Controller.calculate(slideLeft.getCurrentPosition()));
    }
    public void ResetSlides() {
        setsetpoint(RESET);
        slideLeft.setPower(Controller.calculate(slideLeft.getCurrentPosition()));
        slideRight.setPower(-Controller.calculate(slideLeft.getCurrentPosition()));
    }
}

