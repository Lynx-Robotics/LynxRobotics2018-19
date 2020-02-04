package org.firstinspires.ftc.teamcode;

import android.view.Display;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.Range;

public class gryo_Implementation extends LinearOpMode {

    DcMotor TL;
    DcMotor TR;


    GyroSensor sensorGyro;
    ModernRoboticsI2cGyro mrGyro;
    @Override
    public void runOpMode() throws InterruptedException{

        TL=hardwareMap.dcMotor.get("ml");
        TR=hardwareMap.dcMotor.get("mr");
        TL.setDirection(DcMotor.Direction.REVERSE);

        TL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);





        sensorGyro=hardwareMap.gyroSensor.get("gyro");
        mrGyro=(ModernRoboticsI2cGyro)sensorGyro;
        int zAccumulated;
        int target=0;

        sleep(1000);
        mrGyro.calibrate();
        waitForStart();
        while(mrGyro.isCalibrating()){

        }
        while(opModeIsActive()){

        }


    }
    //this is function to turn as we need
    public void turnAb(int target ){
       int  zAccumulated=mrGyro.getIntegratedZValue();
        double turnSpeed=0.2;

        while(Math.abs(zAccumulated-target) >3) {

            if (zAccumulated > target) {
                TL.setPower(turnSpeed);
                TR.setPower(-turnSpeed);

            }
            if (zAccumulated < target) {
                TL.setPower(-turnSpeed);
                TR.setPower(turnSpeed);

            }
            zAccumulated=mrGyro.getIntegratedZValue();
            telemetry.addData("1. accu",String.format("%03d",zAccumulated));


        }
        TL.setPower(0);
        TR.setPower(0);
        telemetry.addData("1,accu",String.format("%03d",zAccumulated));
    }

    public void turn(int target){
        turnAb(target+mrGyro.getIntegratedZValue());
    }
    public void driveStraight(double distance, double power ){
        double leftSpeed;
        double rightSpeed;
        double target=mrGyro.getIntegratedZValue();
        double startPosition=TL.getCurrentPosition();
        while(TL.getCurrentPosition()<distance+startPosition){
            int zaccumulated=mrGyro.getIntegratedZValue();
            leftSpeed=power+(zaccumulated-target)/100;
            rightSpeed=power-(zaccumulated-target)/100;
            leftSpeed= Range.clip(leftSpeed,-1,1);
            rightSpeed=Range.clip(rightSpeed,-1,1);
            TL.setPower(leftSpeed);
            TR.setPower(rightSpeed);

            telemetry.addData("1.left ",TL.getPower());
            telemetry.addData("2.right",TR.getPower());
            telemetry.addData("3.disToGo",distance+startPosition
            - TL.getCurrentPosition());
        }
        TL.setPower(0);
        TR.setPower(0);
    }


}
