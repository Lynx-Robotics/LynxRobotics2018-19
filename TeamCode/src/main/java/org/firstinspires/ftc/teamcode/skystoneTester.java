package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "SkyStone Tester")
public class skystoneTester extends autoBaseV2 {


    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("SkyStoneBlack: ", !(SkyStoneSpottedBlack(chart.colorSensorLeft, 10) || SkyStoneSpottedBlack(chart.colorSensorRight, 10)));
            telemetry.addData("SkyStone: ", !(SkyStoneSpottedAntiBlack(chart.colorSensorLeft, 10) && SkyStoneSpottedBlack(chart.colorSensorLeft,10)));

        }
    }
}
