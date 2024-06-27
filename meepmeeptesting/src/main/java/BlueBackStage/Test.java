package BlueBackStage;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class Test {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(73, 73, Math.toRadians(180), Math.toRadians(180), 7.55)
                .setDimensions(11,14.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36.10, 66.55, Math.toRadians(270)))
                                .setReversed(true)
                                .lineTo(new Vector2d(-35.69, 29.40))
                                .setReversed(false)
                                .back (6)
                                .lineTo(new Vector2d(-20.48, 61.45))
                                .lineTo(new Vector2d(22.57, 61.03))
                                .lineTo(new Vector2d(47.84, 35.62))
                                .splineTo(new Vector2d(48.12, 62.97), Math.toRadians(90.00))
                                .build()




                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
