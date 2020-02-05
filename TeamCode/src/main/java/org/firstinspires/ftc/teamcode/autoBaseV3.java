package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    public void goToPosition(double position, double power) {
        boolean front;
        boolean back;
        double powerOut = power;
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

        if ((Math.abs(power) + power) == 0) { //power is negative and thus we are going backwards
            /*front = false;
            back = true;

            powerOut = -power;
            correction = -correction;*/
            chart.TL.setPower(power);
            chart.TR.setPower(power + correction);
            chart.BL.setPower(power);
            chart.BR.setPower(power + correction);

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
        } else {
            /*front = true;
            back = false;

            powerOut = power;*/
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
    }

    //all values must be put in aboslute value
    //made for strafing right
    public void encoderStrafeRight(double position, double power) {
        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        double avgEncPos = (double) (encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(power);
        chart.TR.setPower(-power);
        chart.BL.setPower(-power);
        chart.BR.setPower(power);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();

    }

    //made for strafing left
    //all values must be placed in absolute value
    public void encoderStrafeLeft(double position, double power) {
        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        double avgEncPos = (double) (encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(-power);
        chart.TR.setPower(power);
        chart.BL.setPower(power);
        chart.BR.setPower(-power);

        while (opModeIsActive() && (avgEncPosFixed > position)) {
            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();

    }

    public void correctionLeft(double position, double power) {
        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPostion = chart.TL.getCurrentPosition();

        chart.TL.setPower(power);
        chart.BR.setPower(-power);

        while (opModeIsActive() && (encoderPostion < position)) {
            encoderPostion = chart.TL.getCurrentPosition();
        }
        rest();
    }

    public void correctionRight(double position, double power) {
        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPostion = chart.TL.getCurrentPosition();

        chart.TL.setPower(-power);
        chart.BR.setPower(power);

        while (opModeIsActive() && (encoderPostion > position)) {
            encoderPostion = chart.TL.getCurrentPosition();
        }
        rest();
    }

    //method used for correction purposes
    /*public void correction(double position, double power){

        int encoderPosTL = Math.abs(chart.TL.getCurrentPosition());
        int encoderPosTR = Math.abs(chart.TR.getCurrentPosition());
        int encoderPosBL = Math.abs(chart.BL.getCurrentPosition());
        int encoderPosBR = Math.abs(chart.BR.getCurrentPosition());

        double avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(power);
        chart.TR.setPower(-power);
        chart.BL.setPower(power);
        chart.BR.setPower(-power);

        while(opModeIsActive() && ( < position)){
            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }*/

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

    public void rest() {
        chart.TL.setPower(0);
        chart.TR.setPower(0);
        chart.BL.setPower(0);
        chart.BR.setPower(0);
    }
}
