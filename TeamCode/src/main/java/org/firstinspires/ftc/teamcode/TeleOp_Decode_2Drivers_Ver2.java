package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp-2Drivers_Decode_Ver2", group="STEAMachines_DECODE")
public class TeleOp_Decode_2Drivers_Ver2 extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intakeMotors;
    private DcMotor launcherMotors;
    private Servo handleServo;
    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        intakeMotors = hardwareMap.get(DcMotor.class, "intakeMotors");
        launcherMotors = hardwareMap.get(DcMotor.class, "launcherMotors");
        handleServo = hardwareMap.get(Servo.class, "handleServo");
        waitForStart();
        while(opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftDrive.setPower(Range.clip(drive+turn, -1.0, 1.0));
            rightDrive.setPower(Range.clip(drive-turn, -1.0, 1.0));
            intakeMotors.setPower(
                    gamepad1.right_bumper ? 1.0 :
                    gamepad1.left_bumper ? -1.0 :
                    0
            );
            launcherMotors.setPower(
                    gamepad2.right_trigger == 1.0 ? 1.0 :
                    gamepad2.left_trigger == 1.0 ? -1.0 :
                    0
            );
            handleServo.setPosition(
                    gamepad2.right_bumper ? 1.0 :
                    gamepad2.left_bumper ? -1.0 :
                    0
            );
        }
    }
}
