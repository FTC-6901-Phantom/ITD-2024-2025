package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Claw;

@TeleOp
public class ClawServoTest extends OpMode {
    Claw claw;

    @Override
    public void init() {
      claw = new Claw(this);
    }

    @Override
    public void loop() {
       // claw.testCommand();
    }
}