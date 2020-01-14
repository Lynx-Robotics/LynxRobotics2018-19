package org.firstinspires.ftc.teamcode;

import android.graphics.LinearGradient;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "color tester", group = "tester")
//@Disabled
public class colorTest extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("GVal: ", chart.bottomColorSensor.green());
            telemetry.addData("BVal: ", chart.bottomColorSensor.blue());
            telemetry.addData("RVal: ", chart.bottomColorSensor.red());
            telemetry.update();
        }
    }
}
