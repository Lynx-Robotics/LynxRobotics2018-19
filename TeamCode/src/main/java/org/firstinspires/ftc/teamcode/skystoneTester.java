package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "SkyStone Tester")
public class skystoneTester extends autoBaseV2 {
    ColorSensor csLeft;

    @Override
    public void runOpMode() throws InterruptedException {

        csLeft = hardwareMap.get(ColorSensor.class, "csLeft");
        csLeft.enableLed(true);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("SkyStoneBlack: ", !(SkyStoneSpottedBlack(csLeft, 10)));
            telemetry.addData("SkyStone: ", !(SkyStoneSpottedAntiBlack(csLeft, 10)));

            telemetry.addData("SkyStone Detected Old: ", !SkyStoneSpotted(csLeft));
            telemetry.addData("SkyStone Detected Green Method: ", !SkyStoneSpottedGreen(csLeft));
            telemetry.addData("Objected Detected General", objDetected(csLeft));

            telemetry.addData("Green: ", csLeft.green());
            telemetry.addData("Alpha: ", csLeft.alpha());

            telemetry.update();
        }
    }

    public boolean objDetected(ColorSensor cs){
        if(opModeIsActive()&&cs.alpha() > 800){
            return false;
        }
        else {
            return true;
        }
    }
}
