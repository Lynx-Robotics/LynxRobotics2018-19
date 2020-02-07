package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "blueTapeTest")
public class strafeColorTester extends autoBaseV3 {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        while(opModeIsActive() && iteration!=1200) calibrate(chart.bottomColorSensor);

        waitForStart();

        strafeLeft(0.27);
        while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor)){

        }
        rest();
    }

    public boolean detectNonGrey(){
        boolean G = isInRange(chart.bottomColorSensor.green(), 400, avgG);
        boolean B = isInRange(chart.bottomColorSensor.blue(), 400, avgB);
        boolean R = isInRange(chart.bottomColorSensor.red(), 400, avgR);

        return G && B && R;
    }
}
