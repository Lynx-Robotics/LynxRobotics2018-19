package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "encoderSystem", group = "encoders")
public class encoderBase extends encoderSystem {

    double CPRo = 75.6; //Counts per Rotation Output

    @Override
    public void runOpMode() throws InterruptedException {
        //chart.init(hardwareMap);

        waitForStart();
        telemetry.addData("Going to Position: ", true);

        goToPosition(chart.TR, 76, 0.15); //18.9 gear ratio
        //goToPosition(chart.BR, 76, 0.15);

        while(opModeIsActive()){
            telemetry.addData("Encoder Position: ", chart.TR.getCurrentPosition());
            telemetry.addData("Encoder Position: ", chart.BR.getCurrentPosition());

            telemetry.update();
        }
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
