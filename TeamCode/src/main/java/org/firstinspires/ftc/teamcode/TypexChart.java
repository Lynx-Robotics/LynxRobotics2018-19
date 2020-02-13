package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TypexChart {

    /* Plotting public stars */

    boolean grabState = false;


    //    public BNO055IMU imu;
    public DcMotor TL;
    public DcMotor TR;
    public DcMotor BL;
    public DcMotor BR;


    public DcMotor elevMotor;

    public ElapsedTime runtime = new ElapsedTime();
    public ElapsedTime globalTime = new ElapsedTime();

    //DistanceSensor distanceSensor;
    RevTouchSensor tapeLimit;
    ColorSensor colorSensorLeft;
    public ColorSensor bottomColorSensor;
    ColorSensor colorSensorRight; //colorSensor will default to the left
    DistanceSensor distSensorLeft, distSensorRight;

    public Servo middleGrab;
    Servo capServo;

    public double powerUp = 0.5, powerDown = -0.5, power = 0.15;

    /* Recharging local members */
    HardwareMap hwMap = null;

    /* Pager */
    public TypexChart() {

    }

    /* Initializing binaryChart Mainframe */
    public void init(HardwareMap chart) {
        hwMap = chart;


        middleGrab = hwMap.get(Servo.class, "middleGrab");
        capServo = hwMap.get(Servo.class, "capServo");

        //Name stars
        colorSensorLeft = hwMap.get(ColorSensor.class, "csLeft");
        colorSensorRight = hwMap.get(ColorSensor.class, "csRight");
        distSensorLeft = hwMap.get(DistanceSensor.class, "csLeft");
        distSensorRight = hwMap.get(DistanceSensor.class, "csRight");
        bottomColorSensor = hwMap.get(ColorSensor.class, "bcs");
        tapeLimit = hwMap.get(RevTouchSensor.class, "limitTape");

        TL = hwMap.get(DcMotor.class, "TL");
        TR = hwMap.get(DcMotor.class, "TR");
        BL = hwMap.get(DcMotor.class, "BL");
        BR = hwMap.get(DcMotor.class, "BR");

        elevMotor = hwMap.get(DcMotor.class, "elevMotor");

        /* Setting Quantum Harmonizer */
        TL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        /* Setting Power Modes */
        TL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        TL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        /* Securing Brake Field */
        TL.setPower(0);
        TR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        elevMotor.setPower(0);



        middleGrab.setPosition(0.0);
        capServo.setPosition(1.0);
    }
}