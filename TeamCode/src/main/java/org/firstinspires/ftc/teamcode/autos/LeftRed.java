package org.firstinspires.ftc.teamcode.autos;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;

@Config
@Autonomous(name = "LeftRed", group = "Autonomous")
public class LeftRed extends LinearOpMode {

                @Override
                public void runOpMode() {
                    MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-33, -63, Math.toRadians(90)));
                    Claw claw = new Claw(this);
                    Slide slides = new Slide(this);
                    Wrist wrist = new Wrist(this);
                    Arm arm = new Arm(this);

                    waitForStart();
                    Action trajectoryAction1;
                    trajectoryAction1 = drive.actionBuilder(drive.pose)
                            .setTangent(0)
                            .splineToConstantHeading(new Vector2d(-8, -40), Math.PI / 2)
                            .waitSeconds(2)
                            .strafeTo(new Vector2d(-48, -42))
                            .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(-135)), Math.PI / 2)
                            .waitSeconds(1)
                            .splineTo(new Vector2d(-58, -42), Math.PI / 2)
                            .waitSeconds(2)
                            .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(-135)), Math.PI / 2)
                            .waitSeconds(2)
                            .setTangent(Math.toRadians(90))
                            .lineToYLinearHeading(-26, Math.toRadians(180))
                            .waitSeconds(2)
                            .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(-135)), Math.PI / 2)
                            .waitSeconds(2)
                            .setTangent(Math.toRadians(355))
                            .lineToXLinearHeading(36, Math.toRadians(90))
                            .build();

                    // actions that need to happen on init; for instance, a claw tightening.
                    //Actions.runBlocking(claw.setcloseClaw());

//                    Actions.runBlocking(
//                            new SequentialAction(
//                                    lift.liftUp(),
//                                    claw.openClaw(),
//                                    lift.liftDown()
//                            )
//                    );
                }
            }