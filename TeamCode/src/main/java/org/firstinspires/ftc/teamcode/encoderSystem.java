package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class encoderSystem extends LinearOpMode {
    TypexChart chart = new TypexChart();

    public void resetEncoders(DcMotor motor){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public void goToPosition(DcMotor motor, int position, double power){
        resetEncoders(motor);
        telemetry.addData("Internal Reset: ", true);
        telemetry.update();
        sleep(5000);

        int motorPosition = motor.getCurrentPosition();
        double powerSent = 0.0;

        telemetry.addData("Internal Variable Declaration", true);
        telemetry.update();
        sleep(5000);

        motor.setPower(power);

        telemetry.addData("PreFlight Warm UP", true);
        telemetry.update();
        sleep(5000);

        while (motorPosition < position){
            powerSent = power;
            motorPosition = motor.getCurrentPosition();
        }
        powerSent = 0;
        resetEncoders(motor);
    }


}
