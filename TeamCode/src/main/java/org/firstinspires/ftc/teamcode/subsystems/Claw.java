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
    private final CRServo clawOne;
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

        clawOne = hardwareMap.get(CRServo.class, "clawOne");
        clawTwo = hardwareMap.get(CRServo.class, "clawTwo");
        clawOne.setDirection(CRServo.Direction.FORWARD);
        clawTwo.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void teleOpCommand() {
        if (gamepad2.right_bumper) intake();
        if (gamepad2.left_bumper) outake();
    }

    public void intake() {
        clawOne.setPower(1);
        clawTwo.setPower(-1);
    }

    public void outake() {
        clawOne.setPower(-1);
        clawTwo.setPower(1);
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