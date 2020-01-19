package org.firstinspires.ftc.teamcode;

import android.graphics.LinearGradient;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "color tester", group = "tester")
//@Disabled
public class colorTest extends TeleBase {



    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Bottom GVal: ", chart.bottomColorSensor.green());
            telemetry.addData("Bottom BVal: ", chart.bottomColorSensor.blue());
            telemetry.addData("Bottom RVal: ", chart.bottomColorSensor.red());

            telemetry.addData("Left GVal: ", chart.colorSensorLeft.green());
            telemetry.addData("Left BVal: ", chart.colorSensorLeft.blue());
            telemetry.addData("Left RVal: ", chart.colorSensorLeft.red());
            telemetry.addData("Left Hue: ", chart.colorSensorLeft.argb());
            telemetry.addData("Left Alpha: ", chart.colorSensorLeft.alpha());

            telemetry.addData("Right GVal: ", chart.colorSensorRight.green());
            telemetry.addData("Right BVal: ", chart.colorSensorRight.blue());
            telemetry.addData("Right RVal: ", chart.colorSensorRight.red());
            telemetry.addData("Right Hue: ", chart.colorSensorRight.argb());
            telemetry.addData("Right Alpha: ", chart.colorSensorRight.alpha());

            //telemetry.addData("Spotted the skystone Left? ", !SkyStoneSpottedLeft());
            //telemetry.addData("Spotted the skystone Right? ", !SkyStoneSpottedRight());


            telemetry.update();
        }
    }
}
