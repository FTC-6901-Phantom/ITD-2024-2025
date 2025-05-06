package org.firstinspires.ftc.teamcode.subsystems.Vision;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Vision extends LinearOpMode {
    private final double limelightHeight = 12.25;
    private final double limelightAngle = 0;
    private final double minDistance = 18.0;
    private final double maxDistance = 32.0;

    // Offsets
    private final double forwardOffset = 0;
    private final double lateralOffset = -1.5;

    private final Limelight3A limelight;
    private final Telemetry telemetry;
    private LLResult result;
    private List<ScoredDetection> scoredDetections;

    private int loopCount = 0;
    private static final int MAX_LOOPS = 200;
    private boolean visionComplete = false;
    private ScoredDetection bestDetection = null;

    // Labels (from uploaded file)
    private final String[] labels = {"blue", "red", "yellow"};

    public Vision(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        limelight = hardwareMap.get(Limelight3A.class, "limelight1");
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(5);
        limelight.start();
    }

    public void periodic() {
        if (loopCount >= MAX_LOOPS) {
            visionComplete = true;
            return;
        }

        result = limelight.getLatestResult();
        if (result == null) {
            telemetry.addLine("Limelight result is null");
            return;  // Skip this loop iteration if there's no result
        }

        List<LLResultTypes.DetectorResult> detections = result.getDetectorResults();
        if (detections == null || detections.isEmpty()) {
            telemetry.addLine("No detections found");
            return;  // Skip processing if no detections are found
        }

        scoredDetections = new ArrayList<>();
        for (LLResultTypes.DetectorResult detection : detections) {
            int classID = detection.getClassId();
            if (classID < 0 || classID >= labels.length) continue; // Ignore unknown classes

            double actualYAngle = limelightAngle - detection.getTargetYDegrees();
            double yDist = limelightHeight * Math.tan(Math.toRadians(actualYAngle));
            double xDist = Math.tan(Math.toRadians(detection.getTargetXDegrees())) * yDist;
            double rotationScore = -Math.abs(detection.getTargetXDegrees());

            double score = calculateScore(yDist, xDist, rotationScore, detections);
            scoredDetections.add(new ScoredDetection(detection, score, yDist, xDist, rotationScore));
        }

        scoredDetections.sort(Comparator.comparingDouble(ScoredDetection::getScore).reversed());

        if (!scoredDetections.isEmpty()) {
            bestDetection = scoredDetections.get(0);
        }

        loopCount++;
    }

    private double calculateScore(double distance, double x, double rotationScore, List<LLResultTypes.DetectorResult> detections) {
        double score = 0.0;
        if (distance < minDistance || distance > maxDistance) score -= 100;
        score -= Math.abs(x);
        score += rotationScore * 15.0;

        return score;
    }

    public boolean isVisionComplete() {
        return visionComplete;
    }

    public Pose2d getAlignedPose(Pose2d currentPose) {
        if (bestDetection == null) return currentPose;
        double targetX = currentPose.position.x + bestDetection.getXDistance() - lateralOffset;
        double targetY = currentPose.position.y + bestDetection.getYDistance() - forwardOffset;
        return new Pose2d(targetX, targetY, currentPose.heading.log());
    }

    public String getBestLabel() {
        return (bestDetection != null) ? labels[bestDetection.getDetection().getClassId()] : "None";
    }

    @Override
    public void runOpMode() throws InterruptedException {
        // This method is not used in Vision, as it's a subsystem class.
    }
}
