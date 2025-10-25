package org.firstinspires.ftc.teamcode.part_robot;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RollerIntake_EncoderBased {
    public DcMotorEx intakeMotors;

    public RollerIntake_EncoderBased(HardwareMap hardwareMap) {
        intakeMotors = hardwareMap.get(DcMotorEx.class, "intakeMotors");
    }
    public class RollerIntakePull implements Action {
        private boolean initialized = false;
        public boolean run(@NonNull TelemetryPacket packet) {
            if(!initialized) {
                intakeMotors.setPower(-1);
                initialized = true;
            }
            double pos = intakeMotors.getCurrentPosition();
            packet.put("intakePos", pos);
            if(pos < 5000) {
                return true;
            }
            else {
                intakeMotors.setPower(0);
                return false;
            }
        }
    }
    public Action rollerIntkakePull() {
        return new RollerIntakePull();
    }
}
