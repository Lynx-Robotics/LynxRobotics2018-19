package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;

public class gyro_reading extends LinearOpMode {
@Override
//need to add to FtcOpModeRegister
//manager.register("gyro",gyro_reading.class);
public void runOpMode() throws InterruptedException{
    int zAccumuated;
    int heading;
    int xVal,yVal, zVal;
    GyroSensor sensorGyro;
    ModernRoboticsI2cGyro mrGyro;

    sensorGyro=hardwareMap.gyroSensor.get("gyro");
    mrGyro=(ModernRoboticsI2cGyro)sensorGyro;
    mrGyro.calibrate();
    waitForStart();

    while(mrGyro.isCalibrating()){

    }
    while(opModeIsActive()){
        zAccumuated=mrGyro.getIntegratedZValue();
        heading=360-mrGyro.getHeading();
        if(heading==360){
            heading=0;
        }


        xVal=mrGyro.rawX()/128;
        yVal=mrGyro.rawY()/128;
        zVal=mrGyro.rawZ()/128;
        telemetry.addData("1. heading", String.format("%03d",heading));
        telemetry.addData("2. acc",String.format("%03d",zAccumuated));
        telemetry.addData("3. X",String.format("%03d",xVal));
        telemetry.addData("4. Y",String.format("%03d",yVal));
        telemetry.addData("5. Z",String.format("%03d",zVal));


    }
    //servo position        gyro reading(z accumulated)
    // servoPosition= 127 + [(zaccumulated)*128)/90]
}


}
