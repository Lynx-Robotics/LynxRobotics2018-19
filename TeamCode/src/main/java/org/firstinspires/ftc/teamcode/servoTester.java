package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "servoTest", group = "test")
public class servoTester extends LinearOpMode {
    Servo mGrab;
    CRServo crGrab;

    @Override
    public void runOpMode() throws InterruptedException {
        mGrab = hardwareMap.get(Servo.class, "middleGrab");
        crGrab = hardwareMap.get(CRServo.class, "mGrab");

        mGrab.setPosition(0.9);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Position of Servo: ", mGrab.getPosition());
            telemetry.update();

            if(gamepad2.a){
                crGrab.setPower(1.0);
            }
            else {
                crGrab.setPower(0.0);
            }

            if(gamepad1.a){
                mGrab.setPosition(mGrab.getPosition()+0.05);
                sleep(1000);
            }
            if(gamepad1.y){
                mGrab.setPosition(mGrab.getPosition()-0.05);
                sleep(1000);
            }
        }
    }
}