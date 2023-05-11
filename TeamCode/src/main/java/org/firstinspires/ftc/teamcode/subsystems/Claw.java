package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    public static double INITIAL_POSITION = 0;
    public double CHANGE_AMOUNT = 0.0003;
    public static double CLOSED = 0.38;
    public static double OPEN = 0.69;


    private double clawPosition = INITIAL_POSITION;

//     create hardware variables
    private final Servo claw;


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

        claw = hardwareMap.get(Servo.class, "claw");
        claw.setDirection(Servo.Direction.FORWARD);
    }

    public void teleOpCommand() {

        if (gamepad2.right_bumper) closeClaw();
        if (gamepad2.left_bumper) openClaw();
        if (gamepad2.b) openClaw();
    }

    public void closeClaw(){
        claw.setPosition(CLOSED);
    }

    public void openClaw(){
        claw.setPosition(OPEN);
    }


    public void testCommand(){

        if(gamepad1.a){
            clawPosition += CHANGE_AMOUNT;
        }
        else if(gamepad1.b){
            clawPosition -= CHANGE_AMOUNT;
        }
        claw.setPosition(clawPosition);
        telemetry.addData("claw position", clawPosition);
    }
}