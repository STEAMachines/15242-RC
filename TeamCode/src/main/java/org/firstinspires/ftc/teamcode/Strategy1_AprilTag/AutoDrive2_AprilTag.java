package org.firstinspires.ftc.teamcode.Strategy1_AprilTag;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous(name="AutoDrive2_AprilTag", group="STEAMachines_DECODE")
public class AutoDrive2_AprilTag extends LinearOpMode {
    final double DESIRED_DISTANCE = 12.0;
    final double SPEED_GAIN = 0.02;
    final double TURN_GAIN = 0.01;
    final double MAX_AUTO_SPEED = 1.0;
    final double MAX_AUTO_TURN = 0.5;
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private static final boolean USE_WEBCAM = true;
    private static final int DESIRED_TAG_ID = 20;
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;
    private AprilTagDetection desiredTag;

    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        boolean isShooting = false;
        initializeAprilTag();
        waitForStart();
        displayWebcamVision();
        autoDrive();
    }
    public void initializeAprilTag() {
        aprilTag = new AprilTagProcessor.Builder().build();
//        aprilTag.setDecimation(3); //If wanna use decimation for down-sampling images or decrease the amount of fps to create smaller images
        VisionPortal.Builder builder = new VisionPortal.Builder();
        if(USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam1"));
        }
        else{
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
                double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
                double headingError = desiredTag.ftcPose.bearing;
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

    public void autoDrive() {
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.4);
        sleep(2500);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(3000);
        leftDrive.setPower(-0.5);
        rightDrive.setPower(0.5);
        sleep(700);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(250);
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);
        sleep(750);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(3000);
        leftDrive.setPower(-0.5);
        rightDrive.setPower(-0.5);
        sleep(2250);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
