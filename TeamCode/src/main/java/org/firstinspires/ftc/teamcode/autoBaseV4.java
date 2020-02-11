package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class autoBaseV4 extends LinearOpMode {
    TypexChart chart = new TypexChart();
    CONSTANTS constants = new CONSTANTS();

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

    public double pChange(double num) {
        double firstRead = num;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            telemetry.addData("Thread was Interrupted (pChange): ", true);
            telemetry.update();
            while (opModeIsActive()) {
                //wait for stop button
            }
        }
        double lastRead = num;

        if (num == 0) {
            return 0;
        } else {
            return (firstRead - lastRead) / lastRead;
        }
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

    public double changePerTime(double num) {
        double firstRead = num;
        chart.globalTime.reset();
        while(chart.globalTime.milliseconds() < 20){

        }
        chart.globalTime.reset();
        double lastRead = num;

        return (lastRead - firstRead) / 20.0; //unit per milliseconds
    }

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public void rest() {
        chart.TL.setPower(0);
        chart.TR.setPower(0);
        chart.BL.setPower(0);
        chart.BR.setPower(0);
    }

    public double distance2encoderNew(double distance) {
        double encoderTicks = (constants.distance2encoderNew * distance);

        return Math.floor(encoderTicks);
    }

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
}
