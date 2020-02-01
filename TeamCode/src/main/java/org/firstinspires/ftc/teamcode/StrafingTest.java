package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "StrafingTest")
@Disabled
public class StrafingTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TypexChart chart = new TypexChart();
        double power = 0;

        chart.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.dpad_left){
                resetEncoders(chart.TL);
                resetEncoders(chart.TR);
                resetEncoders(chart.BL);
                resetEncoders(chart.BR);

                chart.TL.setPower(power);
                chart.TR.setPower(-power);
                chart.BL.setPower(-power);
                chart.BR.setPower(power);
                sleep(2000);
            }
            if(gamepad1.dpad_right){
                resetEncoders(chart.TL);
                resetEncoders(chart.TR);
                resetEncoders(chart.BL);
                resetEncoders(chart.BR);

                chart.TL.setPower(-power);
                chart.TR.setPower(power);
                chart.BL.setPower(power);
                chart.BR.setPower(-power);
                sleep(2000);
            }
            if(gamepad1.a){
                power = power + 0.1;
                sleep(50);
                while (gamepad1.a) {
                }
            }
            if(gamepad1.b){
                power = power - 0.1;
                sleep(50);
                while (gamepad1.b) {
                }
            }
            chart.TL.setPower(0);
            chart.TR.setPower(0);
            chart.BL.setPower(0);
            chart.BR.setPower(0);
            
            telemetry.addData("Power Value: ", power);
            telemetry.addData("Encoder Position of TL: ", chart.TL.getCurrentPosition());
            telemetry.addData("Encoder Position of TR: ", chart.TR.getCurrentPosition());
            telemetry.addData("Encoder Position of BL: ", chart.BL.getCurrentPosition());
            telemetry.addData("Encoder Position of BR: ", chart.BR.getCurrentPosition());

            telemetry.update();
        }

    }

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }
}