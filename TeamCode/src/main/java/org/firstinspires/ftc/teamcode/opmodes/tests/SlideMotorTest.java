package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Slide;

@TeleOp
public class SlideMotorTest extends OpMode {
    Slide slide;

    @Override
    public void init(){
        slide = new Slide(this);
    }
    @Override
    public void loop() {
        slide.testCommand();
    }
}
