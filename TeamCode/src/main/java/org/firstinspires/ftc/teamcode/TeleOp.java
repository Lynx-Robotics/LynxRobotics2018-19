package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Tele")
public class TeleOp extends LinearOpMode {

    TypexChart chart = new TypexChart();

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){

            chart.TL.setPower(-gamepad1.right_stick_y);
            chart.BL.setPower(-gamepad1.right_stick_y);
            chart.TR.setPower(-gamepad1.left_stick_y);
            chart.BR.setPower(-gamepad1.left_stick_y);

            chart.elevMotor.setPower((-gamepad1.left_trigger) + (gamepad1.right_trigger));

            telemetry.addData("Encoder Position of elevMotor: ", chart.elevMotor.getCurrentPosition());

            telemetry.update();
        }
    }
}
