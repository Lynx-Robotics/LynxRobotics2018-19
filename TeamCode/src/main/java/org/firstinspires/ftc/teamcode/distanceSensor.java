package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Distance Sensor")
public class distanceSensor extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Distance in CM: ", chart.distanceSensor.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}
