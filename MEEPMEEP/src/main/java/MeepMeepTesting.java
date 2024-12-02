import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.Vector2d;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, 60, 60, 14.5)
                        .build();
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(33, 63, Math.toRadians(270)))
                        .setReversed(true)
                        .splineTo(new Vector2d(-5, 36), Math.toRadians(270)) //score preload specimen
                        .waitSeconds(2)
                        .setReversed(false)
                        .splineTo(new Vector2d(-35, 28), Math.toRadians(270)) // waypoint for first alignment

                        .splineTo(new Vector2d(-45, 6), Math.toRadians(270)) // align with first sample

                        .strafeTo(new Vector2d(-45,53)) // push first sample into HP zone

                        .strafeTo(new Vector2d(-47, 11)) // drive back for second
                        .setReversed(true)
                        .splineTo(new Vector2d(-55, 25), Math.toRadians(90)) //waypoint
                        .splineTo(new Vector2d( -55, 53), Math.toRadians(90)) //pushed
                        .setReversed(false)
                        .splineTo(new Vector2d( -57, 11), Math.toRadians(270)) //return for last
                        .setReversed(true)
                        .strafeTo(new Vector2d(-60.5,11))  //align with last sample
                        .strafeTo(new Vector2d(-60.5,53)) // push last sample



                        .setReversed(false)



                        // .splineTo(new Vector2d(-48, 13), Math.toRadians(0))

                        .build());

        //go to starting pos for sample pusher
//                        .strafeTo(new Vector2d(-47,13))
//                        //align to sample
//                        .strafeTo(new Vector2d(-47,52))
//                        // push sample
//                        .strafeTo(new Vector2d(-47,13))
//                        //return to position
//                        .strafeTo(new Vector2d(-57, 13))
//                        //align to sample
//                        .strafeTo(new Vector2d(-57, 52))
//                        //push sample
//                        .strafeTo(new Vector2d(-57, 13))
//                        //return to position
//                        .strafeTo(new Vector2d(-61, 13))
//                        //align to sample
//                        .strafeTo(new Vector2d(-61, 52))
//                        //push sample
//                        .strafeTo(new Vector2d(-42,54))
//                        .waitSeconds(.8)
//                        //pick up sample
//                        .setTangent(0)
//                        .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(0))
//                        .setTangent(180)
//                        .splineToLinearHeading(new Pose2d(-42, 54, Math.toRadians(90)), Math.toRadians(0))
//                        .setTangent(0)
//                        .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(0))
//                        .setTangent(0)
//                        .splineToLinearHeading(new Pose2d(-42, 54, Math.toRadians(90)), Math.toRadians(0))
////                        .setTangent(0)
////                        .splineToLinearHeading(new Pose2d(-5, 34, Math.toRadians(90)), Math.toRadians(0))
//                        .strafeTo(new Vector2d(-42,54))
//                        .setTangent(0)
//                        .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(0))



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }}