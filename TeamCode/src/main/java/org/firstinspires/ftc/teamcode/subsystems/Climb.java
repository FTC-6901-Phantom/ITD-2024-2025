package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Climb {
    public final DcMotor LeftClimb;
    public final DcMotor RightClimb;

    private final HardwareMap hardwareMap;
    private final Gamepad Driver2;
    private final Gamepad Driver1;
    public final Telemetry telemetry;

    public Climb(OpMode opMode) {
        Driver2 = opMode.gamepad2;
        Driver1 = opMode.gamepad1;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        LeftClimb = hardwareMap.get(DcMotor.class, "LClimb");
        LeftClimb.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftClimb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RightClimb = hardwareMap.get(DcMotor.class, "RClimb");
        RightClimb.setDirection(DcMotorSimple.Direction.FORWARD);
        RightClimb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset encoders to start at 0
        LeftClimb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightClimb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void teleOp() {
        if (Driver1.right_bumper) moveByTicks(150);  // Increment by 20 ticks
        else if (Driver1.left_bumper) moveByTicks(-150);  // Decrement by 20 ticks
    }

    public void teleOp2() {
        if (Driver2.dpad_left) moveByTicks(150);  // Increment by 20 ticks
        else if (Driver2.dpad_right) moveByTicks(-150);  // Decrement by 20 ticks
    }

    /**
     * Moves both motors by the specified number of ticks.
     *
     * @param ticks Number of encoder ticks to add/subtract (positive for up, negative for down)
     */
    public void moveByTicks(int ticks) {
        // Calculate new target positions by adding ticks
        int newLeftTarget = LeftClimb.getCurrentPosition() + ticks;
        int newRightTarget = RightClimb.getCurrentPosition() + ticks;

        LeftClimb.setTargetPosition(newLeftTarget);
        RightClimb.setTargetPosition(newRightTarget);

        LeftClimb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightClimb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        LeftClimb.setPower(1);
        RightClimb.setPower(1);

        // Optional telemetry for debugging
        telemetry.addData("Left Target", newLeftTarget);
        telemetry.addData("Right Target", newRightTarget);
        telemetry.addData("Left Position", LeftClimb.getCurrentPosition());
        telemetry.addData("Right Position", RightClimb.getCurrentPosition());
        telemetry.update();
    }

    /**
     * Stops both motors and holds their position.
     */
    public void Stop() {
        // Stop power but hold position
        LeftClimb.setPower(0);
        RightClimb.setPower(0);
    }
}