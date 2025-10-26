package com.example.meepmeeptesting.Strategy1;


import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Autonomous2_DECODE_MeepMeep {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(16.5, 17.7)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(60, -15, Math.PI))
                        .lineToLinearHeading(new Pose2d(0, 0, Math.toRadians(-135)))
                        .build());
        meepMeep.setBackground(MeepMeep.Background.GRID_GRAY)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(STEAMachines_bot)
                .start();
    }
}
