package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_V2")
public class EncoderBlueOneStoneFullV2 extends autoBaseV2 {
    boolean spotLeftSensor = false, spotRightSensor = false;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(27.0), 1.0);

        rest();
        sleep(450);

        //repositioning for strafe
        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.6), 1.0, false);

        sleep(450);

        //strafe left
        chart.runtime.reset();
        strafe(-0.35);
        while(opModeIsActive() && (!SkyStoneReBorn(chart.colorSensorLeft) || !SkyStoneReBorn(chart.colorSensorRight))){

        }


        //Strafe left s'more
        if(spotLeftSensor){
            goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(7.0), -0.3);
            spotLeftSensor = false;

        }

        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(1500);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(12.9), -1.0);

        //strafe left more until foundation

        strafe(-0.40);
        while(opModeIsActive() && !bottomTapeSensorDetectedReborn(chart.bottomColorSensor)){

        }
        /*goToPosition(chart.BR, chart.TL, distance2encoderNew(1.2), 1.0, false);
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(48), -0.9);



        //raise until foundation
        goToPosition(chart.elevMotor, 500, 1.0);


        //go to foundation
        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.8), 1.0, false);

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(23), 0.4);

        //goDown elevMotor
        raiseDL();
        goToPositionDown(chart.elevMotor, 17, -1.0);

        sleep(500);

        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(3), -.25);


        //goBack to wall
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(41), -1.0);
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(3), -.25);



        goToPosition(chart.elevMotor, 200, 1.0);

        //parking
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1.0);
        sleep(500);
        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.8), 1.0, false);
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(55), 0.5);*/

    }
}
