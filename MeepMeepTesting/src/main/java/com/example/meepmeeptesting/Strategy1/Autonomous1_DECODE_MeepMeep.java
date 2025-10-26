package com.example.meepmeeptesting.Strategy1;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Autonomous1_DECODE_MeepMeep {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(16.5, 17.7)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(60, 15, Math.PI))
                        .lineToLinearHeading(new Pose2d(0, 0, Math.toRadians(135)))
//                        .lineTo(new Vector2d(0,0))
//                        .waitSeconds(3)
                        .lineTo(new Vector2d(-34, 34))
//                        .waitSeconds(3)
                        .turn(Math.toRadians(-90))
                        .forward(18)
                        .back(18)
                        .turn(Math.toRadians(90))
                        .build());
        meepMeep.setBackground(MeepMeep.Background.GRID_GRAY)
                .setBackgroundAlpha(0.95f)
                .setDarkMode(true)
                .addEntity(STEAMachines_bot)
                .start();
    }
}
