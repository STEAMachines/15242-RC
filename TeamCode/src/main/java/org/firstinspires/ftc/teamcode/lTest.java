package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "lTest", group = "STEAMachines_DECODE")
public class lTest extends LinearOpMode{
    private DcMotor launcherMotors;

    public void runOpMode() {
        launcherMotors = hardwareMap.get(DcMotor.class, "launcherMotors");
        waitForStart();
        while (opModeIsActive()) {
            for (double i = 0.0; i <= 1.0; i += 0.1) {
                launcherMotors.setPower(i);
                telemetry.addData("power", i);
                telemetry.update();
                sleep(500);
            }
            for (double i = 1.0; i >= 0.0; i -= 0.1) {
                launcherMotors.setPower(i);
                telemetry.addData("power", i);
                telemetry.update();
                sleep(500);
            }
        }
    }
}
