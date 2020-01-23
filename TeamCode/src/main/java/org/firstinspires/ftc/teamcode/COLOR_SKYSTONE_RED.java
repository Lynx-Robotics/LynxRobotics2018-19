package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "RED_SKYSTONE_COLOR", group = "COLOR")
public class COLOR_SKYSTONE_RED extends autoBase {

    boolean aBtn = true;
    boolean objDetected = false;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        while (!chart.imu.isGyroCalibrated() && opModeIsActive()) {
            telemetry.addData("Calibrating", "...");
            telemetry.update();
        }

        telemetry.addData("objDetectedAlpha: ", objDetectedAlpha(0.70, chart.colorSensorRight, chart.colorSensorLeft));
        telemetry.update();

        waitForStart();

        goForward(.15);
        //goFront(-0.15);
        while(opModeIsActive() && !objDetected){
            telemetry.addData("Finding an object", "...");
            telemetry.addData("Object Found?",objDetectedAlpha(0.05, chart.colorSensorRight, chart.colorSensorLeft));
            telemetry.addData("ObjDetected Variable: ", objDetected);
            telemetry.update();

            toggleObjDetected(objDetectedAlpha(0.10, chart.colorSensorRight, chart.colorSensorLeft));
        }
        rest();

        goForward(-0.175);
        wait(0.12);

        strafeLeft(-0.175);
        //while(opModeIsActive()&&!SkyStoneSpottedOld(chart.colorSensorLeft));
        while(opModeIsActive()&&!SkyStoneSpotted(chart.colorSensorLeft, 5));

        rest();
    }

    public double pChangeColor(ColorSensor cs) {
        double GValNew;
        double BValNew;
        double RValNew;

        double GValBase = cs.green();
        double BValBase = cs.blue();
        double RValBase = cs.red();

        wait(0.01);

        GValNew = cs.green();
        BValNew = cs.blue();
        RValNew = cs.red();

        double pChangeGreen = (GValNew - GValBase) / (GValBase);
        double pChangeBlue = (BValNew - BValBase) / (BValBase);
        double pChangeRed = (RValNew - RValBase) / (RValBase);

        double pChangeAvg = (pChangeBlue + pChangeGreen + pChangeRed) / 3.0;

        return pChangeAvg;
    }

    public double pChangeAlpha(ColorSensor cs) {
        double alphaNew;
        double alphaBase = cs.alpha();
        wait(0.01);
        //sleep(1);
        alphaNew = cs.alpha();

        double pChangeAlpha = (alphaNew - alphaBase) / alphaBase;
        return pChangeAlpha;

    }

    public double avgPChangeAlpha(ColorSensor csRight, ColorSensor csLeft){
        double pChangeAlphaLeft = pChangeAlpha(csLeft);
        double pChangeAlphaRight = pChangeAlpha(csRight);

        return (pChangeAlphaLeft + pChangeAlphaRight)/2.0;
    }

    public boolean objDetectedAlpha(double threshold, ColorSensor csRight, ColorSensor csLeft){
        return threshold < avgPChangeAlpha(csRight, csLeft); //75% change minimum
        //return ((threshold < pChangeAlpha(csLeft)) && (threshold < pChangeAlpha(csRight)));
    }

    public void toggleObjDetected(boolean stateControl){
        if(stateControl == true){
            objDetected = !objDetected;
        }
        else {
            objDetected = objDetected;
        }
    }

    public void goFront(double power){
        chart.TL.setPower(power);
        chart.TR.setPower(power);
        chart.BL.setPower(power);
        chart.BR.setPower(power);
    }
}
