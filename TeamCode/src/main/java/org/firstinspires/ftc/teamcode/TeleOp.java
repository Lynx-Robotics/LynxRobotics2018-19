package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.GENETX_ENVIRON.hardwareMap;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends autoBaseV4 {

     hardwareMap map = new hardwareMap();
    double speedMultip;

    int speedSelector = 0;

    int phase = 0;

    double ZERODOWN = 1.0, ZEROUP = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        speedMultip = 1.0 + (-gamepad1.left_trigger);

        waitForStart();
        while(opModeIsActive()){

            telemetry.addData("Speed Selector: ", speedSelector);
            telemetry.update();

            map.TL.setPower(-gamepad1.right_stick_y * speedMultip);
            map.BL.setPower(-gamepad1.right_stick_y * speedMultip);
            map.TR.setPower(-gamepad1.left_stick_y * speedMultip);
            map.BR.setPower(-gamepad1.left_stick_y * speedMultip);

            if(gamepad1.dpad_left){
                map.TL.setPower((-0.5));
                map.TR.setPower((0.5));
                map.BL.setPower((0.5) - 0.002);
                map.BR.setPower((-0.5) + 0.002);
                while(gamepad1.dpad_left){

                }
            }

            if(gamepad1.dpad_right){
                map.TL.setPower((0.5));
                map.TR.setPower(-(0.5));
                map.BL.setPower(-(0.5) + 0.002);
                map.BR.setPower((0.5) - 0.002);
                while(gamepad1.dpad_right){

                }
            }

            if(gamepad2.a){
                map.grabState = !map.grabState;
                sleep(50); //Maybe Delete?
                while (gamepad2.a) {
                }
            }


            if(map.grabState){
                map.middleGrab.setPosition(0.4);
            }
            else {
                map.middleGrab.setPosition(0.97);
            }

            if(map.elevMotor.getCurrentPosition() <= 10 && !gamepad2.y){
                ZERODOWN = 0;
                while (gamepad2.y){

                }
                resetEncoders(map.elevMotor);
            }
            else if(gamepad2.y){
                ZERODOWN = 1;
            }
            else {
                ZERODOWN = 1;
            }

            if(gamepad1.dpad_down && gamepad2.dpad_down){
                map.capServo.setPosition(0.0);
            }
            if(gamepad1.dpad_up){
                map.capServo.setPosition(0.48);
            }

            if(gamepad1.left_bumper){
                speedSelector--;
                if(speedSelector < 0){
                    speedSelector = 0;
                }
                while(gamepad1.left_bumper){

                }
            }
            if(gamepad1.right_bumper){
                speedSelector++;
                if(speedSelector == 5){
                    speedSelector = 0;
                }
                while(gamepad1.right_bumper){

                }
            }

            if(speedSelector == 0){
                speedMultip = 0.25;
            }
            else if(speedSelector == 1){
                speedMultip = 0.40;
            }
            else if(speedSelector == 2){
                speedMultip = 0.60;
            }
            else if(speedSelector == 3){
                speedMultip = 0.80;
            }
            else if (speedSelector == 4){
                speedMultip = 1.0;
            }


            if(map.TAPEMOTOR.getCurrentPosition() <= 10 && !gamepad1.y){
                ZERODOWN = 1;
                while (gamepad1.y){

                }
//                resetEncoders(map.TAPEMOTOR);
            }
            else if(gamepad1.y){
                ZERODOWN = 1;
            }
            else {
                ZERODOWN = 1;
            }


            if(gamepad1.a){
                phase++;
                if(phase == 4){
                    phase = 1;
                }
                if (phase == 1){
                    map.TAPEROT.setPosition(0.25);
                }
                else if(phase == 2){
                    map.TAPEROT.setPosition(0.65);
                }
                else if (phase == 3){
                    map.TAPEROT.setPosition(0.96);
                }
                while(gamepad1.a);
            }

            map.TAPEMOTOR.setPower((-gamepad1.right_trigger * ZERODOWN) + (gamepad1.left_trigger * ZEROUP));


            map.elevMotor.setPower((-gamepad2.left_trigger * ZERODOWN) + (gamepad2.right_trigger * ZEROUP));

            telemetry.addData("Encoder Position of elevMotor: ", map.elevMotor.getCurrentPosition());
            telemetry.addData("Encoder Position of TMMotor: ", map.TAPEMOTOR.getCurrentPosition());


            telemetry.update();
        }
    }
}