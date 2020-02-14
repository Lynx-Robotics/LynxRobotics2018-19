package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.ArrayList;

@Autonomous(name = "colorTester")
public class colorTester extends autoBaseV5A {

    ArrayList<Integer> colorAvgG = new ArrayList<>();
    ArrayList<Integer> colorAvgB = new ArrayList<>();
    ArrayList<Integer> colorAvgR = new ArrayList<>();

    int totalG, totalB, totalR;
    int avgG, avgB, avgR;

    int iteration = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){


            telemetry.addData("Green Right: ", map.colorSensorRight.green());
            telemetry.addData("Blue Right: ", map.colorSensorRight.blue());
            telemetry.addData("Red Right: ", map.colorSensorRight.red());

            telemetry.addData("Alpha Right: ", map.colorSensorRight.alpha());

            telemetry.addData("Green Ground: ", map.bottomColorSensor.green());
            telemetry.addData("Blue Ground: ", map.bottomColorSensor.blue());
            telemetry.addData("Red Ground: ", map.bottomColorSensor.red());
            telemetry.addData("Alpha Ground: ", map.bottomColorSensor.alpha());

            telemetry.addData("AVG G: ", avgG);
            telemetry.addData("AVG B: ", avgB);
            telemetry.addData("AVG R: ", avgR);

            telemetry.update();

            calibrate(map.bottomColorSensor);

        }
    }

    public void calibrate(ColorSensor cs){

        int baseG = cs.green();
        int baseB = cs.blue();
        int baseR = cs.red();

        totalG = totalG+baseG;
        totalB = totalB+baseB;
        totalR = totalR+baseR;

        avgG = (totalG/iteration);
        avgB = (totalB/iteration);
        avgR = (totalR/iteration);

        iteration++;

//        sleep(5);

    }
}