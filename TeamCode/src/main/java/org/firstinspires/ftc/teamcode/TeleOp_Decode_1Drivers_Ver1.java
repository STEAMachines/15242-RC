package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name="TeleOp-1Drivers_Decode_Ver1", group="STEAMachines_DECODE")
public class TeleOp_Decode_1Drivers_Ver1 extends LinearOpMode {
    final double DESIRED_DISTANCE = 12.0;
    final double SPEED_GAIN = 0.02;
    final double TURN_GAIN = 0.01;
    final double MAX_AUTO_SPEED = 1.0;
    final double MAX_AUTO_TURN = 0.5;
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intakeMotors;
    private DcMotor launcherMotors;
    private CRServo handleServo;
    private AprilTagProcessor aprilTag;
    private AprilTagDetection desiredTag;
    private VisionPortal visionPortal;
    private static final boolean USE_WEBCAM = true;
    private static final int DESIRED_TAG_ID = 20;
    private boolean intakeToggle = false;

    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class,"leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        intakeMotors = hardwareMap.get(DcMotor.class, "intakeMotors");
        launcherMotors = hardwareMap.get(DcMotor.class, "launcherMotors");
        handleServo = hardwareMap.get(CRServo.class, "handleServo");
        waitForStart();
//        initializeAprilTag();
//        displayWebcamVision();
        while(opModeIsActive()) {
            double leftPower;
            double rightPower;
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(turn + drive, -1.0, 1.0);
            rightPower = Range.clip(turn - drive, -1.0, 1.0);
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            //left_bumper && right_bumper to move......
            if(gamepad1.left_bumper) {
                intakeMotors.setPower(-1);
            }
            else {
                intakeMotors.setPower(0);
            }
//            if (gamepad1.left_bumper && !gamepad1.left_bumper) {
//                intakeToggle = !intakeToggle;
//            }
//            if (intakeToggle) {
//                intakeMotors.setPower(1);
//            }
//            else {
//                intakeMotors.setPower(0);
//            }
            //left_trigger && right_trigger to move..
            if(gamepad1.left_trigger == 1.0) {
                handleServo.setPower(1);
                launcherMotors.setPower(1);
            }
            else {
                handleServo.setPower(0);
                launcherMotors.setPower(0);
            }
        }
    }
    public void initializeAprilTag() {
        aprilTag = new AprilTagProcessor.Builder().build();
        VisionPortal.Builder builder = new VisionPortal.Builder();
        if(USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam SM"));
        }
        else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }
        builder.addProcessor(aprilTag);
        visionPortal = builder.build();
    }
    public void displayWebcamVision() {
        boolean targetFound = true;
        double drive = 0;
        double turn = 0;
        while(opModeIsActive()) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            for(AprilTagDetection detection:currentDetections) {
                if (detection.metadata != null) {
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
                double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
                double headingError = (desiredTag.ftcPose.bearing);
                drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
                turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
                telemetry.addData("Found", "ID %d (%s)", desiredTag.id, desiredTag.metadata.name);
                telemetry.addData("Range",  "%5.1f inches", desiredTag.ftcPose.range);
                telemetry.addData("Bearing","%3.0f degrees", desiredTag.ftcPose.bearing);
            }
            else {
                targetFound = false;
            }
            telemetry.update();
        }
    }
}
