package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "colorTester")
public class colorTester extends autoBaseV2 {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Green Left: ", chart.colorSensorLeft.green());
            telemetry.addData("Blue Left: ", chart.colorSensorLeft.blue());
            telemetry.addData("Red Left: ", chart.colorSensorLeft.red());

            telemetry.addData("Alpha Left: ", chart.colorSensorLeft.alpha());

            telemetry.addData("Green Right: ", chart.colorSensorRight.green());
            telemetry.addData("Blue Right: ", chart.colorSensorRight.blue());
            telemetry.addData("Red Right: ", chart.colorSensorRight.red());

            telemetry.addData("Alpha Right: ", chart.colorSensorRight.alpha());

            telemetry.update();

        }
    }
}
