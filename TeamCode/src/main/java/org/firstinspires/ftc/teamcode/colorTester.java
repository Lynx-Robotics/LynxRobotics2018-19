package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.ArrayList;

@Autonomous(name = "colorTester")
public class colorTester extends autoBaseV2 {

    ArrayList<Integer> colorAvgG = new ArrayList<>();
    ArrayList<Integer> colorAvgB = new ArrayList<>();
    ArrayList<Integer> colorAvgR = new ArrayList<>();

    int totalG, totalB, totalR;
    int avgG, avgB, avgR;

    int iteration = 1;

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

            telemetry.addData("Green Ground: ", chart.bottomColorSensor.green());
            telemetry.addData("Blue Ground: ", chart.bottomColorSensor.blue());
            telemetry.addData("Red Ground: ", chart.bottomColorSensor.red());
            telemetry.addData("Alpha Ground: ", chart.bottomColorSensor.alpha());

            telemetry.addData("Distance (Left): ", chart.distSensorLeft.getDistance(DistanceUnit.MM));
            telemetry.addData("Distance (Right): ", chart.distSensorRight.getDistance(DistanceUnit.MM));

            telemetry.addData("AVG G: ", avgG);
            telemetry.addData("AVG B: ", avgB);
            telemetry.addData("AVG R: ", avgR);

            telemetry.addData("Bottom Sensor Tape Spotted: ", bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor));

            telemetry.update();

            calibrate(chart.bottomColorSensor);

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
