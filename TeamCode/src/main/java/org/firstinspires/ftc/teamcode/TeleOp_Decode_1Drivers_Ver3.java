package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.lynx.LynxServoController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;


@TeleOp(name="TeleOp-1Drivers_Decode_Ver3", group="STEAMachines_DECODE")
public class TeleOp_Decode_1Drivers_Ver3 extends LinearOpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intakeMotors;
    private DcMotor launcherMotors;
    private Servo handleServo;
    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTag;
    private boolean launcherOn = false;
    private AprilTagDetection desiredTag;
    private static final boolean USE_WEBCAM = true;
    private static final int DESIRED_TAG_ID = 20;
    final double DESIRED_DISTANCE = 12.0;
    final double SPEED_GAIN = 0.02;
    final double TURN_GAIN = 0.01;
    final double MAX_AUTO_SPEED = 1.0;
    final double MAX_AUTO_TURN = 0.5;
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
                    gamepad1.left_bumper ? 1.0 :
                    gamepad1.right_bumper ? -1.0 :
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
            if (gamepad1.yWasPressed()) {
                c2g();
            }
            telemetry.addData("pos", handleServo.getPosition());
            telemetry.update();
        }
    }

    public void initializeAprilTag() {
        aprilTag = new AprilTagProcessor.Builder().build();
        VisionPortal.Builder builder = new VisionPortal.Builder();
        if(USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam1"));
        }
        else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }
        builder.addProcessor(aprilTag);
        visionPortal = builder.build();
    }

    public void c2g() {
        boolean targetFound = false;
        double left = 0.0;
        double right = 0.0;
        List<AprilTagDetection> currentDetection = aprilTag.getDetections();
        for(AprilTagDetection detection:currentDetection) {
            if(detection.metadata != null) {
                if(DESIRED_TAG_ID < 0 || (detection.id == DESIRED_TAG_ID)) {
                    targetFound = true;
                    desiredTag = detection;
                    break;
                }
                else {
                    telemetry.addData("Skipping", "Tag ID %d is not desired", detection.id);
                }
            }
            else {
                telemetry.addData("Unknown", "Tag ID %d is not in TagLibrary", detection.id);
            }
            telemetry.addData("ID", detection.id);
            telemetry.addData("Range", detection.ftcPose.range);
            telemetry.addData("Bearing", detection.ftcPose.bearing);
        }
        if(targetFound) {
            while (desiredTag.ftcPose.bearing > 0) {
                double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
                double headingError = desiredTag.ftcPose.bearing;
                drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
                turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
                telemetry.addData("Found", "ID %d (%s)", desiredTag.id, desiredTag.metadata.name);
                telemetry.addData("Range", "%5.1f inches", desiredTag.ftcPose.range);
                telemetry.addData("Bearing", "%3.0f degrees", desiredTag.ftcPose.bearing);
            }
        }
        else {
            targetFound = false;
        }
        telemetry.update();
    }
}
