package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class autoBaseV2 extends LinearOpMode {

    TypexChart chart = new TypexChart();

    public void wait(double seconds, String phase) {
        ElapsedTime Intruntime = new ElapsedTime();
        Intruntime.reset();
        while (opModeIsActive() && Intruntime.seconds() < seconds) {
            telemetry.addData("Status: ", phase);
            telemetry.update();
        }
    }

    public void dropDL() {
        chart.hookLeft.setPosition(0.1);
        chart.hookRight.setPosition(0.1);
    }

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public void goToPosition(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, int position, double power) {
        resetEncoders(motor1);

        int currentPos = motor1.getCurrentPosition();
        double pError = (double)(currentPos - position) / (double)position;
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);

        while ((motorPosition <= position) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        chart.DebugSwitch = true;
    }

    public int distance2encoder(double distance){
        double circumference = 12.56637961;
        int fullRotateEncoder = 76;

        double conversionRate = (double)fullRotateEncoder/circumference;

        return (int) (distance * conversionRate);


    }

    public void encoderStrafeLeft(int position, double power){
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
    }

    public void encoderStrafeRight(int position, double power){
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
    }

    public void encoderDrive(int position, double power){
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
    }
}
