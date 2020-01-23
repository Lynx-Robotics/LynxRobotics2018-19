package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "operationControl", group = "smart")
public class operationControl extends TeleBase {
    boolean aBtn2 = false;
    boolean leftBumper2 = false;

    double thresholdBottom = 0.25, thresholdStone = 0.05;

    boolean isInRangeRedLeft = false, isInRangeBlueLeft = false, isInRangeGreenLeft = false;
    boolean isInRangeRedRight = false, isInInRangeBlueRight = false, isInRangeGreenRight = false;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            aBtn2 = gamepad2.right_bumper;
            leftBumper2 = gamepad2.left_bumper;
            telemetry.addData("Automatic Grabbing State: ", autoGrabState);
            telemetry.addData("Grab State: ", grabberState);
            //telemetry.addData("Tape Spotted: ", !tapeSpotted());
            telemetry.addData("Are we on the scoring side? ", crossSide(thresholdBottom));
            //telemetry.addData("Here is the speedMultip: ", getSpeedMultip(gamepad1.right_trigger));
            //telemetry.addData("Percent Change Color: ", percentChange(chart.bottomColorSensor));
            //telemetry.addData("tapeSpotted: ", tapeSpottedPercentBase(35));
            //telemetry.addData("Left Color Sensor Percent Change: ", percentChange(chart.colorSensorLeft));
            //telemetry.addData("Right Color Sensor Percent Change: ", percentChange(chart.colorSensorRight));
            //telemetry.addData("Skystone Spotted: ", SkyStoneBase(chart.colorSensorLeft));
            //telemetry.addData("SkyStone Spotted: ", SkyStoneBase(chart.colorSensorRight));
            //telemetry.addData("Is the SkyStone Spotted: ", SkystonePercentAlpha(chart.colorSensorLeft));
            /*telemetry.addData("Objected Detected Left: ", objectDetectedColor(chart.colorSensorLeft));
            telemetry.addData("objected Detected Right: ", objectDetectedColor(chart.colorSensorRight));*/
            telemetry.addData("Is In Range Left: ", isInRange(chart.colorSensorLeft.argb(), 7216, 16777216));
            telemetry.addData("Is in Range Right: ", isInRange(chart.colorSensorRight.argb(), 7216, 16777216));
            telemetry.update();

            toggleAutoGrab(aBtn2);
            //toggleGrabberNew(leftBumper2);
            //toggleHaveIGrabbed(objectDetectedColor(chart.colorSensorRight));

            if(grabberState){
                chart.middleGrab.setPosition(1.0);
            }
            else {
                chart.middleGrab.setPosition(0.0);
            }

            isInRangeGreenLeft = isInRangeGreen1(chart.colorSensorLeft, 4);
            isInRangeBlueLeft = isInRangeBlue1(chart.colorSensorLeft, 3);
            isInRangeRedLeft = isInRangeRed1(chart.colorSensorLeft, 5);
            isInRangeGreenRight = isInRangeGreen1(chart.colorSensorRight,4);
            isInInRangeBlueRight = isInRangeBlue1(chart.colorSensorRight, 3);
            isInRangeRedRight = isInRangeRed1(chart.colorSensorRight, 5);


            chart.TL.setPower(gamepad1.right_stick_y * getSpeedMultip(gamepad1.right_trigger));
            chart.BL.setPower(gamepad1.right_stick_y * getSpeedMultip(gamepad1.right_trigger));
            chart.TR.setPower(gamepad1.left_stick_y * getSpeedMultip(gamepad1.right_trigger));
            chart.BR.setPower(gamepad1.left_stick_y * getSpeedMultip(gamepad1.right_trigger));

            //autoGrab(chart.colorSensorLeft, chart.colorSensorRight);
            //smartGrabV3(thresholdStone, SkyStoneDetectedLeft(isInRangeGreenLeft, isInRangeBlueLeft, isInRangeRedLeft), SkyStoneDetectedRight(isInRangeGreenRight, isInInRangeBlueRight, isInRangeRedRight));
            smartGrabV4();

            telemetry.update();
        }
    }

    public boolean detectsYellow(ColorSensor cs){
        boolean green = isInRange(cs.green(), 7, 36.5);
        boolean blue = isInRange(cs.blue(), 4, 23.75);
        boolean red = isInRange(cs.red(), 10, 56.25);

        boolean threeColorsMatch = green && blue && red;

        boolean alpha = isInRange(cs.alpha(), 30, 113.5);
        boolean hue = isInRange(cs.argb(), 10000, 33554432);


        return (alpha && threeColorsMatch)  &&  hue;
    }

    public void smartGrabV5(){
        if(!detectsYellow(chart.colorSensorLeft)){
            grabberState = true;
        }
        else if(crossSide(thresholdBottom) == false);
    }

    public void smartGrabV4(){
        if(isInRange(chart.colorSensorLeft.argb(), 7216, 16777216) && isInRange(chart.colorSensorRight.argb(), 7216, 16777216)){
            grabberState = true;
        }
        else if (crossSide(thresholdBottom) == false){
            grabberState = false;
        }
    }

    public boolean isInRangeGreen1(ColorSensor cs, double tolerance){
        double avgGreen = 17.583333;
        if ((cs.green() < (avgGreen-tolerance)) && (cs.green() > (avgGreen-tolerance))){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isInRangeBlue1(ColorSensor cs, double tolerance){
        double avgBlue = 15.583333;
        if ((cs.green() < (avgBlue-tolerance)) && (cs.green() > (avgBlue-tolerance))){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isInRangeRed1(ColorSensor cs, double tolerance){
        double avgRed = 18.25;
        if ((cs.green() < (avgRed-tolerance)) && (cs.green() > (avgRed-tolerance))){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isInRange(double number, double tolerance, double targ){
        if(((targ+tolerance)>number)&&((targ-tolerance)<number)){

            return true;
        }else{
            return false;
        }
    }

    public boolean SkyStoneDetectedLeft(boolean isInRangeGreen,
                                        boolean isInRangeBlue,
                                        boolean isInRangeRed){

        if(/*(isInRangeBlue && isInRangeRed && isInRangeGreen) || */isInRange(chart.colorSensorLeft.argb(), 7216, 16777216)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean SkyStoneDetectedRight(boolean isInRangeGreen,
                                         boolean isInRangeBlue,
                                         boolean isInRangeRed){

        if(/*(isInRangeBlue && isInRangeRed && isInRangeGreen) || */isInRange(chart.colorSensorRight.argb(), 7216, 16777216) ){
            return true;
        }
        else {
            return false;
        }
    }
}
