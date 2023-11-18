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
    public static double CLOSED = 0.38;
    public static double OPEN = 0.69;


    private double clawPosition = INITIAL_POSITION;

    //create hardware variables
   private final Servo clawOne;
   private final CRServo clawTwo;


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

        clawOne = hardwareMap.get(Servo.class, "clawOne");
        clawTwo = hardwareMap.get(CRServo.class, "clawTwo");
        clawOne.setDirection(Servo.Direction.FORWARD);
        clawTwo.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void teleOpCommand() {
        if(gamepad1.right_bumper) openClaw();
    }

    public void openClaw(){
        clawOne.setPosition(OPEN);
    }


}


//    public void testCommand(){
//
//        if(gamepad1.a){
//            clawPosition += CHANGE_AMOUNT;
//        }
//        else if(gamepad1.b){
//            clawPosition -= CHANGE_AMOUNT;
//        }
//        claw.setPosition(clawPosition);
//        telemetry.addData("claw position", clawPosition);
//    }
//}