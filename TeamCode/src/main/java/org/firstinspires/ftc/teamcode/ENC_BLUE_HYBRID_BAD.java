package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;



@Autonomous(name = "ENC_BLUE_V2_BAD_IMU")
public class ENC_BLUE_HYBRID_BAD extends autoBaseV2_BAD {

    BNO055IMU               imu;
    Orientation             lastAngles = new Orientation();
    double                  globalAngle, correction, power = 0.3;


    boolean spotLeftSensor = false, spotRightSensor = false;

    boolean parkWall = true;

    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;


        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        while (!isStopRequested() && !imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
        telemetry.update();

        waitForStart(); //-----------------------------------------------------------------------------------

        telemetry.addData("Mode", "running");
        telemetry.update();

        sleep(1000);
        //strafe Right
//        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(5), -0.3);

        //go forward
        goToPosition(chart.elevMotor, 360, 1.0);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(27.0), 1.0);

        rest();
        sleep(450);

        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(8), 0.35);

        //repositioning for strafe
        correction = checkDirection();
        while (correction != 0) {
            correction = checkDirection();
            chart.TL.setPower(power - correction);
            chart.BR.setPower(power + correction);
        }
        chart.TL.setPower(0);
        chart.BR.setPower(0);

        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.6), 1.0, false);

        sleep(450);

        //strafe left
        chart.runtime.reset();
        strafe(-0.30);
        while (opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))) {

        }
        rest();
        goToPositionDown(chart.elevMotor, 5, -1.0);


        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.30);


        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(1500);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(16), -1.0);

        //strafe left more until foundation

        //adjust slightly
        goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(4), -0.4, false);

        strafe(-0.3);
        while (opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor))) ;
        rest();
        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.1), 0.4, false);
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(62.7), -0.7);


        //raise until foundation
        goToPosition(chart.elevMotor, 500, 1.0);


        //go to foundation
//        goToPosition(chart.BR, chart.TL, distance2encoderNew(1.3), 1.0, false);

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(15), 0.4);

        //goDown elevMotor
        raiseDL();
//        dropHookers();
        goToPositionDown(chart.elevMotor, 5, -1.0);

        sleep(500);

        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(3), -.25);


        //goBack to wall
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(41), -1.0);
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(3), -.25);

//        raiseHookers();
        chart.middleGrab.setPosition(0.5);
        goToPosition(chart.elevMotor, 200, 1.0);

        sleep(1000);

        //parking
        if (parkWall == true) {
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1.0);
            sleep(500);
            goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(1.1), -1.0, false);
            strafe(0.3);
            while (opModeIsActive() && !bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor))
                ;
            rest();
        } else {
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1.0);
            sleep(500);
            goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(1.1), -1.0, false);
            goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(42), 0.3);
            goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), -0.5);
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(18), 1.0);
            strafe(0.3);
            while (opModeIsActive() && !bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)) {

            }
            rest();

        }


    }

    public void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    public double getAngle() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double dAngles = angles.firstAngle - lastAngles.firstAngle;

        if (dAngles<-180) dAngles+=360;
        else if (dAngles>180) dAngles-=360;

        globalAngle += dAngles;

        lastAngles = angles;

        return globalAngle;
    }
    public double checkDirection() {
        double correction, gain = 0.10, angle;
        angle = getAngle();

        if (angle ==0){
            correction = 0;
        }
        else correction = -angle;

        correction = correction * gain;
        return
                correction;
    }
    private void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = power;
            rightPower = -power;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = -power;
            rightPower = power;
        }
        else return;

        // set power to rotate.
        chart.TL.setPower(leftPower);
        chart.BR.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getAngle() == 0) {}

            while (opModeIsActive() && getAngle() > degrees) {}
        }
        else    // left turn.
            while (opModeIsActive() && getAngle() < degrees) {}

        // turn the motors off.
        chart.TL.setPower(0);
        chart.BR.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }
}

