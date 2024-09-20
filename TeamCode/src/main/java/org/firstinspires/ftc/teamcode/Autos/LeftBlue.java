package org.firstinspires.ftc.teamcode.Autos;

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

@Config
@Autonomous(name = "LeftRed", group = "Autonomous")
public class LeftBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
    }

    public class Lift {
        private final DcMotorEx rightSlide;
        private final DcMotorEx leftSlide;

        public Lift(HardwareMap hardwareMap) {
            rightSlide = hardwareMap.get(DcMotorEx.class, "liftMotor");
            rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
            leftSlide = hardwareMap.get(DcMotorEx.class, "liftMotor");
            leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightSlide.setPower(0.8);
                    leftSlide.setPower(0.8);
                    initialized = true;
                }

                double pos = rightSlide.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos == 3000.0) {
                    return true;
                } else {
                    rightSlide.setPower(0);
                    leftSlide.setPower(0);
                    return false;
                }
            }
        }
        public Action liftUp() {
            return new LiftUp();
        }

        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightSlide.setPower(-0.8);
                    leftSlide.setPower(-0.8);
                    initialized = true;
                }

                double pos = rightSlide.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 100) {
                    return true;
                } else {
                    rightSlide.setPower(0);
                    leftSlide.setPower(0);
                    return false;
                }
            }
        }
        public Action liftDown(){
            return new LiftDown();
        }
    }

    public class Claw {
        private Servo claw;

        public Claw(HardwareMap hardwareMap) {
            claw = hardwareMap.get(Servo.class, "claw");
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(0.14);
                return false;
            }
        }
        public Action closeClaw() {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(0);
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }
        public class Wrist {
            private Servo wrist;

            public Wrist(HardwareMap hardwareMap) {
                wrist = hardwareMap.get(Servo.class, "wrist");
            }

            public class Score implements Action {
                @Override
                public boolean run(@NonNull TelemetryPacket packet) {
                    wrist.setPosition(0.14);
                    return false;
                }
            }
            public Action score() {
                return new Score();
            }

            public class Intake implements Action {
                @Override
                public boolean run(@NonNull TelemetryPacket packet) {
                    wrist.setPosition(0);
                    return false;
                }
            }
            public Action intake() {
                return new Intake();
            }

            public class Arm {
                private Servo leftArm;
                private Servo rightArm;

                public Arm(HardwareMap hardwareMap) {
                    leftArm = hardwareMap.get(Servo.class, "leftArm");
                    rightArm =  hardwareMap.get(Servo.class, "rightArm");
                }

                public class ScoreArm implements Action {
                    @Override
                    public boolean run(@NonNull TelemetryPacket packet) {
                        leftArm.setPosition(0.14);
                        rightArm.setPosition(0.14);
                        return false;
                    }
                }
                public Action scoreArm() {
                    return new ScoreArm();
                }

                public class IntakeArm implements Action {
                    @Override
                    public boolean run(@NonNull TelemetryPacket packet) {
                        leftArm.setPosition(0);
                        rightArm.setPosition(0);
                        return false;
                    }
                }
                public Action intakeArm() {
                    return new IntakeArm();
                }


            public void runOpMode() {
                MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-33, -63, Math.PI/2));
                Claw claw = new Claw(hardwareMap);
                Lift lift = new Lift(hardwareMap);
                Wrist wrist = new Wrist(hardwareMap);
                Arm arm = new Arm(hardwareMap);



                Action trajectoryAction1;
                trajectoryAction1 = drive.actionBuilder(drive.pose)
                        .setTangent(180)
                        .splineToConstantHeading(new Vector2d(8, 40), -Math.PI / 2)
                        .waitSeconds(2)
                        .strafeTo(new Vector2d(48, 42))
                        .waitSeconds(2)
                        .splineToLinearHeading(new Pose2d(52,52, Math.toRadians(45)), -Math.PI/2)
                        .waitSeconds(2)
                        .splineTo(new Vector2d(58, 42), -Math.PI / 2)
                        .waitSeconds(2)
                        .splineToLinearHeading(new Pose2d(52,52, Math.toRadians(45)), -Math.PI/2)
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(270))
                        .lineToYLinearHeading (26,Math.toRadians(0))
                        .waitSeconds(2)
                        .splineToLinearHeading(new Pose2d(52,52, Math.toRadians(45)), -Math.PI/2)
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(175))
                        .lineToXLinearHeading (-36,Math.toRadians(270))
                        .build();

                // actions that need to happen on init; for instance, a claw tightening.
                Actions.runBlocking(claw.closeClaw());

                Actions.runBlocking(
                        new SequentialAction(
                                lift.liftUp(),
                                claw.openClaw(),
                                arm.scoreArm(),
                                wrist.intake()));


                        }}}}}
