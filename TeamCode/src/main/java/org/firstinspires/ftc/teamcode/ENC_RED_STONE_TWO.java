package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_RED_STONE_TWO")
public class ENC_RED_STONE_TWO extends autoBaseV2 {
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
        goToPosition(chart.elevMotor, 360, 1.0);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(27.0), 1.0);

        rest();
        sleep(250);

        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.3), 1.0, false);

        sleep(250);

        //strafe left
        chart.runtime.reset();
        strafe(-0.50);
        while(opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))){

        }
        rest();
        goToPositionDown(chart.elevMotor, 5, -1.0);
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(2.5), 0.50);

        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.45);

        //grab
        dropDL();

        sleep(250);
        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);
        //go back
        goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(2), -0.4, false);
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(13), -7);
        rest();
    }

    public void turnNintyLeft() {
        goToPositionStrafeBackLeft(chart.TL, chart.TR, distance2encoderNew(33), 0.50);
        rest();
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1);

     //   goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(1), -7);
        rest();
    }

    public void distanceToDrop() {

        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(1.5), 0.6);

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(10), 1);
        rest();
        goForward(0.5);
        while (opModeIsActive() && (! bottomTapeSensorDetectedRedReborn(chart.bottomColorSensor))) {

        }
        rest();

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(6.5), 1);

        rest();

        raiseDL();
        //goToPosition(chart.elevMotor, 14, 1.0);
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(2.5), 0.5);
        goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(2), -0.6, false);
        sleep(200);
    }

    public void disToSecondStoneTask() {

       // goToPosition(chart.elevMotor, 10, 1.0);

//        goForward(-0.5);
//        while (opModeIsActive() && (! bottomTapeSensorDetectedRedReborn(chart.bottomColorSensor))) {
//
//        }
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(77), -1.0);
        rest();
    }

    public void turnNintyRight() {
        goToPositionStrafeBackLeft(chart.TR, chart.TL, distance2encoderNew(30), 0.50);
        rest();
    }

    public void stoneTaskForwardSecond() {
        chart.init(hardwareMap);
        goToPosition(chart.elevMotor, 360, 1.0);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(7.5), 1.0);

        sleep(250);

        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.3), 1.0, false);

        sleep(150);

        //strafe right
        strafe(0.50);
        while(opModeIsActive() && (!SkyStoneReBornLeft(chart.colorSensorLeft))){

        }
        rest();
        goToPositionDown(chart.elevMotor, 5, -1.0);


        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(6), 0.50);


        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(300);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(12.5), -7);
        rest();
    }
    public void parkBridge() {
        goToPosition(chart.elevMotor, 10, 1.0);

        goForward(-0.3);
        while (opModeIsActive() && (! bottomTapeSensorDetectedRedReborn(chart.bottomColorSensor))) {

        }
        rest();
    }
    public void distanceToDropSecond(){

        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4.5), 0.80);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(20), 1);
        rest();
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(50), 1);
        raiseDL();
        sleep(250);

        goToPosition(chart.elevMotor, 10, 1.0);
    }
}
