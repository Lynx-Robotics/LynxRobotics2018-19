package org.firstinspires.ftc.teamcode;

//import com.qualcomm.hardware.modernrobotics.ModernRoboticsAnalogOpticalDistanceSensor;
import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.lynx.commands.standard.LynxGetModuleLEDColorResponse;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import static java.lang.Thread.sleep;

public class TypexChart {

    /* Plotting public stars */
    PIDController pidRotate, pidDrive;

    public DcMotor TL ;
    public DcMotor TR ;
    public DcMotor BL ;
    public DcMotor BR ;

    boolean DebugSwitch = false, DebugSwitch2 = false, DebugSwitch3 = false, DebugSwitch4 = false; // used for controlling loops for debugging

    ElapsedTime runtime = new ElapsedTime();
    ElapsedTime globalTime = new ElapsedTime();

    public double correction, rotation;

    BNO055IMU imu;

    //DistanceSensor distanceSensor;
    ColorSensor colorSensorLeft, bottomColorSensor, colorSensorRight; //colorSensor will default to the left

    Servo hookLeft,hookRight, middleGrab;

    public double powerUp = 0.5, powerDown = -0.5, power = 0.15;
    public double globalAngle;
    Orientation lastAngles = new Orientation();
    /* Recharging local members */
    HardwareMap hwMap = null;

    /* Pager */
    public TypexChart() {

    }

    /* Initializing binaryChart Mainframe */
    public void init (HardwareMap chart) {
        hwMap = chart;

        hookRight = hwMap.get(Servo.class, "hookRight");
        hookLeft = hwMap.get(Servo.class, "hookLeft");
        middleGrab = hwMap.get(Servo.class, "middleGrab");

        //Name stars
        //distanceSensor = hwMap.get(DistanceSensor.class, "dist");
        //colorSensorLeft = hwMap.get(ColorSensor.class, "csLeft");
        //colorSensorRight = hwMap.get(ColorSensor.class, "csRight");
        //bottomColorSensor = hwMap.get(ColorSensor.class, "bcs");

        TL = hwMap.get(DcMotor.class, "TL");
        TR = hwMap.get(DcMotor.class, "TR");
        BL = hwMap.get(DcMotor.class, "BL");
        BR = hwMap.get(DcMotor.class, "BR");

        /* Setting Quantum Harmonizer */
        TL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        /* Setting Power Modes */
        TL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        TL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        /* Securing Brake Field */
        TL.setPower(0);
        TR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hwMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        hookRight.setPosition(0.9);
        hookLeft.setPosition(0.9);
        //middleGrab.setPosition(0.0);

        pidDrive = new PIDController(.05, 0, 0);
        pidRotate = new PIDController(.004, .00004, 0);

        //bottomColorSensor.enableLed(true);
        //colorSensorLeft.enableLed(true);
        //colorSensorRight.enableLed(true);


    }
}
