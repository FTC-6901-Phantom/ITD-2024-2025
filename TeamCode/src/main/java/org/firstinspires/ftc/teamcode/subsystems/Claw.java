package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    public static double INITIAL_POSITION = 0;
    public static double CHANGE_AMOUNT = 0.0003;
    public static double CLOSED = 0.07;
    public static double OPEN = 0;
    public static double UP;
    public static double DOWN;

    private double clawPosition = INITIAL_POSITION;

    //create hardware variables
//    private final Servo box;
//    private final Servo claw1;
//    private final Servo claw2;


    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Gamepad gamepad2;
    private final Telemetry telemetry;

    //Constructer
    public Claw(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
        telemetry = opMode.telemetry;

//        claw1 = hardwareMap.get(Servo.class, "claw1");
//        claw2 = hardwareMap.get(Servo.class, "claw2");
//        box = hardwareMap.get(Servo.class, "box");
    }

    public void teleOpCommand() {

        if(gamepad2.left_bumper) openClaw();
        if(gamepad2.right_bumper) closeClaw();
    }

    public void openClaw(){
//        claw1.setPosition(OPEN);
//        claw2.setPosition(OPEN);
    }

    public void closeClaw(){
//        claw1.setPosition(CLOSED);
//        claw2.setPosition(CLOSED);
    }

    public void boxUp(){
//        box.setPosition(UP);
    }

    public void boxDown(){
//        box.setPosition(DOWN);
    }

    public void testCommand(){
        if(gamepad1.a){
            clawPosition += CHANGE_AMOUNT;
        }
        else if(gamepad1.b){
            clawPosition -= CHANGE_AMOUNT;
        }
     //   claw.setPosition(clawPosition);
        telemetry.addData("claw position", clawPosition);
    }
}