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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-8, 63, Math.toRadians(90)))
                        .strafeTo(new Vector2d(-2,32))
                .waitSeconds(.5)
                .splineToLinearHeading(new Pose2d(-37,40,Math.toRadians(90)),Math.toRadians(270))
                .lineToY(10)
                .strafeTo(new Vector2d(-46,10))
                .strafeTo(new Vector2d(-46,56))
                .strafeTo(new Vector2d(-46,10))
                .strafeTo(new Vector2d(-54,10))
                .strafeTo(new Vector2d(-54,56))
                .strafeTo(new Vector2d(-54,10))
                .strafeTo(new Vector2d(-62,10))
                .strafeTo(new Vector2d(-62,56))
                .strafeTo(new Vector2d(-37,50))
                        .waitSeconds(1)
                .strafeTo(new Vector2d(-2.5,32))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-37,50))
                        .waitSeconds(1)
                .strafeTo(new Vector2d(-3,32))
                        .waitSeconds(1)
                .strafeTo(new Vector2d(-37,50))
                        .waitSeconds(1)
                .strafeTo(new Vector2d(-3.5,32))
                        .waitSeconds(1)
                .strafeTo(new Vector2d(-37,50))
                        .waitSeconds(1)
                .strafeTo(new Vector2d(-4,32))
                .strafeTo(new Vector2d(-37,50))
                .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}