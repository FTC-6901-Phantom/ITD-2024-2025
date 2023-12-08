package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    //arm positions
    public static double ARM_RESET = 0;
    public static double ARM_DROP = 0;
    public static double CHANGE_AMOUNT = 0.0003;

    public double armPosition = 0;

    private final Telemetry telemetry;
    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Gamepad gamepad2;

    private final Servo arm1;
    private final Servo arm2;

    public Arm(OpMode opMode){
        hardwareMap = opMode.hardwareMap;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
        telemetry = opMode.telemetry;

        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");
        arm1.setDirection(Servo.Direction.FORWARD);
        arm2.setDirection(Servo.Direction.REVERSE);
    }

    public void teleOpCommand(){
        if(gamepad2.a) armReset();
        if(gamepad2.a) armReset();

        if (gamepad2.b) armDrop();
        if (gamepad2.b) armDrop();
    }

    public void armReset(){
        arm1.setPosition(ARM_RESET);
        arm2.setPosition(ARM_RESET);
    }

    public void armDrop(){
        arm1.setPosition(ARM_DROP);
        arm2.setPosition(ARM_DROP);
    }

    public void testCommand(){
        if(gamepad1.a){
            armPosition += CHANGE_AMOUNT;
        }
        else if(gamepad1.b){
            armPosition -= CHANGE_AMOUNT;
        }
        //arm1.setPosition(armPosition);
        telemetry.addData("arm position", armPosition);
    }
}
