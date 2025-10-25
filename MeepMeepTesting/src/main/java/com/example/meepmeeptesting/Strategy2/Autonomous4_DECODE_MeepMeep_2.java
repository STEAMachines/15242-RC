package com.example.meepmeeptesting.Strategy2;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Autonomous4_DECODE_MeepMeep_2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setDimensions(16.5, 17.7)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        STEAMachines_bot.runAction(STEAMachines_bot.getDrive().actionBuilder(new Pose2d(-48, -48, Math.PI/4))
                .strafeTo(new Vector2d(-20, -20))
                .turn(Math.toRadians(-180))
                .waitSeconds(3)
                .turn(Math.toRadians(60))
                .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(STEAMachines_bot)
                .start();
    }
}
