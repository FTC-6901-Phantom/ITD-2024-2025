package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public static double POWER = 0.5;

    public static int HIGH = 2660;
    public static int LOW = 870;
    public static int MID = 1810 ;
    public static int RESET = 0;
    private int position = 0;

    private final DcMotor slideMotor;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad2;
    private final Telemetry telemetry;

    public Slide(OpMode opMode) {
        gamepad2 = opMode.gamepad2;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        slideMotor = hardwareMap.get(DcMotor.class, "slide");

        slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideMotor.setTargetPosition(0);

        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void teleOpCommand() {
        if (gamepad2.dpad_down) moveReset();

        if(gamepad2.dpad_left) moveLow();

        if(gamepad2.dpad_right) moveMid();

        else if (gamepad2.dpad_up) moveHigh();

        if (gamepad2.right_stick_y < -0.3) moveMoters(position + 1);

        if (gamepad2.right_stick_y > 0.3) moveMoters(position - 1);

        telemetry.addData("slide position", position);

    }
    public void moveMoters(int position){
        this.position = position;
        slideMotor.setTargetPosition(position);
        slideMotor.setPower(POWER);
    }

    public void moveHigh() {
        moveMoters(HIGH);
    }
    public void moveMid(){
        moveMoters(MID);
    }
    public void moveLow(){
        moveMoters(LOW);
    }
    public void moveReset(){
        moveMoters(RESET);
    }
}
