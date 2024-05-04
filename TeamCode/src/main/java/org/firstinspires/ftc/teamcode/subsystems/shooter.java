package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
public class shooter {
    private static Servo shooterServo;
    private static Gamepad Driver1;
    private static double init = 0;
    private static double shoot = 0.40;

    public shooter(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        shooterServo = (Servo) opMode.hardwareMap.get("shooter");
        shooterServo.setDirection(Servo.Direction.REVERSE);
//        opMode.time
    }

    public static void teleOp() throws InterruptedException {
        if (Driver1.dpad_right) shooterServo.setPosition(shoot);
    }
}

