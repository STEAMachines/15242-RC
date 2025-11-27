package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Autonomous_DECODE_MeepMeep_9Auto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(16.5, 17.7)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(60, 15, Math.PI))
                        .lineToLinearHeading(new Pose2d(0,0, Math.toRadians(135)))
                        .waitSeconds(1)
                        .turn(Math.toRadians(-25))
                        .forward(40)
                        .waitSeconds(1)
                        .forward(14)
                        .waitSeconds(1)
                        .back(54)
                        .turn(Math.toRadians(25))
                        .waitSeconds(1)
                        .turn(Math.toRadians(-50))
                        .forward(40)
                        .waitSeconds(1)
                        .forward(14)
                        .waitSeconds(1)
                        .back(54)
                        .turn(Math.toRadians(50))
                        .build());
        meepMeep.setBackground(MeepMeep.Background.GRID_GRAY)
                .setBackgroundAlpha(0.95f)
                .setDarkMode(true)
                .addEntity(STEAMachines_bot)
                .start();
    }
}
