//package com.example.meepmeeptesting.Strategy2;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.noahbres.meepmeep.MeepMeep;
//import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
//import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//
//public class AutonomousDynamic1_MeepMeep_2 {
//    public static void main(String[] args) {
//        MeepMeep meepMeep = new MeepMeep(800);
//        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .setDimensions(16.5, 17.7)
//                .build();
//        STEAMachines_bot.runAction(STEAMachines_bot.getDrive().actionBuilder(new Pose2d(60, -15, Math.PI/4))
//                .build());
//        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(STEAMachines_bot)
//                .start();
//    }
//}
