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
                .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(225)), -Math.PI / 2)
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(48, 42, Math.toRadians(270)), -Math.PI / 2)
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(53,53, Math.toRadians(225)), Math.toRadians(270))
                .waitSeconds(2)
                .splineTo(new Vector2d(58, 42), -Math.PI / 2)
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(53,53, Math.toRadians(225)), -Math.PI/2)
                .waitSeconds(2)
                .setTangent(Math.toRadians(270))
                .lineToYLinearHeading (26,Math.toRadians(0))
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(53,53, Math.toRadians(225)), -Math.PI/2)
                .waitSeconds(2)
                .setTangent(Math.toRadians(240))
                .lineToYLinearHeading (0,Math.toRadians(270))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}