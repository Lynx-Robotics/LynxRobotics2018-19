package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class autoBaseV3 extends LinearOpMode {
    TypexChart chart = new TypexChart();
    CONSTANTS constants = new CONSTANTS();

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    //standard goToPosition for going forwards and or backwards
    public void goToPositionForward(double position, double power) {
        double correction = 0.002;

        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(power);
        chart.TR.setPower(power - correction);
        chart.BL.setPower(power);
        chart.BR.setPower(power - correction);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }

    public void goToPositionBackward(double position, double power) {
        double correction = 0.002;

        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(-power);
        chart.TR.setPower(-power + correction);
        chart.BL.setPower(-power);
        chart.BR.setPower(-power + correction);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }

    public void strafeRight(double power) {
        chart.TL.setPower(power/* - joltControl(chart.runtime)*/); //TL
        chart.TR.setPower(-power);  //TR
        chart.BL.setPower(-power + 0.002); //BL
        chart.BR.setPower(power - 0.002/*+ 0.03*/); //BR
    }

    public void strafeLeft(double power) {
        chart.TL.setPower(-power/* - joltControl(chart.runtime)*/); //TL
        chart.TR.setPower(power);  //TR
        chart.BL.setPower(power - 0.002); //BL
        chart.BR.setPower(-power + 0.002/*+ 0.03*/); //BR
    }

    //all values must be put in aboslute value
    //made for strafing right
    public void encoderStrafeRight(double position, double power) {
        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(power);
        chart.TR.setPower(-power);
        chart.BL.setPower(-power + 0.002);
        chart.BR.setPower(power - 0.002);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();

    }

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

    //made for strafing left
    //all values must be placed in absolute value
    public void encoderStrafeLeft(double position, double power) {
        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(-power);
        chart.TR.setPower(power);
        chart.BL.setPower(power - 0.002);
        chart.BR.setPower(-power + 0.002);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();

    }

    public void correctionRight(double position, double power) {
        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPositionTL = chart.TL.getCurrentPosition();
        int encoderPositionBR = chart.BR.getCurrentPosition();

        double avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
        double avgEncoderPosFix = Math.floor(avgEncoderPos);

        chart.BR.setPower(-power);
        chart.TL.setPower(power);

        while (opModeIsActive() && (avgEncoderPosFix < position)) {
            encoderPositionTL = chart.TL.getCurrentPosition();
            encoderPositionBR = chart.BR.getCurrentPosition();

            avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
            avgEncoderPosFix = Math.floor(avgEncoderPos);

            telemetry.addData("Average Encoder Positions: ", avgEncoderPosFix);
            telemetry.update();
        }

        rest();
    }

    public void correctionLeft(double position, double power) {
        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPositionTL = chart.TL.getCurrentPosition();
        int encoderPositionBR = chart.BR.getCurrentPosition();

        double avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
        double avgEncoderPosFix = Math.floor(avgEncoderPos);

        chart.BR.setPower(power);
        chart.TL.setPower(-power);

        while (opModeIsActive() && (avgEncoderPosFix < position)) {
            encoderPositionTL = chart.TL.getCurrentPosition();
            encoderPositionBR = chart.BR.getCurrentPosition();

            avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
            avgEncoderPosFix = Math.floor(avgEncoderPos);

            telemetry.addData("Average Encoder Positions: ", avgEncoderPosFix);
            telemetry.update();
        }

        rest();
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

    public void motorControl(double position, double power) {
        boolean down = false;
        int encoderPosition = chart.elevMotor.getCurrentPosition();

        if ((Math.abs(power) + power) == 0) {
            down = true;
        }

        boolean debugSwitch = true;

        if (down && debugSwitch) {
            chart.elevMotor.setPower(power);

            while (opModeIsActive() && (encoderPosition > position)) {
                encoderPosition = chart.elevMotor.getCurrentPosition();
            }
            rest();
            debugSwitch = false;
        } else if (!down && debugSwitch) {
            chart.elevMotor.setPower(power);

            while (opModeIsActive() && (encoderPosition < position)) {
                encoderPosition = chart.elevMotor.getCurrentPosition();
            }
            rest();
            debugSwitch = false;
        }
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

        chart.DebugSwitch = true;
    }

    public void rest() {
        chart.TL.setPower(0);
        chart.TR.setPower(0);
        chart.BL.setPower(0);
        chart.BR.setPower(0);
    }

    public boolean isInRange(double number, double tolerance, double targ) {
        if (((targ + tolerance) > number) && ((targ - tolerance) < number)) {

            return true;
        } else {
            return false;
        }
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

    public void elevMotorDown(DcMotor motor1, double position, double power) {
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
}
