package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.lang.reflect.Type;

@TeleOp(name = "Encoder Tester")
public class encoderTester extends LinearOpMode {
    TypexChart chart = new TypexChart();

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Encoder Position: ", chart.elevMotor.getCurrentPosition());

            telemetry.update();

        }
    }
}
