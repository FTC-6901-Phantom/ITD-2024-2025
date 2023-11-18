package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {

    public static double POWER = 1;

    public static int MANUAL_MOVE_SPEED = 7;

    private int position = 0;

    private final DcMotor intakeMotor;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Telemetry telemetry;

    public Intake(OpMode opMode) {
        gamepad1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void teleOpCommand() {
        if(gamepad1.a) intakeMotor.setPower(1);
        else intakeMotor.setPower(0);

    }
}
