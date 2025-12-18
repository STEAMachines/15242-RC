package org.firstinspires.ftc.teamcode.Strategy1_MeepMeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.part_robot.RollerIntake_EncoderBased;
import org.firstinspires.ftc.teamcode.part_robot.Shooter_EncoderBased;

@Autonomous(name="AutoDrive1_MeepMeep_EncoderBased", group="STEAMachines_DECODE")
public class AutoDrive1_MeepMeep extends LinearOpMode {
    public void runOpMode() {
        Pose2d beginPose = new Pose2d(60, 15, Math.PI);
        TankDrive drive = new TankDrive(hardwareMap, beginPose);
//        RollerIntake_EncoderBased intake = new RollerIntake_EncoderBased(hardwareMap);
//        Shooter_EncoderBased shooter = new Shooter_EncoderBased(hardwareMap);
        waitForStart();
//        if(!isStopRequested()) return;
//        Trajectory traj = drive.trajectoryBuilder(beginPose)
//                .lineTo(new Vector2d(-34, 34))
//                .build();
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .turn(Math.toRadians(-45))
                        .lineToY(0)
//                        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//                        .lineToXLinearHeading(0, Math.toRadians(135))
                        .turn(Math.toRadians(15))
                        .waitSeconds(3)
                        .lineToY(-34)
                        .waitSeconds(3)
//                        .afterTime(3, shooter.shooterMotorsThrow())
                        .turn(Math.toRadians(-60))
                        .lineToY(5)
                        .waitSeconds(3)
                        .lineToY(-5)
                        .turn(Math.toRadians(-60))
////                        .strafeTo(new Vector2d(-34, 34))
////                        .afterTime(3, shooter.shooterMotorsThrow())
////                        .strafeTo(new Vector2d(25,   -25))
                        .build());
    }
}
