package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Climb {
    public final DcMotor LeftClimb;
    public final DcMotor RightClimb;

    private final HardwareMap hardwareMap;
    private final Gamepad Driver2;
    private final Gamepad Driver1;
    public final Telemetry telemetry;

    public Climb(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        LeftClimb = hardwareMap.get(DcMotor.class,"LClimb");
        LeftClimb.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftClimb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RightClimb = hardwareMap.get(DcMotor.class, "RClimb");
        RightClimb.setDirection(DcMotorSimple.Direction.FORWARD);
        RightClimb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LeftClimb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightClimb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void teleOp(){
        if(Driver1.right_bumper) Up();
        else if (Driver1.left_bumper) Down();
        else Stop();}

    public void Up(){
        LeftClimb.setPower(1);
        RightClimb.setPower(1);}
    public void Down() {
        LeftClimb.setPower(-1);
        RightClimb.setPower(-1);
    }
    public void Stop(){
        LeftClimb.setPower(0);
        RightClimb.setPower(0);
    }
    }