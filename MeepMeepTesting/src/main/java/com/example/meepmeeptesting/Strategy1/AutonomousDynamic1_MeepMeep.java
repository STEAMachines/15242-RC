//package com.example.meepmeeptesting.Strategy1;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.noahbres.meepmeep.MeepMeep;
//import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
//import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//
//public class AutonomousDynamic1_MeepMeep {
//    public static void main(String[] args) {
//        int xpmrand = (int) (Math.random() * 100);
//        int ypmrand = (int) (Math.random() * 100);
//        int x = (int) (Math.random() * 72);
//        int y = (int) (Math.random() * 72);
//        if (xpmrand < 50) {
//            x = -x;
//        }
//        if (ypmrand < 50) {
//            y = -y;
//        }
//        int sequence = (int) (Math.random() * 3);
//        System.out.println("X: " + x + " Y: " + y);
//        MeepMeep meepMeep = new MeepMeep(800);
//        RoadRunnerBotEntity STEAMachines_bot = new DefaultBotBuilder(meepMeep)
//                .setDimensions(16.5,17.7)
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .build();
//        STEAMachines_bot.runAction(STEAMachines_bot.getDrive().actionBuilder(new Pose2d(x, y, Math.PI))
//                .setTangent(0)
//                .splineToConstantHeading(new Vector2d(-54, 54), Math.PI/4)
//                .build());
//        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(STEAMachines_bot)
//                .start();
//    }
//}
