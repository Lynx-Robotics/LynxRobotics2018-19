package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "servoTester")
public class servoTester extends LinearOpMode {

    Servo test;

    double pos = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        test = hardwareMap.get(Servo.class, "test");

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("POSITION: ", pos);
            telemetry.update();

            if(gamepad1.a){
                pos+=0.05;
                while(gamepad1.a);
            }

            if(gamepad1.b){
                pos-=0.05;
                while(gamepad1.b){

                }
            }

            test.setPosition(pos);
        }
    }
}
