package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    //values

    // create hardware variables
    private final Servo clawLeft;
    private final Servo clawRight;

    public static double CLOSED;
    public static double OPEN;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    private final Telemetry telemetry;

    //Constructer
    public Claw(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        gamepad2 = opMode.gamepad2;
        gamepad1 = opMode.gamepad1;
        telemetry = opMode.telemetry;

        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        clawLeft.setDirection(Servo.Direction.REVERSE);
        clawRight.setDirection(Servo.Direction.FORWARD);

    }

    public void teleOpCommand() {

        if (gamepad2.right_bumper){
            clawLeft.setPosition(CLOSED);
            clawRight.setPosition(CLOSED);
        }
        if (gamepad2.left_bumper){
            clawLeft.setPosition(OPEN);
            clawRight.setPosition(OPEN);
        }
    }

    public static double initialPosition = 0;
    public double leftClawPosition = initialPosition;
    public double rightClawPosition = initialPosition;
    private static final double changePos = 0.0003;
    public void testCommand(){

        if(gamepad1.a){
            leftClawPosition +=changePos;
        }
        else if(gamepad1.b){
            leftClawPosition -=changePos;
        }
        if(gamepad1.x){
            rightClawPosition +=changePos;
        }
        else if(gamepad1.y){
            rightClawPosition -=changePos;
        }
        clawLeft.setPosition(leftClawPosition);
        clawRight.setPosition(rightClawPosition);
        telemetry.addData("left claw position", leftClawPosition);
        telemetry.addData("right claw position", rightClawPosition);
    }
}