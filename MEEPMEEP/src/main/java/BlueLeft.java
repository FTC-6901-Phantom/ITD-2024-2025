import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.Vector2d;

public class BlueLeft {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(75, 75, Math.toRadians(180), Math.toRadians(180), 10.67)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(33, 63, Math.toRadians(270)))
                        .setTangent(Math.toRadians(350))
                        .splineToLinearHeading(new Pose2d(60, 60, Math.toRadians(225)), Math.toRadians(350))  // Preload to Basket
                        .waitSeconds(2)
                        .splineToLinearHeading(new Pose2d(50, 41, Math.toRadians(270)), Math.toRadians(225))          // Basket to First Sample
                        .waitSeconds(2)
                        .setTangent(45)
                        .splineToLinearHeading(new Pose2d(60, 60, Math.toRadians(225)), Math.toRadians(45))  // First Sample back to Basket
                        .waitSeconds(2)
                        .splineTo(new Vector2d(60, 40), -Math.PI / 2)                                        // Basket to Second Sample
                        .waitSeconds(2)
                        .splineToLinearHeading(new Pose2d(60, 60, Math.toRadians(225)), Math.toRadians(270))  // Second Sample back to Basket
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(250))
                        .lineToYLinearHeading(26, Math.toRadians(0))                                         // Basket to Third Sample (approach)
                        .setTangent(Math.toRadians(0))
                        .lineToXLinearHeading(54, Math.toRadians(0))                                         // Fine adjustment for Third Sample
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(0))
                        .lineToXLinearHeading(50, Math.toRadians(0))                                         // Adjust to grab Third Sample
                        .waitSeconds(2)
                        .splineToLinearHeading(new Pose2d(62, 62, Math.toRadians(225)), Math.toRadians(270))  // Third Sample to Basket
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(245))
                        .lineToYLinearHeading(0, Math.toRadians(270))                                        // Park
                        .build());



                meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}