//package org.firstinspires.ftc.teamcode.part_robot;
//
//import com.acmerobotics.roadrunner.Action;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
//import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//
//import java.util.List;
//
//public class centerToGoal {
//    private VisionPortal visionPortal ;
//    private AprilTagProcessor aprilTag;
//    private AprilTagDetection desiredTag;
//
//    private DcMotor leftDrive;
//    private DcMotor rightDrive;
//
//    private int bearing;
//
//    VisionPortal.Builder enableLiveView(boolean enableLiveView)
//
//    public centerToGoal(HardwareMap hardwareMap) {
//        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
//        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
//        while (AprilTagPoseFtc.bearing()) {
//            if (AprilTagPoseFtc.bearing()) {
//                leftDrive.setPower(-1);
//                rightDrive.setPower(1);
//                if (/*apriltag sudah terlalu Samping*/) {
//                    leftDrive.setPower(1);
//                    /*cara return balik ke memposisikan*/
//                }
//            } else if (AprilTagPoseFtc.bearing()) {
//                leftDrive.setPower(1);
//                rightDrive.setPower(-1);
//                if (/*apriltag sudah terlalu Samping*/) {
//                    rightDrive.setPower(1);
//                    /*cara return balik ke memposisikan*/
//                }
//            }
//        }
//
//    }
//
//    public Action centerToGoal() {return new centerToGoal()}
//}
