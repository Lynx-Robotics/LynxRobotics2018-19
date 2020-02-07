package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_RED_STONE_TWO")
public class ENC_RED_STONE_TWO extends autoBaseV3 {
    int side = 1;

    boolean spotLeftSensor = false, spotRightSensor = false;

    boolean parkWall = true;


    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();

        stoneTaskForward();
        turnNintyRight();
        distanceToDrop();
        disToSecondStoneTask();
        turnNintyLeft();
        stoneTaskForwardSecond();
        turnNintyRight();
        distanceToDropSecond();
        parkBridge();

    }

    public void stoneTaskForward() {
        elevControl(chart.elevMotor, 360, 1.0);
        goToPositionForward(distance2encoderNew(23.5), 1.0);

        rest();
        sleep(250);
        correctionRight(distance2encoderNew(1.0), 0.5);
        goToPositionForward(distance2encoderNew(3.5), 0.4);

        correctionRight(distance2encoderNew(0.2), 1);

        rest();
        //strafe left
        chart.runtime.reset();
        strafeLeft(0.4);
        while(opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))){

        }
        rest();
        elevMotorDown(chart.elevMotor, 5, -1.0);
        encoderStrafeRight(distance2encoderNew(6), 0.50);

        //go forward

        goToPositionForward(distance2encoderNew(4), 0.45);

        //grab
        dropDL();

        sleep(450);
        //raise elevMotor
        elevControl(chart.elevMotor, 13, 1.0);
        //go back
        correctionLeft( 0.4, 0.8);
        goToPositionBackward(distance2encoderNew(12.2), 1);
        rest();
    }

    public void turnNintyLeft() {
        goToPositionStrafeBackLeft(chart.TL, chart.TR, distance2encoderNew(32), 0.50);
        rest();
        encoderStrafeRight(distance2encoderNew(4), 1);
        correctionLeft(distance2encoderNew(0.6), 1);

        rest();
    }

    public void distanceToDrop() {

        encoderStrafeRight(distance2encoderNew(1.5), 0.6);
        rest();
        correctionLeft(0.2,1);
        goToPositionForward(distance2encoderNew(10), 1);
        rest();
        goForward(0.4);
        while (opModeIsActive() && (! bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor))) {

        }
        rest();

        goToPositionForward(distance2encoderNew(6.5), 1);

        rest();

        raiseDL();
        //goToPosition(chart.elevMotor, 14, 1.0);
        encoderStrafeRight(distance2encoderNew(2.5), 0.5);
        correctionLeft( distance2encoderNew(2), 0.6);
        sleep(200);
    }

    public void disToSecondStoneTask() {
        goToPositionBackward(distance2encoderNew(20), 1.0);
        rest();
        correctionRight(distance2encoderNew(2.5), 1.0);
        goToPositionBackward( distance2encoderNew(20), 1.0);
        rest();
        encoderStrafeRight(distance2encoderNew(3), 0.8);
        goToPositionBackward( distance2encoderNew(39), 1.0);
        rest();
    }

    public void turnNintyRight() {
        goToPositionStrafeBackLeft(chart.TR, chart.TL, distance2encoderNew(30), 0.50);
        rest();
    }

    public void stoneTaskForwardSecond() {
        chart.init(hardwareMap);
        elevControl(chart.elevMotor, 360, 1.0);
        sleep(100);
        encoderStrafeLeft(distance2encoderNew(9), 0.7);
        sleep(200);


        goToPositionForward(distance2encoderNew(4), 0.8);
//         rest();
        sleep(250);
        //goToPosition(chart.BR, chart.TL, distance2encoderNew(0.5), 1.0, false);
        //sleep(250);
        goToPositionForward(distance2encoderNew(2.5), 0.3);
        encoderStrafeLeft(distance2encoderNew(4), 0.5);
        //strafe right
        strafeRight(0.40);
        while(opModeIsActive() && (!!SkyStoneReBornLeft(chart.colorSensorLeft))){

        }
        rest();
        elevMotorDown(chart.elevMotor, 5, 1.0);


        encoderStrafeRight(distance2encoderNew(6), 0.50);

        goToPositionForward(distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(200);

        //raise elevMotor
        elevControl(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBackward(distance2encoderNew(12.5), 7);
        rest();
    }
    public void parkBridge() {
        elevControl(chart.elevMotor, 10, 1.0);

        goForward(0.3);
        while (opModeIsActive() && (! bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor))) {

        }
        rest();
    }
    public void distanceToDropSecond(){

        encoderStrafeRight( distance2encoderNew(1), 0.3);
        goToPositionForward(distance2encoderNew(20), 1);
        rest();
        encoderStrafeRight( distance2encoderNew(2), 0.6);
        rest();

        goToPositionForward( distance2encoderNew(40), 1);
        raiseDL();
        sleep(250);

        elevControl(chart.elevMotor, 10, 1.0);
    }
}
