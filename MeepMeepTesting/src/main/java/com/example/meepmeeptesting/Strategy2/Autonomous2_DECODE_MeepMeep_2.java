package com.example.meepmeeptesting.Strategy2;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Autonomous2_DECODE_MeepMeep_2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
                .setDimensions(16.5, 17.7)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .build();
        STEAMachines_bot.runAction(STEAMachines_bot.getDrive().actionBuilder(new Pose2d(60, -15, Math.PI))
                .turn(Math.toRadians(-15))
                .lineToX(0)
                .waitSeconds(3)
                .turn(Math.toRadians(60))
                .strafeTo(new Vector2d(-34, -34))
                .waitSeconds(3)
                .turn(Math.toRadians(120))
                .strafeTo(new Vector2d(-16, -40))
                .waitSeconds(3)
                .strafeTo(new Vector2d(-34, -34))
                .turn(Math.toRadians(-120))
                .waitSeconds(3)
                .strafeTo(new Vector2d(25, 25))
                .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(STEAMachines_bot)
                .start();
    }
}
