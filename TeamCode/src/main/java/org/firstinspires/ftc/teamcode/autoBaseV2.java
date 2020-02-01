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
    public void dropDL() {
        chart.middleGrab.setPosition(1.0);
        while(chart.middleGrab.getPosition() < 0.97){

        }
    }


    public void raiseDL() {
        chart.middleGrab.setPosition(0.05);
        /*while(chart.middleGrab.getPosition() > 0.1){

        }*/
    }

    //percentage error calculator
    public double pError(double actual, double theoretical) {
        double actualMinusTheoretical = actual - theoretical;
        double division = actualMinusTheoretical / theoretical;

        return division;
    }

    public void toggleAutoGrab(boolean stateControl) {
        if (stateControl) {
            chart.grabState = !chart.grabState;
        }
    }

    //resets the encoder values and brakes
    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    //Acceleration based on the exponential trend
    public double acceleratePowerReturnExponential(double targetPower) {
        double dilationFactor = targetPower;
        double pErrorTime = chart.pDoneTimeAccel;

        double rawResult = Math.pow(pErrorTime, 2.0);
        double resultUnClipped = (dilationFactor * rawResult);

        return Range.clip(resultUnClipped, 0.0, 1.0);
    }

    //Acceleration based on square root trend
    public double acceleratPowerReturn(double targetPower) {
        double dilationFactor = targetPower;
        double pDoneSqrt = Math.sqrt(chart.pDoneTimeAccel);


        double rawResult = (pDoneSqrt * dilationFactor);
        double fixedResult = Range.clip(rawResult, 0.0, 1.0);
        return fixedResult;

    }

    //Deceleration based on square root trend
    public double decelerationPowerReturnEncoder(double targetPower, double threshold, int targetEncoder, DcMotor motor) {
        int currentPosEnc = motor.getCurrentPosition();

        double pErrorEncoder = pError(currentPosEnc, targetEncoder);
        double preparation = ((-pErrorEncoder) + 1);

        double pDoneSqer = Math.sqrt(preparation);
        return (0.5 * pDoneSqer);

    }

    //goes to specific position down
    public void goToPositionDown(DcMotor motor1, double position, double power) {
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

    public void driveForward(double power) {
        chart.TL.setPower(power);
        chart.BL.setPower(power);
        chart.TR.setPower(power - 0.03);
        chart.BR.setPower(power - 0.03);
    }

    public void rest() {
        chart.TL.setPower(0);
        chart.BL.setPower(0);
        chart.TR.setPower(0);
        chart.BR.setPower(0);
    }

    public boolean FoundationDetected(ColorSensor cs) {
        boolean GREEN = isInRange(cs.green(), 02, constants.greenFoundation);
        boolean BLUE = isInRange(cs.blue(), 15, constants.blueFoundation);
        boolean RED = isInRange(cs.red(), 15, constants.redFoundation);
        boolean ALPHA = isInRange(cs.alpha(), 1, constants.alphaFoundationIdeal);

        if (cs.alpha() > 1200) {
            return false; //stop looking you found an object
        } else {
            return true; //keep looking you found nothing
        }
    }

    //goes to specific position
    public void goToPosition(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double position, double power) {
        resetEncoders(motor1);

        int currentPos = motor1.getCurrentPosition();
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);
        motor2.setPower(power - 0.03);
        motor3.setPower(power);
        motor4.setPower(power - 0.03);

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
    public void goToPosition(DcMotor motor1, DcMotor motor2, double position, double power, boolean Debug) {
        resetEncoders(motor1);

        int currentPos = motor1.getCurrentPosition();
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);
        motor2.setPower(-power);

        while ((motorPosition <= position) /*&&  pError>.25*/ && !Debug) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);
        motor2.setPower(0);

        chart.DebugSwitch = true;
    }

    public void goToPositionAnti(DcMotor motor1, DcMotor motor2, double position, double power, boolean Debug) {
        resetEncoders(motor1);

        int currentPos = motor1.getCurrentPosition();
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);
        motor2.setPower(-power);

        while ((motorPosition >= position) /*&&  pError>.25*/ && !Debug) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);
        motor2.setPower(0);

        chart.DebugSwitch = true;
    }

    public void goToPositionStrafeLeft(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double position, double power) {
        resetEncoders(motor1);

        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power); //TL
        motor2.setPower(-power);  //TR
        motor3.setPower(-power); //BL
        motor4.setPower(power); //BR

        while ((motorPosition >= -position) /*&&  pError>.25*/) {
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

    public void goToPositionStrafeRight(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double position, double power) {
        resetEncoders(motor1);

        int motorPosition = motor1.getCurrentPosition();

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        motor1.setPower(power); //TL
        motor2.setPower(-power);  //TR
        motor3.setPower(-power); //BL
        motor4.setPower(power); //BR

        while ((motorPosition <= position) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        chart.DebugSwitch = true;
    }

    public double joltControl(ElapsedTime runtime) {
        if (runtime.seconds() < 1.0) {
            return 0.0050;
        } else {
            return 0.0;
        }
    }

    public void dropHookers(){
        chart.hookRight.setPosition(0.0);
        chart.hookLeft.setPosition(0.0);
    }

    public void raiseHookers(){
        chart.hookRight.setPosition(1.0);
        chart.hookLeft.setPosition(1.0);
    }

    public void strafe(double power) {
        chart.TL.setPower(power/* - joltControl(chart.runtime)*/); //TL
        chart.TR.setPower(-power);  //TR
        chart.BL.setPower(-power); //BL
        chart.BR.setPower(power /*+ 0.03*/); //BR
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
        motor2.setPower(power + 0.03);
        motor3.setPower(power);
        motor4.setPower(power + 0.03);

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

    public double distance2encoder(double distance) {
        double encoderTicks = (constants.distance2encoder * distance);

        return Math.floor(encoderTicks);
    }

    public double distance2encoderNew(double distance) {
        double encoderTicks = (constants.distance2encoderNew * distance);

        return Math.floor(encoderTicks);
    }

    public double distance2encoderNewFullVolt(double distance) {
        double encoderTicks = (constants.distance2encoderFullVoltage * distance);

        return Math.floor(encoderTicks);
    }

    //strafes to the left
    /*public void encoderStrafeLeft(int position, double power){
        goToPositionStrafe(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
    }*/

    //strafes to the right
    public void encoderStrafeRight(int position, double power) {
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, -power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
    }

    //@Depreciated - DO NOT USE
    public void encoderDrive(int position, double power) {
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, position, power);
    }

    public boolean isInRange(double number, double tolerance, double targ) {
        if (((targ + tolerance) > number) && ((targ - tolerance) < number)) {

            return true;
        } else {
            return false;
        }
    }

    public boolean colorCheclerGreen(ColorSensor cs, double GVal, int tolerance) {

        return ((cs.green() < (GVal + tolerance)) && ((cs.green() > GVal - tolerance)));
    }

    public boolean colorCheclerBlue(ColorSensor cs, double BVal, int tolerance) {

        return ((cs.blue() < (BVal + tolerance)) && ((cs.blue() > BVal - tolerance)));
    }

    public boolean colorCheclerRed(ColorSensor cs, double RVal, int tolerance) {

        return ((cs.red() < (RVal + tolerance)) && ((cs.red() > RVal - tolerance)));
    }

    public boolean SkyStoneSpottedGreen(ColorSensor colorSensor) {
        double GVal = constants.greenBlack;

        double alpha = constants.alphaBlackIdeal;


        if (isInRange(colorSensor.alpha(), 100, alpha)&&colorCheclerGreen(colorSensor, GVal, 30)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean SkyStoneSpotted(ColorSensor colorSensor) {
        double GVal = constants.greenBlack;
        double BVal = constants.blueBlack;
        double RVal = constants.redBlack;


        if (colorCheclerGreen(colorSensor, GVal, 140) && colorCheclerBlue(colorSensor, BVal, 60) && colorCheclerRed(colorSensor, RVal, 100)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean SkyStoneReBorn(ColorSensor cs){
        String alpha = "undefined";
        double G = constants.avgGreenBlackMID;
        double B = constants.avgBlueBlackMID;
        double R = constants.avgRedBlackMID;

        boolean GP = false, BP = false, RP = false;

        if(isInRange(cs.alpha(), 50, 810)){ //if it is far
            alpha = "far";
        }
        else if (isInRange(cs.alpha(), 50, 1019)){ //if it is middle
            alpha = "middle";
        }
        else if(isInRange(cs.alpha(), 50, 1200)){ //if it is very close
            alpha = "close";
        }
        else {
            alpha = "middle";
        }

        switch (alpha){
            case "far":
                G = constants.avgGreenBlackFAR;
                B = constants.avgBlueBlackFAR;
                R = constants.avgRedBlackFAR;
                break;
            case "middle":
                G = constants.avgGreenBlackMID;
                B = constants.avgBlueBlackMID;
                R = constants.avgRedBlackMID;
                break;
            case "close":
                G = constants.avgGreenBlackCLOSE;
                B = constants.avgBlueBlackCLOSE;
                R = constants.avgRedBlackCLOSE;
                break;
        }

        GP = isInRange(cs.green(), 50, G);
        BP = isInRange(cs.blue(), 25, B);
        RP = isInRange(cs.red(), 25, R);

        return GP && RP && BP;
    }

    public boolean SkyStoneReBornRight(ColorSensor cs){
        String alpha = "undefined";
        double G = constants.avgGreenBlackMIDR;
        double B = constants.avgBlueBlackMIDR;
        double R = constants.avgRedBlackMIDR;

        boolean GP = false, BP = false, RP = false;

        if(isInRange(cs.alpha(), 50, 810)){ //if it is far
            alpha = "far";
        }
        else if (isInRange(cs.alpha(), 50, 1019)){ //if it is middle
            alpha = "middle";
        }
        else if(isInRange(cs.alpha(), 50, 1200)){ //if it is very close
            alpha = "close";
        }
        else {
            alpha = "middle";
        }

        switch (alpha){
            case "far":
                G = constants.avgGreenBlackCLOSER;
                B = constants.avgBlueBlackCLOSER;
                R = constants.avgRedBlackCLOSER;
                break;
            case "middle":
                G = constants.avgGreenBlackCLOSER;
                B = constants.avgBlueBlackCLOSER;
                R = constants.avgRedBlackCLOSER;
                break;
            case "close":
                G = constants.avgGreenBlackCLOSER;
                B = constants.avgBlueBlackCLOSER;
                R = constants.avgRedBlackCLOSER;
                break;
        }

        GP = isInRange(cs.green(), 70, G);
        BP = isInRange(cs.blue(), 45, B);
        RP = isInRange(cs.red(), 45, R);

        return GP && RP && BP;
    }

    public boolean bottomTapeSensorDetectedGreyReborn(ColorSensor cs){
        String alpha;
        double G = constants.greyG;
        double B = constants.greyB;
        double R = constants.greyR;

        boolean GP = false, BP = false, RP = false;

        GP = isInRange(cs.green(), 700, G);
        BP = isInRange(cs.blue(), 700, B);
        RP = isInRange(cs.red(), 700, R);

        return GP && RP && BP;
    }

    public boolean bottomTapeSensorDetectedBlueReborn(ColorSensor cs){
        String alpha;
        double G = constants.tapeGreenBLUE;
        double B = constants.tapeBlueBLUE;
        double R = constants.tapeRedBLUE;

        boolean GP = false, BP = false, RP = false;

        GP = isInRange(cs.green(), 100, G);
        BP = isInRange(cs.blue(), 100, B);
        RP = isInRange(cs.red(), 100, R);

        return GP && RP && BP;
    }
    public boolean bottomTapeSensorDetectedRedReborn(ColorSensor cs){
        String alpha;
        double G = constants.tapeGreenRED;
        double B = constants.tapeBlueRED;
        double R = constants.tapeRedRED;

        boolean GP = false, BP = false, RP = false;

        GP = isInRange(cs.green(), 100, G);
        BP = isInRange(cs.blue(), 100, B);
        RP = isInRange(cs.red(), 100, R);

        return GP && RP && BP;
    }

    public boolean SkyStoneSpottedBlack(ColorSensor cs, double tolerance) {
        boolean GREEN = isInRange(cs.green(), tolerance, constants.greenBlack);
        boolean BLUE = isInRange(cs.blue(), tolerance, constants.blueBlack);
        boolean RED = isInRange(cs.red(), tolerance, constants.redBlack);

        if (GREEN && BLUE && RED) {
            return false; //stop looking you found a black
        } else {
            return true; //keep looking you found a yellow
        }
    }

    public boolean SkyStoneSpottedAntiBlack(ColorSensor cs, double tolerance) {
        boolean GREEN = isInRange(cs.green(), tolerance, constants.greenYellow);
        boolean BLUE = isInRange(cs.blue(), tolerance, constants.blueYellow);
        boolean RED = isInRange(cs.red(), tolerance, constants.redYellow);

        if (GREEN && BLUE && RED) {
            return true; //you found a yellow so keep going
        } else {
            return false; //you found something not yellow so stop
        }
    }
}