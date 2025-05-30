package org.firstinspires.ftc.teamcode.subsystems;

import android.widget.GridLayout;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {
    public static double POWER = 1;
    public static int HighBasket = 1650;

    public static int SpecimenIntake = 350;
    public static int HighRung = 425;
    public static int RESET = 0;
    public static int POSITION_TOLERANCE = 600; // Tolerance for position checking
    public static int POSITION_TOLERANCE2 = 50; // Tolerance for position checking

    public static int MANUAL_MOVE_SPEED = 10;
    private int position = 0;

    public final DcMotor slideLeft;
    public final DcMotor slideRight;
    private final HardwareMap hardwareMap;
    private final Gamepad Driver2;
    private final Gamepad Driver1;
    public final Telemetry telemetry;

    public Slide(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        slideLeft = hardwareMap.get(DcMotor.class, "leftSlide");
        slideLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideRight = hardwareMap.get(DcMotor.class, "rightSlide");
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideLeft.setTargetPosition(0);
        slideRight.setTargetPosition(0);

        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveHighBasket() {
        slideLeft.setPower(1);
        slideRight.setPower(1);
        slideLeft.setTargetPosition(HighBasket);
        slideRight.setTargetPosition(HighBasket);
    }

    public void moveToWall() {
        slideLeft.setPower(1);
        slideRight.setPower(1);
        slideLeft.setTargetPosition(SpecimenIntake);
        slideRight.setTargetPosition(SpecimenIntake);
    }

    public void Reset() {
        slideLeft.setPower(1);
        slideRight.setPower(1);
        slideLeft.setTargetPosition(RESET);
        slideRight.setTargetPosition(RESET);
    }

    public void HighRung() {
        slideLeft.setPower(1);
        slideRight.setPower(1);
        slideLeft.setTargetPosition(HighRung);
        slideRight.setTargetPosition(HighRung);
    }

    public void moveMotors(int position) {
        this.position = position;
        slideLeft.setTargetPosition(position);
        slideRight.setTargetPosition(position);
        slideLeft.setPower(POWER);
        slideRight.setPower(POWER);
    }

    /**
     * Checks if the slides are at the high position.
     *
     * @return true if the slides are at the high position within tolerance.
     */
    public boolean isAtHighPosition() {
        int leftPosition = slideLeft.getCurrentPosition();
        int rightPosition = slideRight.getCurrentPosition();
        return Math.abs(leftPosition - HighBasket) <= POSITION_TOLERANCE &&
                Math.abs(rightPosition - HighBasket) <= POSITION_TOLERANCE;
    }
    public boolean isAtIntakePosition() {
        int leftPosition = slideLeft.getCurrentPosition();
        int rightPosition = slideRight.getCurrentPosition();
        return Math.abs(leftPosition - SpecimenIntake) <= POSITION_TOLERANCE2 &&
                Math.abs(rightPosition - SpecimenIntake) <= POSITION_TOLERANCE2;
    }

    /**
     * Adds telemetry data for debugging.
     */
    public void addTelemetry() {
        telemetry.addData("Slide Left Position", slideLeft.getCurrentPosition());
        telemetry.addData("Slide Right Position", slideRight.getCurrentPosition());
        telemetry.addData("Target Position", position);
        telemetry.addData("Slide Power", slideLeft.getPower());
        telemetry.addData("At High Position", isAtHighPosition());
        telemetry.update();
    }
}
