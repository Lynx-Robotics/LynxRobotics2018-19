package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public abstract class autoBase extends LinearOpMode {
    TypexChart chart = new TypexChart();
    CONSTANTS constants = new CONSTANTS();

    boolean objDetected = false;

    /*
    ------------------------------------------------------------------------------------------
    Drive Methods Starting NOW
    ------------------------------------------------------------------------------------------
     */

    public void goForward() {
        chart.TL.setPower(-(chart.power - chart.correction));
        chart.BL.setPower(-(chart.power - chart.correction));
        chart.TR.setPower(-(chart.power + chart.correction));
        chart.BR.setPower(-(chart.power + chart.correction));
    }

    public void goForward(double power1){
        chart.TL.setPower(-((power1-0.0550) - chart.correction));
        chart.BL.setPower(-((power1) - chart.correction));
        chart.TR.setPower(-(power1 + chart.correction));
        chart.BR.setPower(-(power1 + chart.correction));
    }

    public void goBack() {
        chart.TL.setPower((chart.power - chart.correction));
        chart.BL.setPower((chart.power - chart.correction));
        chart.TR.setPower((chart.power + chart.correction));
        chart.BR.setPower((chart.power + chart.correction));
    }

    public void goBack(double power) {
        chart.TL.setPower((power - chart.correction));
        chart.BL.setPower((power - chart.correction));
        chart.TR.setPower((power + chart.correction));
        chart.BR.setPower((power + chart.correction));
    }

    public void strafeRight() {
        chart.TL.setPower(chart.powerDown);
        chart.TR.setPower(chart.powerUp);
        chart.BL.setPower(chart.powerUp);
        chart.BR.setPower(chart.powerDown);
    }

    public void strafeLeft(double power) {
        chart.TL.setPower(power);
        chart.TR.setPower(-power + 0.004);
        chart.BL.setPower(-power);
        chart.BR.setPower(power);
    }

    public void strafeLeft() {
        chart.TL.setPower(chart.powerUp + joltControl(chart.runtime));
        chart.TR.setPower(chart.powerDown);
        chart.BL.setPower(chart.powerDown);
        chart.BR.setPower(chart.powerUp);
    }

    public double joltControl(ElapsedTime runtime) {
        if (runtime.seconds() < 1.0) {
            return 0.0050;
        } else {
            return 0.0;
        }
    }

    public void rest() {
        chart.TL.setPower(0);
        chart.TR.setPower(0);
        chart.BL.setPower(0);
        chart.BR.setPower(0);
    }

    public void strafeRight(double power) {
        chart.TL.setPower(-power);
        chart.TR.setPower(power);
        chart.BL.setPower(power);
        chart.BR.setPower(-power);
    }

    public void strafeLeftFullThrottle(){
        chart.TL.setPower(1.0);
        chart.BL.setPower(-1.0);
        chart.BR.setPower(-1.0);
        chart.TR.setPower(1.0);
    }

    public void strafeRightFullThrottle(){
        chart.TL.setPower(-1.0);
        chart.BL.setPower(1.0);
        chart.BR.setPower(1.0);
        chart.TR.setPower(-1.0);
    }
/*
------------------------------------------------------------------------------------------
Drive Methods Ending NOw
------------------------------------------------------------------------------------------
 */

    /*
    ------------------------------------------------------------------------------------------
    Time Control / State Control Methods Starting NOW
    ------------------------------------------------------------------------------------------
     */
    /*public int parkState(double triggerDist) {
        if (opModeIsActive() && (chart.distanceSensor.getDistance(DistanceUnit.CM) < triggerDist)) {
            return 2;
        } else {
            return 1;
        }
    }*/

    public void wait(double seconds, String phase) {
        ElapsedTime Intruntime = new ElapsedTime();
        Intruntime.reset();
        while (opModeIsActive() && Intruntime.seconds() < seconds) {
            telemetry.addData("Status: ", phase);
            telemetry.update();
        }
        //sleep(500);
    }

    public void wait(double seconds) {
        ElapsedTime Intruntime = new ElapsedTime();
        Intruntime.reset();
        while (opModeIsActive() && Intruntime.seconds() < seconds) {
            /*telemetry.addData("Status: ", "Executing the current Phase");
            telemetry.update();*/
        }
    }

    public boolean detectsYellow(ColorSensor cs){
        boolean green = isInRange(cs.green(), 2, 36.5);
        boolean blue = isInRange(cs.blue(), 3, 23.75);
        boolean red = isInRange(cs.red(), 4, 56.25);

        boolean hue = isInRange(cs.argb(), 10000, 33554432);

        boolean threeColorsMatch = green && blue && red;

        boolean alpha = isInRange(cs.alpha(), 10, 113.5);

        return alpha && threeColorsMatch;
    }

    /*
    ------------------------------------------------------------------------------------------
    Time Control / State Control Methods Ending NOW
    ------------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------------
    SENSOR CONTROL METHODS STARTING NOW
    ------------------------------------------------------------------------------------------
     */

    /*public boolean timeoutDistSensor() {
        ElapsedTime internalTime = new ElapsedTime();
        internalTime.reset();
        if ((isInRange(chart.distanceSensor.getDistance(DistanceUnit.CM), 10, 30)) && (internalTime.seconds() < 4)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean tripWireActive(double triggerDist) {
        if (chart.distanceSensor.getDistance(DistanceUnit.CM) < triggerDist) {
            return true;
        } else {
            return false;
        }
    }*/

    public boolean colorCheclerGreen(ColorSensor cs, int GVal, int tolerance) {

        return ((cs.green() < (GVal + tolerance)) && ((cs.green() > GVal - tolerance)));
    }

    public boolean colorCheclerBlue(ColorSensor cs, int BVal, int tolerance) {

        return ((cs.green() < (BVal + tolerance)) && ((cs.green() > BVal - tolerance)));
    }

    public boolean colorCheclerRed(ColorSensor cs, int RVal, int tolerance) {

        return ((cs.green() < (RVal + tolerance)) && ((cs.green() > RVal - tolerance)));
    }

    /*public boolean tapeSpotted() {
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
            sleep(50);
            return true;
        }
    }*/

    public boolean SkyStoneSpottedOld(ColorSensor colorSensor){
        int HUE = 16777216;
        int GVal = 14;
        int BVal = 10;
        int RVal = 10;

        int tolerance = 10;

        if (colorCheclerGreen(colorSensor, GVal, tolerance) && colorCheclerBlue(colorSensor, BVal, tolerance) && colorCheclerRed(colorSensor, RVal, tolerance)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean SkyStoneSpotted(ColorSensor cs, int tolerance) {
        int HUE = 16777216;
        int GVal = 23;
        int BVal = 21;
        int RVal = 23;

        boolean alpha = isInRange(cs.alpha(), 10, 60);

        boolean colorMatch = false;
        boolean alphaMatch = cs.alpha()>75;

        if (colorCheclerGreen(cs, GVal, tolerance) && colorCheclerBlue(cs, BVal, tolerance) && colorCheclerRed(cs, RVal, tolerance)) {
            colorMatch = true;
        } else {
            colorMatch = false;
        }

        return colorMatch && alphaMatch;
    }

    /*
    ------------------------------------------------------------------------------------------
    SENSOR CONTROL METHODS ENDING NOW
    ------------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------------
    SERVO CONTROL METHODS STARTING NOW
    ------------------------------------------------------------------------------------------
     */
    /*public void grabServo() {
        chart.middleGrab.setPosition(1.0);
    }*/

    /*public void dropDL() {
        chart.hookLeft.setPosition(0.1);
        chart.hookRight.setPosition(0.1);
    }*/

    /*public void raiseDL() {
        chart.hookLeft.setPosition(0.9);
        chart.hookRight.setPosition(0.9);
        chart.middleGrab.setPosition(0.0);
    }*/

    /*
    ------------------------------------------------------------------------------------------
    SERVO CONTROL METHODS ENDING NOW
    ------------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------------
    IMU Methods HERE
    ------------------------------------------------------------------------------------------
     */
    private void resetAngle() {
        chart.lastAngles = chart.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        chart.globalAngle = 0;
    }

    private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = chart.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - chart.lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        chart.globalAngle += deltaAngle;

        chart.lastAngles = angles;

        return chart.globalAngle;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 359 degrees.
     *
     * @param degrees Degrees to turn, + is left - is right
     */
    public void rotate(int degrees, double power) {
        // restart imu angle tracking.
        resetAngle();

        // if degrees > 359 we cap at 359 with same sign as original degrees.
        if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);

        // start pid controller. PID controller will monitor the turn angle with respect to the
        // target angle and reduce power as we approach the target angle. This is to prevent the
        // robots momentum from overshooting the turn after we turn off the power. The PID controller
        // reports onTarget() = true when the difference between turn angle and target angle is within
        // 1% of target (tolerance) which is about 1 degree. This helps prevent overshoot. Overshoot is
        // dependant on the motor and gearing configuration, starting power, weight of the robot and the
        // on target tolerance. If the controller overshoots, it will reverse the sign of the output
        // turning the robot back toward the setpoint value.

        chart.pidRotate.reset();
        chart.pidRotate.setSetpoint(degrees);
        chart.pidRotate.setInputRange(0, degrees);
        chart.pidRotate.setOutputRange(0, power);
        chart.pidRotate.setTolerance(1);
        chart.pidRotate.enable();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        // rotate until turn is completed.

        if (degrees < 0) {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getAngle() == 0) {
                chart.TL.setPower(power);
                chart.BL.setPower(power);
                chart.TR.setPower(-power);
                chart.BR.setPower(-power);
                sleep(100);
            }

            do {
                power = chart.pidRotate.performPID(getAngle()); // power will be - on right turn.
                chart.TL.setPower(-power);
                chart.BL.setPower(-power);
                chart.TR.setPower(power);
                chart.BR.setPower(power);
            } while (opModeIsActive() && !chart.pidRotate.onTarget());
        } else    // left turn.
            do {
                power = chart.pidRotate.performPID(getAngle()); // power will be + on left turn.
                chart.TL.setPower(-power);
                chart.BL.setPower(-power);
                chart.TR.setPower(power);
                chart.BR.setPower(power);
            } while (opModeIsActive() && !chart.pidRotate.onTarget());

        // turn the motors off.
        chart.TL.setPower(0);
        chart.TR.setPower(0);
        chart.BL.setPower(0);
        chart.BR.setPower(0);

        chart.rotation = getAngle();

        // wait for rotation to stop.
        sleep(500);

        // reset angle tracking on new heading.
        resetAngle();
    }
    /*
    ------------------------------------------------------------------------------------------
    IMU Methods found at the end of here
    ------------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------------
    MISC
    ------------------------------------------------------------------------------------------
     */

    public boolean isInRange(double number, double tolerance, double targ){
        if(((targ+tolerance)>number)&&((targ-tolerance)<number)){

            return true;
        }else{
            return false;
        }
    }

    /*public boolean isInRange(double number, double tolerance){
        return (number < (targ+tolerance)) && (number>(targ - tolerance));
    }
*/
    public void toggleObjDetected(boolean stateControl){
        if(stateControl == true){
            objDetected = !objDetected;
        }
        else {
            objDetected = objDetected;
        }
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

    /*public boolean objDetectedColor(){
        return detectsYellow(chart.colorSensorLeft) || detectsYellow(chart.colorSensorRight); //75% change minimum
        //return ((threshold < pChangeAlpha(csLeft)) && (threshold < pChangeAlpha(csRight)));
    }*/
    /*
    ------------------------------------------------------------------------------------------
    MISC
    ------------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------------
    LARGER METHODS (FOR GREATER PURPOSES)
    ------------------------------------------------------------------------------------------
     */

    /*public void park(double triggerDist) {
        switch (parkState(triggerDist)) {
            case 1:
                goForward();
                while (!tapeSpotted()) {
                }
                rest();
                break;
            case 2:
                rotate(90, chart.power);
                goForward();
                wait(0.5);
                rest();
                sleep(500);
                rotate(-90, chart.power);
                goForward();
                while (!tapeSpotted()) {
                }
                rest();
                break;
        }
    }*/

    /*
    ------------------------------------------------------------------------------------------
    Encoder Control Methods
    ------------------------------------------------------------------------------------------
     */

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public void goToPosition(DcMotor motor, int position, double power) {
        resetEncoders(motor);

        int motorPosition = motor.getCurrentPosition();

        motor.setPower(power - sqaureRootPowerControl(motor, position, 3.0));

        while (motorPosition <= position) {
            telemetry.addData("Current Position: ", motor.getCurrentPosition());
            telemetry.update();

            motorPosition = motor.getCurrentPosition();
        }
        //resetEncoders(motor);
        motor.setPower(0);
        chart.DebugSwitch = true;
    }

    public void encoderDrive(int position, double power){
        /*powerControl(0.00291, position, 0.25, chart.TL);
        powerControl(0.00291, position, 0.25, chart.TR);
        powerControl(0.00291, position, 0.25, chart.BL);
        powerControl(0.00291, position, 0.25, chart.BR);*/

        goToPosition(chart.TL, position, power);
        goToPosition(chart.TR, position, power);
        goToPosition(chart.BL, position, power);
        goToPosition(chart.BR, position, power);
    }

    public int encoderTicksForDistance(double distanceTarg){ //ensure that distance is in centimeters
        return (int)(distanceTarg * constants.ticksPerCm);
    }

    //powerControl proportional and linear
    public double sqaureRootPowerControl(DcMotor motor, int target, double tuning){
        //tuning should be three for experimental
        int currentPos = motor.getCurrentPosition();
        double pError = (double)(currentPos - target) / (double)target;

        double underSqrt = tuning * pError;
        double squareRootResult = Math.sqrt(underSqrt);
        double correctionPower = 1 / (squareRootResult);

        return 1 - correctionPower;


    }

    public double powerControl(double tuningConstant, int position, double thresholdPercent, DcMotor motor){
        int currentPosition = motor.getCurrentPosition();
        double pError = (double)(currentPosition - position)/(double)position;

        if((pError < thresholdPercent) && (pError > 0.05)) {
            return pError * tuningConstant;
        }
        else {
            return pError * 0;
        }
    }
}