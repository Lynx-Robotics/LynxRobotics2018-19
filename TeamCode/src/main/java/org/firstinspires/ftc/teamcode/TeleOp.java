package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Tele")
public class TeleOp extends autoBaseV2 {

    TypexChart chart = new TypexChart();
    double speedMultip = 1.0;

    double ZERODOWN = 1.0, ZEROUP = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){

            chart.TL.setPower(-gamepad1.right_stick_y * speedMultip);
            chart.BL.setPower(-gamepad1.right_stick_y * speedMultip);
            chart.TR.setPower(-gamepad1.left_stick_y * speedMultip);
            chart.BR.setPower(-gamepad1.left_stick_y * speedMultip);

            if(gamepad1.dpad_left){
                chart.TL.setPower(0.5);
                chart.TR.setPower(-0.5);
                chart.BL.setPower(-0.5);
                chart.BR.setPower(0.5);
            }
            if(gamepad1.dpad_right){
                chart.TL.setPower(-1);
                chart.TR.setPower(1);
                chart.BL.setPower(1);
                chart.BR.setPower(-1);
            }

            if(gamepad2.a){
                chart.grabState = !chart.grabState;
                sleep(50); //Maybe Delete?
                while (gamepad2.a) {
                }
            }

            if(gamepad1.a){
                speedMultip = 0.25;
            }
            if(gamepad1.y){
                speedMultip = 0.5;
            }
            if (gamepad1.x){
                speedMultip = 0.75;
            }
            if(gamepad1.b){
                speedMultip = 1.0;
            }

            if(chart.grabState){
                chart.middleGrab.setPosition(0.05);
            }
            else {
                chart.middleGrab.setPosition(0.8);
            }

            if(chart.elevMotor.getCurrentPosition() <= 10){
                ZERODOWN = 0;
            }
            else {
                ZERODOWN = 1;
            }

            chart.elevMotor.setPower((-gamepad2.left_trigger * ZERODOWN) + (gamepad2.right_trigger * ZEROUP));

            telemetry.addData("Encoder Position of elevMotor: ", chart.elevMotor.getCurrentPosition());

            telemetry.update();
        }
    }
}
