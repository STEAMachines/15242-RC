package org.firstinspires.ftc.teamcode.Strategy1_MeepMeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.part_robot.RollerIntake_EncoderBased;
import org.firstinspires.ftc.teamcode.part_robot.Shooter_EncoderBased;

@Autonomous(name="AutoDrive4_MeepMeep_EncoderBased", group="STEAMachines_DECODE")
public class AutoDrive4_MeepMeep extends LinearOpMode {
    public void runOpMode() {
        Pose2d beginPose = new Pose2d(-48, -48, Math.PI/4);
        TankDrive drive = new TankDrive(hardwareMap, beginPose);
        RollerIntake_EncoderBased intake = new RollerIntake_EncoderBased(hardwareMap);
        Shooter_EncoderBased shooter = new Shooter_EncoderBased(hardwareMap);
        waitForStart();
        if(!isStopRequested()) return;
        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .build());
    }
}
