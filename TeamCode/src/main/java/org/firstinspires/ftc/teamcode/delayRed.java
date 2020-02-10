package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "delayRed")
public class delayRed extends autoBaseV3 {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        sleep(25000);
        strafeLeft(.378);
        while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor)){

        }
        rest();
    }
}
