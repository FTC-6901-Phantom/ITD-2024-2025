package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Wrist {
    private static Servo wristServo;
    private static Gamepad Driver1;
    private  static Gamepad Driver2;

    public  final double travel = 0.4;
    public final double outtake = 0.7894;
    private final double specimen = .0328;

    int count = 0;
    static boolean WristUp;

    public Wrist(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        Driver2 = opMode.gamepad2;
        wristServo = (Servo) opMode.hardwareMap.get("Wrist");
        wristServo.setDirection(Servo.Direction.FORWARD);
        wristServo.setPosition(travel);

    }

    public  void teleOp() {
        if (count>40){
            if (Driver2.b){
                if(WristUp){
                    wristServo.setPosition(travel);
                    WristUp = false;
                    count =0;
                } else {
                    wristServo.setPosition(outtake);
                    WristUp = true;
                    count =0;
                }
            }
        } else{count++;}

        if (Driver2.dpad_down){
    wristServo.setPosition(travel);
    }
        if (Driver2.dpad_up){
            wristServo.setPosition(travel);
        }
        if (Driver2.dpad_left){
            wristServo.setPosition(travel);
        }
        if (Driver2.dpad_right){
            wristServo.setPosition(specimen);
        }
    }

    public void WristScore(){
        wristServo.setPosition(outtake);
    }
    public void Travel(){
        wristServo.setPosition(travel);
    }
    public void  Specimen(){wristServo.setPosition(specimen);}
}