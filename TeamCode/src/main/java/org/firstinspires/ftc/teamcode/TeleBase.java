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

    public boolean grabberState = false, autoGrabState = false;
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
        if (percentChange > .35){
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

    public boolean crossSide(){
        if(tapeSpottedPercentBase(.35)){
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
        sleep(10);
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

        sleep(10);

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

    public void smartGrab(){
        if (autoGrabState){ //if automatic grabbing then do the following
            if((!SkyStoneBase(chart.colorSensorLeft) && !SkyStoneBase(chart.colorSensorRight)) && !crossSide()){ //if Black is spotted in left and right color sensor and on grabSide do the following
                grabberState = true;
            }
            else if(grabberState && crossSide()){
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

    public void smartGrabPChange(){
        if (autoGrabState){ //if automatic grabbing then do the following
            if((!SkyStoneBase(chart.colorSensorLeft) && !SkyStoneBase(chart.colorSensorRight)) && !crossSide()){ //if Black is spotted in left and right color sensor and on grabSide do the following
                grabberState = true;
            }
            else if(grabberState && crossSide()){
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

    /*
    ------------------------------------------------------------------------------------
    [SMART AUTONOMOUS METHODS] END
    ------------------------------------------------------------------------------------
     */

}
