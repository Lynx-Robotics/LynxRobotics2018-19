package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_V2")
public class ENC_BLUE_HYBRID extends autoBaseV2 {
    boolean spotLeftSensor = false, spotRightSensor = false;

    boolean parkWall = true;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();

        //strafe Right
//        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(5), -0.3);

        //go forward
        goToPosition(chart.elevMotor, 360, 1.0);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(27.0), 1.0);

        rest();
        sleep(450);

        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(8), 0.35);

        //repositioning for strafe
        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.6), 1.0, false);

        sleep(450);

        //strafe left
        chart.runtime.reset();
        strafe(-0.30);
        while(opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))){

        }
        rest();
        goToPositionDown(chart.elevMotor, 5, -1.0);
        goToPosition(chart.elevMotor, 350, 1.0);


        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.30);


        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(1500);

        //raise elevMotor
        goToPosition(chart.elevMotor, 30, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(16), -1.0);

        //strafe left more until foundation

        //adjust slightly
        goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(3), -0.4, false);

        strafe(-0.3);
        while(opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)));
        rest();
        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.1), 0.4, false);
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(62.7), -0.7);


        //raise until foundation


        //go to foundation
//        goToPosition(chart.BR, chart.TL, distance2encoderNew(1.3), 1.0, false);

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(15), 0.4);

        //goDown elevMotor
        raiseDL();
//        dropHookers();
        goToPositionDown(chart.elevMotor, 5, -1.0);

        sleep(500);

//        goToPosition(chart.elevMotor, 500, 1.0);


        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(3), -.25);


        //goBack to wall
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(41), -1.0);
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(3), -.25);

        raiseHookers();
        chart.middleGrab.setPosition(0.5);
        goToPosition(chart.elevMotor, 200, 1.0);

        sleep(1000);

        //parking
        if(parkWall == true){
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1.0);
            sleep(500);
            goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(1.1), -1.0, false);
            strafe(0.3);
            while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor));
            rest();
        }
        else{
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1.0);
            sleep(500);
            goToPositionAnti(chart.BR, chart.TL, distance2encoderNew(1.1), -1.0, false);
            goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(42), 0.3);
            goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), -0.5);
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR,distance2encoderNewFullVolt(18), 1.0);
            strafe(0.3);
            while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)){

            }
            rest();

        }
    }
}
