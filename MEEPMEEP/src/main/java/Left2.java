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

                // Initial strafe to (-53, -63)
                .strafeToConstantHeading(new Vector2d(-53, -63))
                .waitSeconds(1)

                // Back to (-20, -63) and back to (-53, -63) -- preserving the back-and-forth motion
                .strafeToConstantHeading(new Vector2d(-20, -63)) // Move to (-20, -63)
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-53, -63)) // Return to (-53, -63)
                .waitSeconds(1)

                // Now a smoother spline transition to (-48, -42) at a new angle
                // Adjusted tangent to avoid the wall
                .splineToLinearHeading(new Pose2d(-48, -42, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(1)

                // Moving to (-61, -52) and back to (-59, -42), using strafe and back-and-forth
                .strafeToConstantHeading(new Vector2d(-61, -52)) // Move to (-61, -52)
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-59, -42)) // Move to (-59, -42)
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-61, -52)) // Return to (-61, -52)
                .waitSeconds(1)

                // Adjusted spline to move to (-48, -26) while avoiding the wall
                .splineToLinearHeading(new Pose2d(-48, -26, Math.toRadians(180)), Math.toRadians(180))

                // Final back-and-forth moves to (-54, -26) and back to (-48, -26)
                .strafeToConstantHeading(new Vector2d(-54, -26)) // Move to (-54, -26)
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(-48, -26)) // Return to (-48, -26)
                .waitSeconds(1)

                // Final adjusted spline to move to the last point at (-61, -52)
                .splineToLinearHeading(new Pose2d(-61, -52, Math.toRadians(90)), Math.toRadians(90))

                .build());

        // Set background and start the simulation
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}