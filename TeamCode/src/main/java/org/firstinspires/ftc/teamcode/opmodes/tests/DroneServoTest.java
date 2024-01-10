package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.DroneLaunch;

@TeleOp
public class DroneServoTest extends OpMode {

    DroneLaunch drone;

    @Override
    public void init(){
        drone = new DroneLaunch(this);
    }

    @Override
    public void loop(){
        drone.testCommand();
    }
}
