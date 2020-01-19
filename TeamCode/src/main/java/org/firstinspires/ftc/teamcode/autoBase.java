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

    public void goForward(double power1) {
        chart.TL.setPower(-(power1 - chart.correction));
        chart.BL.setPower(-(power1 - chart.correction));
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
        chart.TL.setPower(chart.powerDown + joltControl(chart.runtime));
        chart.TR.setPower(chart.powerUp);
        chart.BL.setPower(chart.powerUp);
        chart.BR.setPower(chart.powerDown);
    }

    public void strafeRight(double power) {
        chart.TL.setPower(power + joltControl(chart.runtime));
        chart.TR.setPower(-power);
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

    public void strafeLeft(double power) {
        chart.TL.setPower(power + joltControl(chart.runtime));
        chart.TR.setPower(-power);
        chart.BL.setPower(-power);
        chart.BR.setPower(power + joltControl(chart.runtime));
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
    public int parkState(double triggerDist) {
        if (opModeIsActive() && (chart.distanceSensor.getDistance(DistanceUnit.CM) < triggerDist)) {
            return 2;
        } else {
            return 1;
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

    public void wait(double seconds) {
        ElapsedTime Intruntime = new ElapsedTime();
        Intruntime.reset();
        while (opModeIsActive() && Intruntime.seconds() < seconds) {
            telemetry.addData("Status: ", "Executing the current Phase");
            telemetry.update();
        }
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

    public boolean timeoutDistSensor() {
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
            sleep(50);
            return true;
        }
    }

    public boolean SkyStoneSpotted(ColorSensor cs) {
        int HUE = 16777216;
        int GVal = 14;
        int BVal = 10;
        int RVal = 10;

        int tolerance = 10;

        if (colorCheclerGreen(cs, GVal, tolerance) && colorCheclerBlue(cs, BVal, tolerance) && colorCheclerRed(cs, RVal, tolerance)) {
            return true;
        } else {
            return false;
        }
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
    public void grabServo() {
        chart.middleGrab.setPosition(1.0);
    }

    public void dropDL() {
        chart.hookLeft.setPosition(0.1);
        chart.hookRight.setPosition(0.1);
    }

    public void raiseDL() {
        chart.hookLeft.setPosition(0.9);
        chart.hookRight.setPosition(0.9);
        chart.middleGrab.setPosition(0.0);
    }

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

    public void park(double triggerDist) {
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
    }

    /*
    ------------------------------------------------------------------------------------------
    Encoder Control Methods
    ------------------------------------------------------------------------------------------
     */

    public int encoderTicksForDistance(double distanceTarg){ //ensure that distance is in centimeters
        return (int)(distanceTarg * constants.ticksPerCm);
    }

    public void driveWithEncoder(int encoderNum, double power){
        chart.TL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chart.TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        chart.BL.setPower(chart.TL.getPower());
        chart.BL.setPower(chart.BL.getPower());

        chart.TL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        chart.TR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        goForward(power);

        while(!isInRange(chart.TL.getCurrentPosition(), 3, encoderNum) || !isInRange(chart.TR.getCurrentPosition(), 3, encoderNum)){
            telemetry.addData("Percentage Complete: (Left Side) ", (
                    ((double)chart.TL.getCurrentPosition()/(double)encoderNum)*100));
            telemetry.addData("Percentage Complete: (Right Side) ",
                    (((double)chart.TR.getCurrentPosition()/(double)encoderNum)*100));
            telemetry.update();
        }
        rest();
    }


}
