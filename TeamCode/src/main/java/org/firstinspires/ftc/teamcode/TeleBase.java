package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

public abstract class TeleBase extends LinearOpMode {
    TypexChart chart = new TypexChart();

    public boolean grabberState = false, autoGrabState = false;
    public boolean aBtn = false, bBtn = false;
    public int side = 1;

    private double speedMultip = 1;


    public void toggleGrabber(boolean stateControl){
        if (stateControl) {
            grabberState = !grabberState;
        }
    }

    public double getSpeedMultip(double controlPower){
        return speedMultip - (controlPower*0.5);
    }

    public void toggleGrabber(){
        grabberState = !grabberState;
    }

    public void toggleAutoGrab(boolean stateControl){
        if (stateControl){
            autoGrabState = !autoGrabState;
        }
    }

    public boolean colorCheclerGreen(ColorSensor cs, int GVal, int tolerance) {

        return ((cs.green() < (GVal + tolerance)) && ((cs.green() > GVal - tolerance)));
    }

    public boolean colorCheclerBlue(ColorSensor cs, int BVal, int tolerance) {

        return ((cs.green() < (BVal + tolerance)) && ((cs.green() > BVal - tolerance)));
    }

    public boolean colorCheclerRed(ColorSensor cs, int RVal, int tolerance) {

        return ((cs.green() < (RVal + tolerance)) && ((cs.green() > RVal - tolerance)));
    }

    /*public boolean SkyStoneSpottedLeftPercentBased(){
        double percentChange = percentChange(chart.colorSensorLeft);
    }*/

    public boolean SkystonePercent(ColorSensor cs){
        int HUE = 16777216;
        int GVal = 28;
        int BVal = 18;
        int RVal = 14;

        int tolerance = 4;

        double percentChange = percentChange(cs);
        if (percentChange>50 && !(colorCheclerGreen(cs, GVal, tolerance) && colorCheclerBlue(cs, BVal, tolerance)
                && colorCheclerRed(cs, RVal, tolerance))){
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

    public boolean SkyStoneSpottedLeft() {
        int HUE = 16777216;
        int GVal = 28;
        int BVal = 18;
        int RVal = 14;

        int tolerance = 4;

        if (colorCheclerGreen(chart.colorSensorLeft, GVal, tolerance) && colorCheclerBlue(chart.colorSensorLeft, BVal, tolerance) && colorCheclerRed(chart.colorSensorLeft, RVal, tolerance)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean SkyStoneSpottedRight() {
        int HUE = 16777216;
        int GVal = 15;
        int BVal = 14;
        int RVal = 11;

        int tolerance = 3;

        if (colorCheclerGreen(chart.colorSensorRight, GVal, tolerance) && colorCheclerBlue(chart.colorSensorRight, BVal, tolerance) && colorCheclerRed(chart.colorSensorRight, RVal, tolerance)) {
            return true;
        } else {
            return false;
        }
    }

    public void grabServo() {
        chart.middleGrab.setPosition(1.0);
    }

    public void letGoServo() {
        chart.middleGrab.setPosition(0.0);
    }

    public boolean tapeSpotted() {
        //Record values for the ground and return false when viewing the grey ground

        int GVal = 50;
        int BVal = 50;
        int RVal = 50;
        int tolerance = 25;

        if ((colorCheclerGreen(chart.bottomColorSensor, GVal, tolerance)) &&
                colorCheclerBlue(chart.bottomColorSensor, BVal, tolerance) &&
                colorCheclerRed(chart.bottomColorSensor, RVal, tolerance)) {
            return false;
        } else {
            return true;
        }
    }

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

    public double percentChange(ColorSensor cs){
        double GValNew;
        double BValNew;
        double RValNew;

        double GValBase = cs.green();
        double BValBase = cs.blue();
        double RValBase = cs.red();

        sleep(40);

        GValNew = cs.green();
        BValNew =cs.blue();
        RValNew = cs.red();

        double deltaGreen = ((GValNew-GValBase)/GValBase);
        double deltaBlue = ((BValNew-BValBase)/BValBase);
        double deltaRed = ((RValNew-RValBase)/RValBase);

        double avgPercentDelta = (deltaGreen+deltaGreen+deltaRed)/3;

        return avgPercentDelta;
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


    public void smartGrab(){
        if (autoGrabState){ //if automatic grabbing then do the following
            if((!SkyStoneSpottedLeft() && !SkyStoneSpottedRight()) && !crossSide()){ //if Black is spotted in left and right color sensor and on grabSide do the following
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
            if((!SkyStoneSpottedLeft() && !SkyStoneSpottedRight()) && !crossSide()){ //if Black is spotted in left and right color sensor and on grabSide do the following
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

}
