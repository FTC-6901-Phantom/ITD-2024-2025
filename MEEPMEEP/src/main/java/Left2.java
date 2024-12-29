import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.Vector2d;

public class Left2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(75, 75, Math.toRadians(180), Math.toRadians(180), 10.67)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-33, -63, Math.toRadians(0)))
                .strafeToConstantHeading(new Vector2d(-53,-63))
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-20,-63))
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-53,-63))
                .strafeToLinearHeading(new Pose2d(-48, -42, Math.toRadians(90)).component1(), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-61,-52))
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-59,-42))
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-61,-52))
                .strafeToLinearHeading(new Pose2d(-48, -26, Math.toRadians(45)).component1(), Math.toRadians(180))
                .strafeToConstantHeading(new Vector2d(-54,-26))
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-48,-26))
                .strafeToLinearHeading(new Pose2d(-61, -52, Math.toRadians(45)).component1(), Math.toRadians(90))


                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}