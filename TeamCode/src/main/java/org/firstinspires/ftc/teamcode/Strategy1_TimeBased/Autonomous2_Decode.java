package org.firstinspires.ftc.teamcode.Strategy1_TimeBased;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Autonomous2_DECODE", group="STEAMachines_DECODE")
public class Autonomous2_Decode extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        waitForStart();
    }
}
