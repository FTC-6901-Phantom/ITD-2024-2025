package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DroneLaunch {
    public static double LAUNCH = 0;

    private final Servo drone;

    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;

    public DroneLaunch(OpMode opMode){
        hardwareMap = opMode.hardwareMap;
        gamepad1 = opMode.gamepad1;

        drone = hardwareMap.get(Servo.class,"drone");
    }

    public void teleOpCommand(){
        if(gamepad1.a){
            drone.setPosition(LAUNCH);
        }
    }
}
