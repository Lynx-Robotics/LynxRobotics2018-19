package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public abstract class autoBaseV2 extends LinearOpMode {

    TypexChart chart = new TypexChart();
    CONSTANTS constants = new CONSTANTS();

    //waits for a certain alloted amount of time.
    public void wait(double seconds, String phase) {
        ElapsedTime Intruntime = new ElapsedTime();
        Intruntime.reset();
        while (opModeIsActive() && Intruntime.seconds() < seconds) {
            telemetry.addData("Status: ", phase);
            telemetry.update();
        }
    }

    //drops the grabbers
    /*public void dropDL() {
        chart.hookLeft.setPosition(0.1);
        chart.hookRight.setPosition(0.1);
    }*/

    //percentage error calculator
    public double pError(double actual, double theoretical){
        double actualMinusTheoretical = actual - theoretical;
        double division = actualMinusTheoretical / theoretical;

        return division;
    }

    //resets the encoder values and brakes
    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    //Acceleration based on the exponential trend
    public double acceleratePowerReturnExponential(double targetPower){
        double dilationFactor = targetPower;
        double pErrorTime = chart.pDoneTimeAccel;

        double rawResult = Math.pow(pErrorTime, 2.0);
        double resultUnClipped = (dilationFactor * rawResult);

        return Range.clip(resultUnClipped, 0.0, 1.0);
    }

    //Acceleration based on square root trend
    public double acceleratPowerReturn(double targetPower){
        double dilationFactor = targetPower;
        double pDoneSqrt = Math.sqrt(chart.pDoneTimeAccel);


        double rawResult = (pDoneSqrt * dilationFactor);
        double fixedResult  = Range.clip(rawResult, 0.0, 1.0);
        return  fixedResult;

    }

    //Deceleration based on square root trend
    public double decelerationPowerReturnEncoder(double targetPower, double threshold, int targetEncoder, DcMotor motor){
        int currentPosEnc = motor.getCurrentPosition();

        double pErrorEncoder = pError(currentPosEnc, targetEncoder);
        double preparation = ((-pErrorEncoder)+1);

        double pDoneSqer = Math.sqrt(preparation);
        return (0.5 * pDoneSqer);

    }

    //goes to specific position down
    public void goToPositionDown(DcMotor motor1, double position, double power){
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);

        while ((motorPosition >= -position) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);

        chart.DebugSwitch = true;
    }

    public void driveForward(double power){
        chart.TL.setPower(power);
        chart.BL.setPower(power);
        chart.TR.setPower(power-0.03);
        chart.BR.setPower(power-0.03);
    }

    public void rest(){
        chart.TL.setPower(0);
        chart.BL.setPower(0);
        chart.TR.setPower(0);
        chart.BR.setPower(0);
    }

    public boolean FoundationDetected(ColorSensor cs){
        boolean GREEN = isInRange(cs.green(), 02, constants.greenFoundation);
        boolean BLUE = isInRange(cs.blue(), 15, constants.blueFoundation);
        boolean RED = isInRange(cs.red(), 15, constants.redFoundation);
        boolean ALPHA = isInRange(cs.alpha(),1 , constants.alphaFoundationIdeal);

        if(cs.alpha() > 1200){
            return false; //stop looking you found an object
        }
        else {
            return true; //keep looking you found nothing
        }
    }

    //goes to specific position
    public void goToPosition(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double position, double power) {
        resetEncoders(motor1);

        int currentPos = motor1.getCurrentPosition();
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);
        motor2.setPower(power-0.03);
        motor3.setPower(power);
        motor4.setPower(power-0.03);

        while ((motorPosition <= position) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        chart.DebugSwitch = true;
    }

    //motor control
    public void goToPosition(DcMotor motor1, double position, double power) {
        //resetEncoders(motor1);
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);

        while ((motorPosition <= position)) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);

        chart.DebugSwitch = true;
    }

    public void goToPositionBack(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double position, double power) {
        resetEncoders(motor1);

        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);
        motor2.setPower(power+0.03);
        motor3.setPower(power);
        motor4.setPower(power+0.03);

        while ((motorPosition >= (-position)) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        chart.DebugSwitch = true;
    }

    public double distance2encoder(double distance){
        double encoderTicks = (constants.distance2encoder*distance);

        return Math.floor(encoderTicks);
    }

    //strafes to the left
    public void encoderStrafeLeft(int position, double power){
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
    }

    //strafes to the right
    public void encoderStrafeRight(int position, double power){
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
    }

    //@Depreciated - DO NOT USE
    public void encoderDrive(int position, double power){
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
    }

    public boolean isInRange(double number, double tolerance, double targ){
        if(((targ+tolerance)>number)&&((targ-tolerance)<number)){

            return true;
        }else{
            return false;
        }
    }

    public boolean SkyStoneSpottedBlack(ColorSensor cs, double tolerance){
        boolean GREEN = isInRange(cs.green(), tolerance, constants.greenBlack);
        boolean BLUE = isInRange(cs.blue(), tolerance, constants.blueBlack);
        boolean RED = isInRange(cs.red(), tolerance, constants.redBlack);

        if(GREEN && BLUE && RED){
            return false; //stop looking you found a black
        }
        else {
            return true; //keep looking you found a yellow
        }
    }

    public boolean SkyStoneSpottedAntiBlack(ColorSensor cs, double tolerance){
        boolean GREEN = isInRange(cs.green(), tolerance, constants.greenYellow);
        boolean BLUE = isInRange(cs.blue(), tolerance, constants.blueYellow);
        boolean RED = isInRange(cs.red(), tolerance, constants.redYellow);

        if(GREEN && BLUE && RED){
            return true; //you found a yellow so keep going
        }
        else {
            return false; //you found something not yellow so stop
        }
    }
}