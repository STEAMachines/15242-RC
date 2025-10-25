package org.firstinspires.ftc.teamcode.part_robot;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter_EncoderBased {
    public DcMotorEx shooterMotors;

    public Shooter_EncoderBased(HardwareMap hardwareMap) {
        shooterMotors = hardwareMap.get(DcMotorEx.class, "shooterMotors");
    }
    public class ShooterMotorsThrow implements Action {
        private boolean initialized = false;
        public boolean run(@NonNull TelemetryPacket packet) {
            if(!initialized) {
                shooterMotors.setPower(1);
                initialized = true;
            }
            double pos = shooterMotors.getCurrentPosition();
            packet.put("shooterPos", pos);
            if(pos < 5000) {
                return true;
            }
            else {
                shooterMotors.setPower(0);
                return false;
            }
        }
    }
    public Action shooterMotorsThrow() {
        return new ShooterMotorsThrow();
    }
}
