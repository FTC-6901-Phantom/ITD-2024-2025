package org.firstinspires.ftc.teamcode.subsystems.lessons;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Intake2 {
    private final DcMotor intakeMotor;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;

    public Intake2(OpMode opMode){
        gamepad1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        intakeMotor = hardwareMap.get(DcMotor.class, "Name");

        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
