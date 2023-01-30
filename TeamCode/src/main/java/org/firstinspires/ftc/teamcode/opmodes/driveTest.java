package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class driveTest extends OpMode {

    SampleMecanumDrive sampleMecanumDrive;

    @Override
    public void init() {
        sampleMecanumDrive = new SampleMecanumDrive(this);
    }

    @Override
    public void loop() {
        sampleMecanumDrive.buttonDrive();
    }
}
