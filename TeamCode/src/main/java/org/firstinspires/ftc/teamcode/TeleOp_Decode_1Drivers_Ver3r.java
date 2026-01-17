package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp-1Drivers_Decode_Ver3r", group="STEAMachines_DECODE")
public class TeleOp_Decode_1Drivers_Ver3r extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intakeMotors;
    private DcMotor launcherMotors;
    private Servo handleServo;
    private boolean launcherOn = false;
    boolean changed = false;
    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        intakeMotors = hardwareMap.get(DcMotor.class, "intakeMotors");
        launcherMotors = hardwareMap.get(DcMotor.class, "launcherMotors");
        handleServo = hardwareMap.get(Servo.class, "handleServo");
        waitForStart();
        while(opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftDrive.setPower(Range.clip(turn-drive, -1.0, 1.0));
            rightDrive.setPower(Range.clip(turn+drive, -1.0, 1.0));
            intakeMotors.setPower(
                    gamepad1.right_bumper ? 1.0 :
                    gamepad1.left_bumper ? -1.0 :
                    0
            );
            launcherMotors.setPower(
                    gamepad1.right_trigger > 0.0 ? -gamepad1.right_trigger :
                    gamepad1.left_trigger > 0.0 ? gamepad1.left_trigger :
                    0
            );
            if (gamepad1.b) {
                handleServo.setPosition(0.5);
            }
            if (gamepad1.a) {
                handleServo.setPosition(0.0);
            }
            telemetry.addData("pos", handleServo.getPosition());
            telemetry.update();
        }
    }
}
