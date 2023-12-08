package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    //for motors
    //public static double POWER = 1;
    //public static int MANUAL_MOVE_SPEED = 7;
    //private int position = 0;

    private final CRServo intake;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Telemetry telemetry;

    public Intake(OpMode opMode) {
        gamepad1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        intake = hardwareMap.get(CRServo.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void teleOpCommand() {
        if(gamepad1.left_bumper) intake.setPower(1);
        if(gamepad1.right_bumper) intake.setPower(-1);
        else intake.setPower(0);
    }
}
