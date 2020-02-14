package org.firstinspires.ftc.teamcode.GENETX_ENVIRON;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hardwareMap {

    /* Plotting public stars */

    public boolean grabState = false;



    //    public BNO055IMU imu;
    RevTouchSensor tapeLimit;

    public DcMotor TL;
    public DcMotor TR;
    public DcMotor BL;
    public DcMotor BR;

    public DcMotor TAPEMOTOR;


    public DcMotor elevMotor;

    public ElapsedTime runtime = new ElapsedTime();
    public ElapsedTime globalTime = new ElapsedTime();

    //DistanceSensor distanceSensor;
    ColorSensor colorSensorLeft;
    public ColorSensor bottomColorSensor;
    public ColorSensor colorSensorRight; //colorSensor will default to the left
    DistanceSensor distSensorLeft, distSensorRight;

    public Servo middleGrab, TAPEROT;
    public Servo capServo;

    public double powerUp = 0.5, powerDown = -0.5, power = 0.15;

    /* Recharging local members */
    HardwareMap hwMap = null;

    /* Pager */
    public hardwareMap() {

    }

    /* Initializing binaryChart Mainframe */
    public void init(HardwareMap chart) {
        hwMap = chart;


        tapeLimit = hwMap.get(RevTouchSensor.class, "tapeLimit");
        middleGrab = hwMap.get(Servo.class, "middleGrab");
        capServo = hwMap.get(Servo.class, "capServo");
        TAPEROT = hwMap.get(Servo.class, "TAPEROT");


        //Name stars
        colorSensorLeft = hwMap.get(ColorSensor.class, "csLeft");
        colorSensorRight = hwMap.get(ColorSensor.class, "csRight");
        distSensorLeft = hwMap.get(DistanceSensor.class, "csLeft");
        distSensorRight = hwMap.get(DistanceSensor.class, "csRight");
        bottomColorSensor = hwMap.get(ColorSensor.class, "bcs");

        TL = hwMap.get(DcMotor.class, "TL");
        TR = hwMap.get(DcMotor.class, "TR");
        BL = hwMap.get(DcMotor.class, "BL");
        BR = hwMap.get(DcMotor.class, "BR");

        TAPEMOTOR = hwMap.get(DcMotor.class, "TAPEMOTOR");
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
        TAPEMOTOR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        TL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TAPEMOTOR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TAPEMOTOR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        /* Securing Brake Field */
        TL.setPower(0);
        TR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        elevMotor.setPower(0);
        TAPEMOTOR.setPower(0);

        middleGrab.setPosition(0.2);
        capServo.setPosition(0.45);
        TAPEROT.setPosition(0.25);
    }
}