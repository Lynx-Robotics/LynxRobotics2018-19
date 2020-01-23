package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "REV V3 Color Sensor")
public class distanceSensorV3 extends LinearOpMode {

    ColorSensor sensorColorLeft;
    DistanceSensor sensorDistanceLeft;

    @Override
    public void runOpMode() throws InterruptedException {

        sensorColorLeft = hardwareMap.get(ColorSensor.class, "sensor");
        //sensorDistanceLeft = hardwareMap.get(DistanceSensor.class, "sensor");

        sensorColorLeft.enableLed(true);

        waitForStart();
        while(opModeIsActive()){
            //double distanceReported = sensorDistanceLeft.getDistance(DistanceUnit.CM);
            double hueReported = sensorColorLeft.argb();
            double greenReported = sensorColorLeft.green();
            double redReported = sensorColorLeft.red();
            double blueReported = sensorColorLeft.blue();
            double alphaReported = sensorColorLeft.alpha();

            //telemetry.addData("Reported Distance in CM: ", distanceReported);
            telemetry.addData("Reported Hue: ", hueReported);
            telemetry.addData("Reported Green: ", greenReported);
            telemetry.addData("Repored Red: ", redReported);
            telemetry.addData("Reported Blue: ", blueReported);
            telemetry.addData("Reported Alpha: ", alphaReported);
            telemetry.update();
        }

    }
}
