//package org.firstinspires.ftc.teamcode.subsystems.lessons;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//
//public class Claw2 {
//    public static double OPEN = 0.5;
//    public static double CLOSE = 1;
//
//    private final Servo claw;
//
//
//    private final HardwareMap hardwareMap;
//    private final Gamepad gamepad1;
//
//    public Claw2(OpMode opMode){
//        gamepad1 = opMode.gamepad1;
//        hardwareMap = opMode.hardwareMap;
//
//        claw = hardwareMap.get(Servo.class, "claw");
//    }
//
//    public void TeleOp(){
//        if(gamepad1.a){
//            claw.setPosition(OPEN);
//        }
//
//    }
//}
