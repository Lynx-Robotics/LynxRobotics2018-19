package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.lang.reflect.Type;

@TeleOp(name = "Encoder Tester")
@Disabled
public class encoderTester extends LinearOpMode {
    TypexChart chart = new TypexChart();

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Encoder Position elevMotor: ", chart.elevMotor.getCurrentPosition());
            telemetry.addData("Encoder Position TL: ", chart.TL.getCurrentPosition());
            telemetry.addData("Encoder Position TR: ", chart.TR.getCurrentPosition());
            telemetry.addData("Encoder Position BL: ", chart.BL.getCurrentPosition());
            telemetry.addData("Encoder Position BR: ", chart.BR.getCurrentPosition());

            telemetry.update();

        }
    }
}
