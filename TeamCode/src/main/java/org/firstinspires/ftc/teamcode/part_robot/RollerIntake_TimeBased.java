//package org.firstinspires.ftc.teamcode.part_robot;
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class RollerIntake_TimeBased {
//    public DcMotorEx intakeMotors;
//
//    public RollerIntake_TimeBased(HardwareMap hardwareMap) {
//        intakeMotors = hardwareMap.get(DcMotorEx.class, "intakeMotors");
//    }
//    public class RollerIntakePull implements Action {
//        private boolean initialized = false;
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if(!initialized) {
//                intakeMotors.setPower(-1);
//                initialized = true;
//            }
//
//        }
//    }
//    public Action rollerIntakePull() {
//        return new RollerIntakePull();
//    }
//}
