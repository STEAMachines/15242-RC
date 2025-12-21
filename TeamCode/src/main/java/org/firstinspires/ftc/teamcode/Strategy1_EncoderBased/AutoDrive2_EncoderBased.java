package org.firstinspires.ftc.teamcode.Strategy1_EncoderBased;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="AutoDrive2_EncoderBased", group="STEAMachines_DECODE")
public class AutoDrive2_EncoderBased extends LinearOpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private int leftPos;
    private int rightPos;
    @Override
    public void runOpMode(){
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftPos = 0;
        rightPos = 0;
        waitForStart();
        drive(1000, 1000, 0.25);
        drive(-1000, -1000, 0.5);
    }
    private void drive(int leftTarget, int rightTarget, double speed) {
        leftPos += leftTarget;
        rightPos += rightTarget;
        leftDrive.setTargetPosition(leftPos);
        rightDrive.setTargetPosition(rightPos);
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setPower(speed);
        rightDrive.setPower(speed);
        while(opModeIsActive() && leftDrive.isBusy() && rightDrive.isBusy()) {
            idle();
        }
    }
}
