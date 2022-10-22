package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem{
    //values

    // create hardware variables
    private Servo clawLeft;
    private Servo clawRight;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad2;

    //Constructer
    public ClawSubsystem(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        gamepad2 = opMode.gamepad2;

    }

    //Initialize hardware variables
    public void init() {
        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        clawLeft.setDirection(Servo.Direction.REVERSE);
        clawRight.setDirection(Servo.Direction.FORWARD);
    }
    public void teleOpCommand() {

        //close
        if (gamepad2.right_bumper){
            clawLeft.setPosition(0);
            clawRight.setPosition(0);
        }
        //open
        if (gamepad2.left_bumper){
            clawLeft.setPosition(0.22);
            clawRight.setPosition(0.17);
        }
    }
}