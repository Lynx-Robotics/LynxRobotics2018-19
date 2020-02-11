package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Tele")
public class TeleOp extends autoBaseV4 {

    TypexChart chart = new TypexChart();
    double speedMultip;

    double ZERODOWN = 1.0, ZEROUP = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        speedMultip = 1.0 + (-gamepad1.left_trigger);

        waitForStart();
        while(opModeIsActive()){

            chart.TL.setPower(-gamepad1.right_stick_y * speedMultip);
            chart.BL.setPower(-gamepad1.right_stick_y * speedMultip);
            chart.TR.setPower(-gamepad1.left_stick_y * speedMultip);
            chart.BR.setPower(-gamepad1.left_stick_y * speedMultip);

            if(gamepad1.dpad_left){
                chart.TL.setPower((-0.3));
                chart.TR.setPower((0.3));
                chart.BL.setPower((0.3) - 0.002);
                chart.BR.setPower((-0.3) + 0.002);
                while(gamepad1.dpad_left){

                }
            }

            if(gamepad1.dpad_right){
                chart.TL.setPower((0.3));
                chart.TR.setPower(-(0.3));
                chart.BL.setPower(-(0.3) + 0.002);
                chart.BR.setPower((0.3) - 0.002);
                while(gamepad1.dpad_right){

                }
            }

            if(gamepad2.a){
                chart.grabState = !chart.grabState;
                sleep(50); //Maybe Delete?
                while (gamepad2.a) {
                }
            }


            if(chart.grabState){
                chart.middleGrab.setPosition(0.05);
            }
            else {
                chart.middleGrab.setPosition(0.8);
            }

            if(chart.elevMotor.getCurrentPosition() <= 10 && !gamepad2.y){
                ZERODOWN = 0;
                while (gamepad2.y){

                }
                resetEncoders(chart.elevMotor);
            }
            else if(gamepad1.y){
                ZERODOWN = 1;
            }
            else {
                ZERODOWN = 1;
            }

            if(gamepad1.dpad_down && gamepad2.dpad_down){
                chart.capServo.setPosition(0.0);
            }

            chart.elevMotor.setPower((-gamepad2.left_trigger * ZERODOWN) + (gamepad2.right_trigger * ZEROUP));

            telemetry.addData("Encoder Position of elevMotor: ", chart.elevMotor.getCurrentPosition());

            telemetry.update();
        }
    }
}