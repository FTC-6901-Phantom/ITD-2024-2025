package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmSubsystem {
    //set positions
    public static double ARM_UP = .4;
    public static double ARM_DOWN = 0.2;

    //create hardware variables
    private Servo armLeft;
    private Servo armRight;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad2;


    //Constructer
    public ArmSubsystem(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        gamepad2 = opMode.gamepad2;
    }

    //Initialize hardware variables
    public void init() {
        armLeft = hardwareMap.get(Servo.class, "armLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");
        armLeft.setDirection(Servo.Direction.REVERSE);
        armRight.setDirection(Servo.Direction.FORWARD);


    }

    public void teleOpCommand() {
        //up
        if (gamepad2.a){
            armLeft.setPosition(ARM_UP);
            armRight.setPosition(ARM_UP);
        }
        //down
        if (gamepad2.b){
            armLeft.setPosition(ARM_DOWN);
            armRight.setPosition(ARM_DOWN);
        }
        armLeft.setPosition(ARM_UP);
        armRight.setPosition(ARM_UP);
        armLeft.setPosition(ARM_DOWN);
        armRight.setPosition(ARM_DOWN);
    }
}
