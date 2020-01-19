package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

public abstract class TeleBase extends LinearOpMode {
    TypexChart chart = new TypexChart();
    /*
    ------------------------------------------------------------------------------------
    [STATE VARIABLES] START
    ------------------------------------------------------------------------------------
     */

    public boolean grabberState = false, autoGrabState = false, sensorDetectLeft = false, sensorDetectRight = false;
    public boolean aBtn = false, bBtn = false;
    public int side = 1;

    /*
    ------------------------------------------------------------------------------------
    [STATE VARIABLES] END
    ------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------
    [DYNAMIC VARIABLES] START
    ------------------------------------------------------------------------------------
     */

    private double speedMultip = 1;

    /*
    ------------------------------------------------------------------------------------
    [DYNAMIC VARIABLES] END
    ------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------
    [TOGGLE METHODS] START
    ------------------------------------------------------------------------------------
     */
    public void toggleSensorLeft(boolean stateControl){
        if(stateControl){
            sensorDetectLeft = !sensorDetectLeft;
        }
    }

    public void toggleSensorRight(boolean stateControl){
        if(stateControl){
            sensorDetectRight = !sensorDetectRight;
        }
    }

    public void toggleGrabber(boolean stateControl){
        if (stateControl) {
            grabberState = !grabberState;
        }
    }

    public void toggleGrabber(){
        grabberState = !grabberState;
    }

    public void toggleAutoGrab(boolean stateControl){
        if (stateControl){
            autoGrabState = !autoGrabState;
        }
    }

    public void toggleGrabberNew(boolean stateControl){
        if (stateControl){
            grabberState = !grabberState;
        }
    }

    public void toggleTolerance(boolean stateCotrol, double change){
        if (stateCotrol){
            change++;
        }
    }

    public void toggleHaveIGrabbed(boolean stateControl){
        if (stateControl){
            grabberState = !grabberState;
        }
    }

    public void toggleHaveIGrabbed(boolean stateControl1, boolean stateControl2){
        if (stateControl1 || stateControl2){
            grabberState = !grabberState;
        }
    }

    /*
    ------------------------------------------------------------------------------------
    [TOGGLE METHODS] END
    ------------------------------------------------------------------------------------
     */

    public double getSpeedMultip(double controlPower){
        return speedMultip - (controlPower*0.5);
    }

    /*
    ------------------------------------------------------------------------------------
    [SLYSTONE DETECTION METHODS] START
    ------------------------------------------------------------------------------------
     */

    public boolean colorCheclerGreen(ColorSensor cs, int GVal, int tolerance) {

        return ((cs.green() < (GVal + tolerance)) && ((cs.green() > GVal - tolerance)));
    }

    public boolean colorCheclerBlue(ColorSensor cs, int BVal, int tolerance) {

        return ((cs.green() < (BVal + tolerance)) && ((cs.green() > BVal - tolerance)));
    }

    public boolean colorCheclerRed(ColorSensor cs, int RVal, int tolerance) {

        return ((cs.green() < (RVal + tolerance)) && ((cs.green() > RVal - tolerance)));
    }

    public boolean SkystonePercentAlpha(ColorSensor cs){

        double percentChange = percentChangeAlpha(cs);
        if (percentChange > .2){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean SkyStoneBase(ColorSensor cs){
        int HUE = 16777216;
        int GVal = 28;
        int BVal = 18;
        int RVal = 14;

        int tolerance = 4;
        return (colorCheclerGreen(cs, GVal, tolerance) && colorCheclerBlue(cs, BVal, tolerance)
                && colorCheclerRed(cs, RVal, tolerance));
    }

    /*
    ------------------------------------------------------------------------------------
    [SKYSTONE DETECTION METHODS] END
    ------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------
    [SERVO CONTROL METHODS] START
    ------------------------------------------------------------------------------------
     */

    public void grabServo() {
        chart.middleGrab.setPosition(1.0);
    }

    public void letGoServo() {
        chart.middleGrab.setPosition(0.0);
    }

    /*
    ------------------------------------------------------------------------------------
    [SERVO CONTROL METHODS] END
    ------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------
    [FIELD NAVIGATION METHODS] START
    ------------------------------------------------------------------------------------
     */
    public boolean objectDetected(ColorSensor cs){
        double percentChange = percentChangeAlpha(cs);

        if (percentChange > 0.5){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean objectDetectedColor(ColorSensor cs){
        double percentChange = percentChange(cs);

        if (percentChange > 0.15){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean crossSide(double threshold){
        if(tapeSpottedPercentBase(threshold)){
            side++;
        }
        if((side%2) == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean tapeSpottedPercentBase(double percentThreshold){
        double percentChange = percentChange(chart.bottomColorSensor);
        if (percentChange>percentThreshold){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    ------------------------------------------------------------------------------------
    [FIELD NAVIGATION METHODS] END
    ------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------
    [COLOR SENSOR LOGIC METHODS] START
    ------------------------------------------------------------------------------------
     */

    public double percentChangeAlpha(ColorSensor cs){
        double alphaNew;
        double alphaBase = cs.alpha();
        sleep(30);
        alphaNew = cs.alpha();

        double percentChange = (alphaNew-alphaBase)/alphaBase;
        return percentChange;
    }

    public double percentChange(ColorSensor cs){
        double GValNew;
        double BValNew;
        double RValNew;

        double GValBase = cs.green();
        double BValBase = cs.blue();
        double RValBase = cs.red();

        sleep(3);

        GValNew = cs.green();
        BValNew =cs.blue();
        RValNew = cs.red();

        double deltaGreen = ((GValNew-GValBase)/GValBase);
        double deltaBlue = ((BValNew-BValBase)/BValBase);
        double deltaRed = ((RValNew-RValBase)/RValBase);

        double avgPercentDelta = (deltaGreen+deltaGreen+deltaRed)/3;

        return avgPercentDelta;
    }

    /*
    ------------------------------------------------------------------------------------
    [COLOR SENSOR LOGIC METHODS] END
    ------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------
    [SMART AUTONOMOUS METHODS] START
    ------------------------------------------------------------------------------------
     */

    public void smartGrab(double threshold){
        if (autoGrabState){ //if automatic grabbing then do the following
            if((!SkyStoneBase(chart.colorSensorLeft) && !SkyStoneBase(chart.colorSensorRight)) && !crossSide(threshold)){ //if Black is spotted in left and right color sensor and on grabSide do the following
                grabberState = true;
            }
            else if(grabberState && crossSide(threshold)){
                letGoServo();
            }
        }
        else {
            if(grabberState){
                grabServo();
            }
            else{
                letGoServo();
            }
        }
    }

    public void autoGrab(ColorSensor csLeft, ColorSensor csRight, double threshold){
        if(autoGrabState){
            if(objectDetected(csLeft) || objectDetected(csRight)){
                chart.middleGrab.setPosition(0.0);
            }
            else if(!grabberState && crossSide(threshold)){
                toggleGrabber();
            }
            else {
                chart.middleGrab.setPosition(0.45);
            }
        }
    }

    public void smartGrabPChange(double threshold){
        if (autoGrabState){ //if automatic grabbing then do the following
            if((!SkyStoneBase(chart.colorSensorLeft) && !SkyStoneBase(chart.colorSensorRight)) && !crossSide(threshold)){ //if Black is spotted in left and right color sensor and on grabSide do the following
                grabberState = true;
            }
            else if(grabberState && crossSide(threshold)){
                letGoServo();
            }
        }
        else {
            if(grabberState){
                grabServo();
            }
            else{
                letGoServo();
            }
        }
    }

    public void smartGrabV2(double threshold){
        if(autoGrabState){
            if(crossSide(threshold) == true){
                toggleSensorLeft(objectDetectedColor(chart.colorSensorLeft));
                toggleSensorRight(objectDetectedColor(chart.colorSensorRight));
                toggleHaveIGrabbed(sensorDetectLeft, sensorDetectRight);

            }
            else if(crossSide(threshold) == false){
                grabberState = false;
            }
        }
    }

    public void smartGrabV3(double threshold, boolean stateControlLeft, boolean stateControlRight){
        if(autoGrabState){
            if(crossSide(threshold) == true){
                toggleSensorLeft(stateControlLeft);
                toggleSensorRight(stateControlRight);
                toggleHaveIGrabbed(sensorDetectLeft, sensorDetectRight);

            }
            else if(crossSide(threshold) == false){
                grabberState = false;
            }
        }
    }

    /*
    ------------------------------------------------------------------------------------
    [SMART AUTONOMOUS METHODS] END
    ------------------------------------------------------------------------------------
     */

}
