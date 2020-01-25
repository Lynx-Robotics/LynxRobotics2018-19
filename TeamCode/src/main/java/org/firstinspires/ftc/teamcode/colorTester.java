package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "colorTester")
public class colorTester extends autoBaseV2 {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Green: ", chart.colorSensorLeft.green());
            telemetry.addData("Blue: ", chart.colorSensorLeft.blue());
            telemetry.addData("Red: ", chart.colorSensorLeft.red());

            telemetry.addData("Alpha: ", chart.colorSensorLeft.alpha());

            telemetry.update();

        }
    }
}
