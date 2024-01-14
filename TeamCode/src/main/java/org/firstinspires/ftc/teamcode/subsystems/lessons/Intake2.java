//package org.firstinspires.ftc.teamcode.subsystems.lessons;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class Intake2 {
//    private final HardwareMap hardwareMap;
//    private final Gamepad gamepad1;
//
//    private final DcMotor intake;
//
//    public Intake2(OpMode opMode){
//        hardwareMap = opMode.hardwareMap;
//        gamepad1 = opMode.gamepad1;
//
//        intake = hardwareMap.get(DcMotor.class, "intake");
//
//        intake.setDirection(DcMotorSimple.Direction.FORWARD);
//    }
//
//    public void teleOpCommand(){
//        if (gamepad1.a){
//            intake.setPower(0);
//        }
//
//        if (gamepad1.b){
//            intake.setPower(1);
//        }
//    }
//
//

//}
