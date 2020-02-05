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
        turnNintyLeft();
        distanceToDrop();
        disToSecondStoneTask();
        turnNintyRight();
        stoneTaskForwardSecond();
        turnNintyLeft();
        distanceToDrop();
        parkBridge();

    }

    public void stoneTaskForward() {
        chart.init(hardwareMap);
        goToPosition(chart.elevMotor, 360, 1.0);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(27.0), 1.0);

        rest();
        sleep(450);

        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.3), 1.0, false);

        sleep(450);

        //strafe left
        strafe(-0.30);
        while(opModeIsActive() && (!SkyStoneReBornLeft(chart.colorSensorLeft))){

        }
        rest();
        goToPositionDown(chart.elevMotor, 5, -1.0);


        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(6), -0.3);


        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(1500);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(12.5), -7);
        rest();
    }

    public void turnNintyLeft() {
        goToPositionStrafeBackLeft(chart.TL, chart.TR, distance2encoderNew(32), 0.30);
        rest();
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(2), -0.3);

        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(1), -7);
        rest();
    }

    public void distanceToDrop() {

        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(1.5), 0.30);

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(10), 1);
        rest();
        goForward(0.3);
        while (opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor))) {

        }
        rest();

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(10), 1);

        rest();

        raiseDL();
        rest();
    }

    public void disToSecondStoneTask() {

        goToPosition(chart.elevMotor, 10, 1.0);

        goForward(-0.3);
        while (opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor))) {

        }
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(30), -1.0);
        rest();
    }

    public void turnNintyRight() {
        goToPositionStrafeBackLeft(chart.TR, chart.TL, distance2encoderNew(30), 0.30);
        rest();
    }

    public void stoneTaskForwardSecond() {
        chart.init(hardwareMap);
        goToPosition(chart.elevMotor, 360, 1.0);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(7.5), 1.0);

        rest();
        sleep(450);

        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.3), 1.0, false);

        sleep(450);

        //strafe right
        strafe(0.30);
        while(opModeIsActive() && (!SkyStoneReBornLeft(chart.colorSensorLeft))){

        }
        rest();
        goToPositionDown(chart.elevMotor, 5, -1.0);


        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(6), 0.30);


        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(1500);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(12.5), -7);
        rest();
    }
    public void parkBridge() {
        goToPosition(chart.elevMotor, 10, 1.0);

        goForward(-0.3);
        while (opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor))) {

        }
        rest();
    }
}
