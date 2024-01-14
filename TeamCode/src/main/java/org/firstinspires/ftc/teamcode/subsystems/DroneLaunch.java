package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DroneLaunch {
    public static double LAUNCH = 0.5;
    public static double RESET = 0;
    private double dronePosition = 0.5;
    public static double CHANGE_AMOUNT = 0.0003;

//    private final Servo drone;
    private final HardwareMap hardwareMap;
    private final Gamepad gamepad1;
    private final Telemetry telemetry;

    public DroneLaunch(OpMode opMode){
        hardwareMap = opMode.hardwareMap;
        gamepad1 = opMode.gamepad1;
        telemetry = opMode.telemetry;

//        drone = hardwareMap.get(Servo.class,"drone");
    }

    public void teleOpCommand(){
        if(gamepad1.x){
//            drone.setPosition(LAUNCH);
        }
        if(gamepad1.y){
//            drone.setPosition(RESET);
        }
    }
    public void testCommand(){
        if(gamepad1.a){
            dronePosition += CHANGE_AMOUNT;
        }
        else if(gamepad1.b){
            dronePosition -= CHANGE_AMOUNT;
        }
//        drone.setPosition(dronePosition);
        telemetry.addData("drone position", dronePosition);
    }
}
