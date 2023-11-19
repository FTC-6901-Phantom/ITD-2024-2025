package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    public static double POWER = 1;
    public static int HIGH = 500;
    public static int MANUAL_MOVE_SPEED = 7;
    private int position = 0;
    private final DcMotor arm;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Telemetry telemetry;

    public Arm(OpMode opMode) {
        gamepad1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //arm.setTargetPosition(0);

        //arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void teleOpCommand() {
        if (gamepad1.dpad_up) arm.setPower(0.6);
        if (gamepad1.dpad_down) arm.setPower(-0.4);
        else arm.setPower(0);
        //telemetry.addData("arm position", position);

    }
    /*
    public void moveMotors(int position){
        this.position = position;
        arm.setTargetPosition(position);
        arm.setPower(POWER);
        }
     */
}
