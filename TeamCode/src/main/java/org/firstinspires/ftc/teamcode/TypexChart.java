package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Thread.sleep;

public class TypexChart {
    public DcMotor TL, TR, BL, BR;
    public BNO055IMU imu;
    public Servo hookLeft, hookRight;

    public DistanceSensor distanceSensor;

    HardwareMap chart;
    ElapsedTime runtime = new ElapsedTime();

    public TypexChart()
    {

    }

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        CONSTANTS constants = new CONSTANTS();
        chart = ahwMap;

        // Define and Initialize Motors
        distanceSensor = ahwMap.get(DistanceSensor.class, "dist");

        hookLeft = ahwMap.get(Servo.class, "hook");
        hookRight = ahwMap.get(Servo.class, "hooke");

        BL = ahwMap.get(DcMotor.class, "BL");
        BR = ahwMap.get(DcMotor.class, "BR");
        TR = ahwMap.get(DcMotor.class, "TR");
        TL = ahwMap.get(DcMotor.class, "TL");

        TL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = ahwMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        hookRight.setPosition(0.9);
        hookLeft.setPosition(0.9);
    }
}
