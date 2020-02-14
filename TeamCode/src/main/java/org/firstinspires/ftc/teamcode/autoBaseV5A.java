package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.GENETX_ENVIRON.hardwareMap;

public abstract class autoBaseV5A extends LinearOpMode {

    CONSTANTS constants = new CONSTANTS();
    hardwareMap map = new hardwareMap();
    acceleration acc = new acceleration();

    double correctionTL, correctionTR, correctionBL, correctionBR;


    double targetVelocity = 9700;

    int totalG, totalB, totalR;
    int avgG, avgB, avgR;
    int iteration = 0;

    int globalPhase = 0;

    boolean tapeFound = false, correctionR = false, correctionL = false;
    boolean elevDown = false;

    boolean G, B, R;

    /*
    Methods
     */
    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public boolean bottomTapeSensorDetectedRedReborn1(ColorSensor cs) {
        String alpha;
        double G = constants.tapeGreenRED;
        double B = constants.tapeBlueRED;
        double R = constants.tapeRedRED;

        boolean GP = false, BP = false, RP = false;

        GP = isInRange(cs.green(), 1050, G);
        BP = isInRange(cs.blue(), 1050, B);
        RP = isInRange(cs.red(), 1050, R);

        return GP && RP && BP;
    }

    public void elevControl(DcMotor motor1, double position, double power) {
        //resetEncoders(motor1);
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);

        while ((motorPosition <= position)) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);

    }

    public void rest() {
        map.TL.setPower(0);
        map.TR.setPower(0);
        map.BL.setPower(0);
        map.BR.setPower(0);
    }

    public boolean isInRange(double number, double tolerance, double targ) {
        if (((targ + tolerance) > number) && ((targ - tolerance) < number)) {

            return true;
        } else {
            return false;
        }
    }

    public double goToPositionForward(double position, double power) {
        double correction = 0.002;

        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPosTL = map.TL.getCurrentPosition();
        int encoderPosTR = map.TR.getCurrentPosition();
        int encoderPosBL = map.BL.getCurrentPosition();
        int encoderPosBR = map.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        map.TL.setPower(power + correctionTL);
        map.TR.setPower(power + correctionTR);
        map.BL.setPower(power + correctionBL);
        map.BR.setPower(power + correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = map.TL.getCurrentPosition();
            encoderPosTR = map.TR.getCurrentPosition();
            encoderPosBL = map.BL.getCurrentPosition();
            encoderPosBR = map.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
        return avgEncPos;
    }

    public void turnRight(double power) {
        correctionLeft(distance2encoderNew(17.25), power);
    }

    public void turnLeft(double power) {
        correctionRight(distance2encoderNew(17.35), power);
    }

    public void goForward(double power) {
        map.TL.setPower(power + correctionTL);
        map.TR.setPower(power + correctionTR);
        map.BL.setPower(power + correctionBL);
        map.BR.setPower(power + correctionBR);
    }

    public void goBackwards(double power) {
        map.TL.setPower(-power - correctionTL);
        map.TR.setPower(-power - correctionTR);
        map.BL.setPower(-power - correctionBL);
        map.BR.setPower(-power - correctionBR);
    }

    public void correctionRight(double position, double power) {
        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPositionTL = map.TL.getCurrentPosition();
        int encoderPositionBR = map.BR.getCurrentPosition();

        double avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
        double avgEncoderPosFix = Math.floor(avgEncoderPos);

        map.BR.setPower(-power + correctionBR);
        map.TR.setPower(-power + correctionTR);
        map.TL.setPower(power - correctionTL);
        map.BL.setPower(power - correctionBL);

        while (opModeIsActive() && (avgEncoderPosFix < position)) {
            encoderPositionTL = map.TL.getCurrentPosition();
            encoderPositionBR = map.BR.getCurrentPosition();

            avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
            avgEncoderPosFix = Math.floor(avgEncoderPos);

            telemetry.addData("Average Encoder Positions: ", avgEncoderPosFix);
            telemetry.update();
        }

        rest();
    }

    public void correctionLeft(double position, double power) {
        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPositionTL = map.TL.getCurrentPosition();
        int encoderPositionBR = map.BR.getCurrentPosition();
        int encoderPositionTR = map.TR.getCurrentPosition();
        int encoderPositionBL = map.BL.getCurrentPosition();

        double avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR) + Math.abs(encoderPositionTR) + Math.abs(encoderPositionBL)) / 4.0;
        double avgEncoderPosFix = Math.floor(avgEncoderPos);

        map.BR.setPower(power - correctionBR);
        map.TR.setPower(power - correctionTR);
        map.TL.setPower(-power + correctionTL);
        map.BL.setPower(-power + correctionBL);

        while (opModeIsActive() && (avgEncoderPosFix < position)) {
            encoderPositionTL = map.TL.getCurrentPosition();
            encoderPositionBR = map.BR.getCurrentPosition();
            encoderPositionTR = map.TR.getCurrentPosition();
            encoderPositionBL = map.BL.getCurrentPosition();

            avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
            avgEncoderPosFix = Math.floor(avgEncoderPos);

            telemetry.addData("Average Encoder Positions: ", avgEncoderPosFix);
            telemetry.update();
        }
        rest();
    }

    public boolean SkyStoneReBornRight(ColorSensor cs) {
        String alpha = "undefined";
        double G = constants.avgGreenBlackMIDR;
        double B = constants.avgBlueBlackMIDR;
        double R = constants.avgRedBlackMIDR;

        boolean GP = false, BP = false, RP = false;

        if (isInRange(cs.alpha(), 50, 810)) { //if it is far
            alpha = "far";
        } else if (isInRange(cs.alpha(), 50, 1019)) { //if it is middle
            alpha = "middle";
        } else if (isInRange(cs.alpha(), 50, 1200)) { //if it is very close
            alpha = "close";
        } else {
            alpha = "middle";
        }

        switch (alpha) {
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

    public double goToPositionBackward(double position, double power) {
        double correction = 0.002;

        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPosTL = map.TL.getCurrentPosition();
        int encoderPosTR = map.TR.getCurrentPosition();
        int encoderPosBL = map.BL.getCurrentPosition();
        int encoderPosBR = map.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        map.TL.setPower(-power + correctionTL);
        map.TR.setPower(-power + correctionTR);
        map.BL.setPower(-power + correctionBL);
        map.BR.setPower(-power + correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = map.TL.getCurrentPosition();
            encoderPosTR = map.TR.getCurrentPosition();
            encoderPosBL = map.BL.getCurrentPosition();
            encoderPosBR = map.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
        return avgEncPos;
    }

    public void encoderStrafeRight(double position, double power) {
        double correction = 0.002;

        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPosTL = map.TL.getCurrentPosition();
        int encoderPosTR = map.TR.getCurrentPosition();
        int encoderPosBL = map.BL.getCurrentPosition();
        int encoderPosBR = map.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        map.TL.setPower(power - correctionTL);
        map.TR.setPower(-power + correctionTR);
        map.BL.setPower(-power + correctionBL);
        map.BR.setPower(power - correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = map.TL.getCurrentPosition();
            encoderPosTR = map.TR.getCurrentPosition();
            encoderPosBL = map.BL.getCurrentPosition();
            encoderPosBR = map.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }

    public void encoderStrafeLeft(double position, double power) {
        double correction = 0.002;

        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPosTL = map.TL.getCurrentPosition();
        int encoderPosTR = map.TR.getCurrentPosition();
        int encoderPosBL = map.BL.getCurrentPosition();
        int encoderPosBR = map.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        map.TL.setPower(-power + correctionTL);
        map.TR.setPower(power - correctionTR);
        map.BL.setPower(power - correctionBL);
        map.BR.setPower(-power + correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = map.TL.getCurrentPosition();
            encoderPosTR = map.TR.getCurrentPosition();
            encoderPosBL = map.BL.getCurrentPosition();
            encoderPosBR = map.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }

    public void strafeRight(double power) {
        map.TL.setPower(power - correctionTL);
        map.TR.setPower(-power + correctionTR);
        map.BL.setPower(-power + correctionBL);
        map.BR.setPower(power - correctionBR);
    }

    public void strafeLeft(double power) {
        map.TL.setPower(-power + correctionTL);
        map.TR.setPower(power - correctionTR);
        map.BL.setPower(power - correctionBL);
        map.BR.setPower(-power + correctionBR);
    }

    public boolean bottomTapeSensorDetectedBlueReborn1(ColorSensor cs) {
        String alpha;
        double G = constants.tapeGreenBLUE;
        double B = constants.tapeBlueBLUE;
        double R = constants.tapeRedBLUE;

        boolean GP = false, BP = false, RP = false;


        GP = isInRange(cs.green(), 1050, G);
        BP = isInRange(cs.blue(), 1050, B);
        RP = isInRange(cs.red(), 1050, R);

        return GP && RP && BP;
    }

    public void elevMotorDown(DcMotor motor1, double position, double power) {
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);

        while ((motorPosition >= -position) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);
    }

    public double distance2encoderNew(double distance) {
        double encoderTicks = (constants.distance2encoderNew * distance);

        return Math.floor(encoderTicks);
    }

    public void calibrate(ColorSensor cs) {
        int baseG = cs.green();
        int baseB = cs.blue();
        int baseR = cs.red();

        totalG = totalG + baseG;
        totalB = totalB + baseB;
        totalR = totalR + baseR;

        avgG = (totalG / iteration);
        avgB = (totalB / iteration);
        avgR = (totalR / iteration);

        iteration++;
    }

    public void toggleGlobalVar(boolean input, boolean toggled) {
        if (input) {
            toggled = !toggled;
        }
    }

    public void toggleGlobalVar(boolean toggled) {
        toggled = !toggled;
    }

    public double changePerTime(double num) {
        double firstRead = num;
        map.globalTime.reset();
        while (map.globalTime.milliseconds() < 20) {

        }
        map.globalTime.reset();
        double lastRead = num;

        return (lastRead - firstRead) / 20.0; //unit per milliseconds
    }

    public void dropDL() {
        map.middleGrab.setPosition(1.0);
        while (map.middleGrab.getPosition() < 0.97) {

        }
    }

    public void raiseDL() {
        map.middleGrab.setPosition(0.35);
        /*while(map.middleGrab.getPosition() > 0.1){
        }*/
    }

    public void callCorrectionRight() {
        toggleGlobalVar(correctionR);
    }

    public void callCorrectionLeft() {
        toggleGlobalVar(correctionR);
    }

    /*
    RUNNABLE INTERFACES
     */
    public class sensorSS implements Runnable {

        @Override
        public void run() {
            while (!isStopRequested()) {
                calibrate(map.bottomColorSensor);
                while (globalPhase != 0 && !isStopRequested()) {
                    if (isInRange(map.bottomColorSensor.green(), 80, avgG)) {
                        toggleGlobalVar(G);
                    }
                    if (isInRange(map.bottomColorSensor.red(), 80, avgR)) {
                        toggleGlobalVar(R);
                    }
                    if (isInRange(map.bottomColorSensor.blue(), 80, avgB)) {
                        toggleGlobalVar(B);
                    }

                    tapeFound = G && B && R;
                }
            }

        }
    }

    public class elevationSS implements Runnable {
        int internalPhase = 1;

        @Override
        public void run() {
            while (!isStopRequested()) {
                if (globalPhase == 1 && internalPhase == 1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    elevControl(map.elevMotor, 490, 1.0);
                    internalPhase++;
                }

                if (globalPhase == 4 && internalPhase == 2) {
                    elevDown = false;
                    elevMotorDown(map.elevMotor, 3, -1.0);
                    elevDown = true;
//                    globalPhase++;
                    internalPhase++;

                }

                if (globalPhase == 6 && internalPhase == 3) {
                    elevControl(map.elevMotor, 490, 1.0);
                    internalPhase++;
                }

                if (globalPhase == 11 && internalPhase == 4) {
//                    map.TAPEROT.setPosition(0.65);
                    elevMotorDown(map.elevMotor, 8, -1.0);
//                    globalPhase++;
                    internalPhase++;
                }

                if (globalPhase == 12 && internalPhase == 5) {
                    try {
                        Thread.sleep(450);
                    } catch (InterruptedException e) {
                    }
                    elevControl(map.elevMotor, 250, 1.0);
                    internalPhase++;
                }

                if (globalPhase == 13 && internalPhase == 6) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }

                    map.TAPEROT.setPosition(0.65);
                    internalPhase++;
                }
                /*if (globalPhase == 20 && internalPhase == 4) {
                    raiseDL();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }

                    internalPhase++;
                }

                if (globalPhase == 7 && internalPhase == 4) {
                    map.middleGrab.setPosition(0.55);
                }*/
            }
        }
    }

    public class odometryPrimary implements Runnable {
        double TL_VEL = changePerTime(map.TL.getCurrentPosition());
        double TR_VEL = changePerTime(map.TL.getCurrentPosition());
        double BL_VEL = changePerTime(map.TL.getCurrentPosition());
        double BR_VEL = changePerTime(map.TL.getCurrentPosition());

        @Override
        public void run() {
            while (!isStopRequested()) {
                if (((TL_VEL + TR_VEL + BL_VEL + BR_VEL) / 4) != targetVelocity) {
                    correctionTL = acc.acceleratorCorrector(targetVelocity, TL_VEL);
                    correctionTR = acc.acceleratorCorrector(targetVelocity, TR_VEL);
                    correctionBL = acc.acceleratorCorrector(targetVelocity, BL_VEL);
                    correctionBR = acc.acceleratorCorrector(targetVelocity, BR_VEL);
                } else {
                    correctionTR = correctionTL = correctionBR = correctionBL = 0;
                }
            }
        }
    }

    public class elevatorControlSystemDouble implements Runnable {

        boolean pingOut = false;

        @Override
        public void run() {
            while (!isStopRequested()) {
                if (globalPhase == 1 && !pingOut) {
                    elevControl(map.elevMotor, 490, 1.0);
                    pingOut = true;
                }
                pingOut = false;
                if (globalPhase == 4 && !pingOut) {
                    elevMotorDown(map.elevMotor, 8, -1.0);
                    pingOut = true;
                }
                pingOut = false;
                if (globalPhase == 6 && !pingOut) {
                    elevControl(map.elevMotor, 450, 1.0);
                    pingOut = true;
                }
                pingOut = false;
                if (globalPhase == 9 && !pingOut) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e){}
                    raiseDL();
                    pingOut = true;
                }
                pingOut = false;
                if (globalPhase == 15 && !pingOut) {
                    elevMotorDown(map.elevMotor, 5, -1.0);
                    pingOut = true;
                }
            }
        }
    }

    public class elevationFoundation implements Runnable {

        boolean pingOut = false;

        @Override
        public void run() {
            while(!isStopRequested()){
                if(globalPhase == 4 && !pingOut){
                    elevMotorDown(map.elevMotor, 5, -1.0);
                    pingOut = true;
                }
                pingOut = false;

                if(globalPhase == 5 && !pingOut){

                }
            }
        }
    }

    public class odometrySecondary implements Runnable {
        @Override
        public void run() {
            while (!isStopRequested()) {
                if (correctionR) {
                    map.runtime.reset();
                    while (!isStopRequested() && map.runtime.milliseconds() < 450) {
                        map.BL.setPower(map.BL.getPower() + 0.18);
                        map.TL.setPower(map.TR.getPower() + 0.18);
                    }
                    correctionR = false;
                }
                if (correctionL) {
                    map.runtime.reset();
                    while (!isStopRequested() && map.runtime.milliseconds() < 450) {
                        map.BL.setPower(map.BL.getPower() - 0.10);
                        map.TL.setPower(map.TR.getPower() - 0.10);
                    }
                    correctionL = false;
                }
            }

        }
    }
}
