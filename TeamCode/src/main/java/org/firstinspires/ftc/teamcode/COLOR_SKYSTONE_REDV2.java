package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "PHASE2 - CROSS OVER - RED", group = "S-Side")
public class COLOR_SKYSTONE_REDV2 extends LinearOpMode {

    ColorSensor colorSensor;

    double thresholdBottom = 0.25, thresholdStone = 0.05;

    ColorSensor bottomColorSensor;

    int side = 1;

    boolean objDetected = false;
    boolean isInRangeRedLeft = false, isInRangeBlueLeft = false, isInRangeGreenLeft = false;
    boolean isInRangeRedRight = false, isInInRangeBlueRight = false, isInRangeGreenRight = false;

    Servo middleGrab;

    boolean grabberState = false;

    PIDController pidRotate, pidDrive;
    double globalAngle, power = .40, correction, rotation;
    Orientation lastAngles = new Orientation();
    BNO055IMU imu;
    DcMotor TL, TR, BL, BR;
    Servo hookLeft, hookRight;

    public double powerUp = 0.2, powerDown = -0.2;

    DistanceSensor distanceSensor;

    ColorSensor colorSensorRight, colorSensorLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        distanceSensor = hardwareMap.get(DistanceSensor.class, "dist");
        colorSensorLeft = hardwareMap.get(ColorSensor.class, "csLeft");
        colorSensorRight = hardwareMap.get(ColorSensor.class, "csRight");
        bottomColorSensor = hardwareMap.get(ColorSensor.class, "bcs");

        middleGrab = hardwareMap.get(Servo.class, "middleGrab");

        hookLeft = hardwareMap.get(Servo.class, "hookLeft");
        hookRight = hardwareMap.get(Servo.class, "hookRight");

        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        TR = hardwareMap.get(DcMotor.class, "TR");
        TL = hardwareMap.get(DcMotor.class, "TL");

        TL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        pidDrive = new PIDController(.05, 0, 0);
        pidRotate = new PIDController(.004, .00004, 0);
        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        while (!isStopRequested() && !imu.isGyroCalibrated()) {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
        telemetry.update();

        hookRight.setPosition(0.9);
        hookLeft.setPosition(0.9);

        middleGrab.setPosition(0.0);

        colorSensorRight.enableLed(true);
        colorSensorLeft.enableLed(true);

        waitForStart();

        /*
        ---------------------------------------------------------------------------------
        ACTION CODE (QZ0)
        ---------------------------------------------------------------------------------
         */
        goForward(0.14);
        //goFront(-0.15);
        while(opModeIsActive() && !objDetected){
            telemetry.addData("Finding an object", "...");
            telemetry.addData("Object Found?",objDetectedAlpha(0.20, colorSensorRight, colorSensorLeft));
            telemetry.addData("ObjDetected Variable: ", objDetected);
            telemetry.update();

            toggleObjDetected(objDetectedAlpha(0.05, colorSensorRight, colorSensorLeft));
        }
        rest();

        goForward(-0.15);
        wait(0.05, "Going back slightly");

        isInRangeGreenLeft = isInRangeGreen1(colorSensorLeft, 4);
        isInRangeBlueLeft = isInRangeBlue1(colorSensorLeft, 3);
        isInRangeRedLeft = isInRangeRed1(colorSensorLeft, 5);
        isInRangeGreenRight = isInRangeGreen1(colorSensorRight,4);
        isInInRangeBlueRight = isInRangeBlue1(colorSensorRight, 3);
        isInRangeRedRight = isInRangeRed1(colorSensorRight, 5);

        strafeLeft();
        /*while((!isInRange(colorSensorLeft.argb(), 700, 16777216) && !isInRange(colorSensorRight.argb(), 700, 16777216)) && !SkyStoneDetectedRight(isInRangeGreenRight, isInInRangeBlueRight, isInRangeRedRight)){
            isInRangeGreenLeft = isInRangeGreen1(colorSensorLeft, 4);
            isInRangeBlueLeft = isInRangeBlue1(colorSensorLeft, 3);
            isInRangeRedLeft = isInRangeRed1(colorSensorLeft, 5);
            isInRangeGreenRight = isInRangeGreen1(colorSensorRight,4);
            isInInRangeBlueRight = isInRangeBlue1(colorSensorRight, 3);
            isInRangeRedRight = isInRangeRed1(colorSensorRight, 5);
        }*/

        while (!detectsYellow(colorSensorLeft) && !detectsYellow(colorSensorRight)) {

        }
        rest();
    }

    public boolean detectsYellow(ColorSensor cs){
        boolean green = isInRange(cs.green(), 2, 36.5);
        boolean blue = isInRange(cs.blue(), 3, 23.75);
        boolean red = isInRange(cs.red(), 4, 56.25);

        boolean threeColorsMatch = green && blue && red;

        boolean alpha = isInRange(cs.alpha(), 10, 113.5);

        return alpha && threeColorsMatch;
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

    /*
    AVG Green Black= 	17.58333333
AVG Blue Black = 	15.58333333
AVG Red Black = 	18.25
AVG Alpha = 	50.83333333

     */

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

    public boolean SkyStoneDetectedLeft(boolean isInRangeGreen,
                                    boolean isInRangeBlue,
                                    boolean isInRangeRed){

        if(isInRangeBlue && isInRangeRed && isInRangeGreen){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean SkyStoneDetectedRight(boolean isInRangeGreen,
                                        boolean isInRangeBlue,
                                        boolean isInRangeRed){

        if(isInRangeBlue && isInRangeRed && isInRangeGreen){
            return true;
        }
        else {
            return false;
        }
    }

    public void toggleObjDetected(boolean stateControl){
        if(stateControl == true){
            objDetected = !objDetected;
        }
        else {
            objDetected = objDetected;
        }
    }

    public void raiseDL() {
        hookLeft.setPosition(0.9);
        hookRight.setPosition(0.9);
        middleGrab.setPosition(0.0);
    }

    public void rest() {
        TL.setPower(0);
        TR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
    }

    public void dropDL() {
        hookLeft.setPosition(0.1);
        hookRight.setPosition(0.1);
    }

    public void wait(double seconds) {
        ElapsedTime Intruntime = new ElapsedTime();
        Intruntime.reset();
        while (opModeIsActive() && Intruntime.seconds() < seconds) {
            telemetry.addData("Status: ", "Executing the current Phase");
            telemetry.update();
        }
    }

    public void wait(double seconds, String phase) {
        ElapsedTime Intruntime = new ElapsedTime();
        Intruntime.reset();
        while (opModeIsActive() && Intruntime.seconds() < seconds) {
            telemetry.addData("Status: ", phase);
            telemetry.update();
        }
        //sleep(500);
    }

    private void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     *
     * @return Angle in degrees. + = left, - = right from zero point.
     */
    private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
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

        pidRotate.reset();
        pidRotate.setSetpoint(degrees);
        pidRotate.setInputRange(0, degrees);
        pidRotate.setOutputRange(0, power);
        pidRotate.setTolerance(1);
        pidRotate.enable();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        // rotate until turn is completed.

        if (degrees < 0) {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getAngle() == 0) {
                TL.setPower(power);
                BL.setPower(power);
                TR.setPower(-power);
                BR.setPower(-power);
                sleep(100);
            }

            do {
                power = pidRotate.performPID(getAngle()); // power will be - on right turn.
                TL.setPower(-power);
                BL.setPower(-power);
                TR.setPower(power);
                BR.setPower(power);
            } while (opModeIsActive() && !pidRotate.onTarget());
        } else    // left turn.
            do {
                power = pidRotate.performPID(getAngle()); // power will be + on left turn.
                TL.setPower(-power);
                BL.setPower(-power);
                TR.setPower(power);
                BR.setPower(power);
            } while (opModeIsActive() && !pidRotate.onTarget());

        // turn the motors off.
        TL.setPower(0);
        TR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);

        rotation = getAngle();

        // wait for rotation to stop.
        sleep(500);

        // reset angle tracking on new heading.
        resetAngle();
    }

    public void goForward(){
        TL.setPower(-(power - correction));
        BL.setPower(-(power - correction));
        TR.setPower(-(power + correction));
        BR.setPower(-(power + correction));
    }

    public void goForward(double power1){
        TL.setPower(-((power1-0.0551) - correction));
        BL.setPower(-((power1) - correction));
        TR.setPower(-(power1 + correction));
        BR.setPower(-(power1 + correction));
    }

    public void goBack() {
        TL.setPower((power - correction));
        BL.setPower((power - correction));
        TR.setPower((power + correction));
        BR.setPower((power + correction));
    }

    public void strafeLeft(){
        TL.setPower(powerDown);
        TR.setPower(powerUp);
        BL.setPower(powerUp);
        BR.setPower(powerDown);
    }

    public void strafeRight(){
        TL.setPower((powerUp  - 0.0551));
        TR.setPower(powerDown);
        BL.setPower(powerDown);
        BR.setPower(powerUp);
    }

    public boolean isInRange(double number, double tolerance, double targ){
        if(((targ+tolerance)>number)&&((targ-tolerance)<number)){

            return true;
        }else{
            return false;
        }
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


    public boolean tapeSpottedPercentBase(double percentThreshold){
        double percentChange = percentChange(bottomColorSensor);
        if (percentChange>percentThreshold){
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

    public void smartGrabV4(){
        if(isInRange(colorSensorLeft.argb(), 7216, 16777216) && isInRange(colorSensorRight.argb(), 7216, 16777216)){
            middleGrab.setPosition(1.0);
        }
        else if (crossSide(thresholdBottom) == false){
            middleGrab.setPosition(0.0);
        }
    }
}