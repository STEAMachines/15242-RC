package com.example.meepmeeptesting.Strategy2;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Autonomous1_DECODE_MeepMeep_2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(16.5, 17.7)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(60, 15, Math.PI))
                        .turn(Math.toRadians(15))
                        .lineTo(new Vector2d(0, 0))
                        .waitSeconds(1)
                        .turn(Math.toRadians(-60))
                        .lineTo(new Vector2d(-34, 34))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(25,   -25))
                        .build());
        meepMeep.setBackground(MeepMeep.Background.GRID_GRAY)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(STEAMachines_bot)
                .start();
    }
}
