package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.nio.channels.DatagramChannel;

public class accelerationTesterV3 extends autoBaseV3 {

    acceleration acc = new acceleration();

    DcMotor motor;
    double targetPower;

    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotor.class, "motor");
        targetPower = gamepad1.left_stick_y;

        waitForStart();
        while(opModeIsActive()){

            motor.setPower(acc.acceleratorCorrector(targetPower, motor.getPower()));

        }
    }
}
