import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.Vector2d;

public class RIGHT {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(75, 75, Math.toRadians(180), Math.toRadians(180), 10.67)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-10, 63, Math.toRadians(270)))
                .lineToYConstantHeading(38)
                .waitSeconds(.5)
                .strafeTo(new Vector2d(-48,42))
                        .waitSeconds(.3)
                .setTangent(Math.toRadians(90))
                .lineToYConstantHeading(50)
                        .waitSeconds(.3)
                .strafeTo(new Vector2d(-58,42))
                        .waitSeconds(.3)
                .setTangent(Math.toRadians(90))
                .lineToYConstantHeading(50)
                        .waitSeconds(.3)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                        .waitSeconds(.5)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(-6,38,Math.toRadians(-90)),Math.toRadians(135))
                .waitSeconds(.5)
                .setTangent(90)
                .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(.5)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(-6,38,Math.toRadians(-90)),Math.toRadians(135))
                .waitSeconds(.5)
                .setTangent(90)
                .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(.5)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(-6,38,Math.toRadians(-90)),Math.toRadians(135))
                .waitSeconds(.5)
                .setTangent(90)
                .splineToLinearHeading(new Pose2d(-36, 56, Math.toRadians(90)), Math.toRadians(90))
                .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}