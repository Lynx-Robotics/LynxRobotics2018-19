package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class autoBaseV3 extends LinearOpMode {
    TypexChart chart = new TypexChart();
    CONSTANTS constants = new CONSTANTS();
    
    //standard goToPosition for going forwards and or backwards
    public void goToPosition(double position, double power){
        boolean front;
        boolean back;

        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);
        
        if ((Math.abs(power) + power) == 0){ //power is negative and thus we are going backwards
            front = false;
            back = true;
        }
        else {
            front = true;
            back = false;
        }
        
        boolean debugSwitch = true;
        
        if(front && debugSwitch){
            chart.TL.setPower(power);
            chart.TR.setPower(power);
            chart.BL.setPower(power);
            chart.BR.setPower(power);
            
            while(opModeIsActive() && (avgEncPosFixed < position)){
                telemetry.addData("In Rotation: ", true);
                telemetry.update();

                encoderPosTL = chart.TL.getCurrentPosition();
                encoderPosTR = chart.TR.getCurrentPosition();
                encoderPosBL = chart.BL.getCurrentPosition();
                encoderPosBR = chart.BR.getCurrentPosition();
                
                avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
                avgEncPosFixed = Math.floor(avgEncPos);
            }
            rest();
            
            debugSwitch = false;
        }
        else if (back && debugSwitch){
            chart.TL.setPower(power);
            chart.TR.setPower(power);
            chart.BL.setPower(power);
            chart.BR.setPower(power);

            while(opModeIsActive() && (avgEncPosFixed > position)){
                telemetry.addData("In Rotation: ", true);
                telemetry.update();

                encoderPosTL = chart.TL.getCurrentPosition();
                encoderPosTR = chart.TR.getCurrentPosition();
                encoderPosBL = chart.BL.getCurrentPosition();
                encoderPosBR = chart.BR.getCurrentPosition();

                avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
                avgEncPosFixed = Math.floor(avgEncPos);
            }
            rest();

            debugSwitch = false;
        }
    }

    //all values must be put in aboslute value
    //made for strafing right
    public void encoderStrafeRight(double position, double power){
        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(power);
        chart.TR.setPower(-power);
        chart.BL.setPower(-power);
        chart.BR.setPower(power);

        while(opModeIsActive() && (avgEncPosFixed < position)){
            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();

    }
    
    //made for strafing left
    //all values must be placed in absolute value
    public void encoderStrafeLeft(double position, double power){
        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();
        
        double avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);
        
        chart.TL.setPower(-power); 
        chart.TR.setPower(power);  
        chart.BL.setPower(power); 
        chart.BR.setPower(-power);
        
        while(opModeIsActive() && (avgEncPosFixed > position)){
            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double)(encoderPosTL + encoderPosTR + encoderPosBL + encoderPosBR)/4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
        
    }
    
    //method used for correction purposes
    public void correction(double position, double power){

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
    }
    
    public void motorControl(double position, double power){
        boolean down = false;
        int encoderPosition = chart.elevMotor.getCurrentPosition();
        
        if((Math.abs(power) + power) == 0){
            down = true;
        }
        
        boolean debugSwitch = true;
        
        if (down && debugSwitch){
            chart.elevMotor.setPower(power);
            
            while(opModeIsActive() && (encoderPosition > position)){
                encoderPosition = chart.elevMotor.getCurrentPosition();
            }
            rest();
            debugSwitch = false;
        }
        else if(!down && debugSwitch){
            chart.elevMotor.setPower(power);

            while(opModeIsActive() && (encoderPosition < position)){
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
    
    public void rest(){
        chart.TL.setPower(0);
        chart.TR.setPower(0);
        chart.BL.setPower(0);
        chart.BR.setPower(0);
    }
}
