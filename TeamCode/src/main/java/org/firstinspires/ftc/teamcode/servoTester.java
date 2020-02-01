package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "servoTest", group = "test")
@Disabled
public class servoTester extends autoBaseV2 {
    CRServo crGrab;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Position of Servo (middleGrab): ", chart.middleGrab.getPosition());
            telemetry.addData("Position of Servo (hookRight): ", chart.hookRight.getPosition());
            telemetry.addData("Position of Servo (hookLeft): ", chart.hookLeft.getPosition());
            telemetry.update();

            if(gamepad1.a){
                chart.middleGrab.setPosition(chart.middleGrab.getPosition()+0.05);
                chart.hookLeft.setPosition(chart.hookLeft.getPosition()+0.05);
                chart.hookRight.setPosition(chart.hookRight.getPosition()+0.05);
                while (gamepad1.a);
            }
            if(gamepad1.y){
                chart.middleGrab.setPosition(chart.middleGrab.getPosition()-0.05);
                chart.hookLeft.setPosition(chart.hookLeft.getPosition()-0.05);
                chart.hookRight.setPosition(chart.hookRight.getPosition()-0.05);
                while (gamepad1.y);
            }
        }
    }
}