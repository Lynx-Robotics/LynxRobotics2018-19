package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_V2")
public class EncoderBlueOneStoneFullV2 extends autoBaseV2 {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(30), 0.8);

        rest();
        sleep(500);

        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.2), 1.0);

        sleep(500);

        //strafe left
        chart.runtime.reset();
        strafe(-0.3);
        while(opModeIsActive() && !SkyStoneReBorn(chart.colorSensorLeft)){

        }


        //Strafe left s'more
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3.6), -0.3);

        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(1500);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(12), -1.0);

        //strafe left more until foundation
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(54), -0.4);
        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.4), 1.0);

        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(51), -0.5);



        //raise until foundation
        goToPosition(chart.elevMotor, 500, 1.0);


        //go to foundation
        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.0), 1.0);

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(13.5), 0.4);

        //goDown elevMotor
        raiseDL();
        goToPositionDown(chart.elevMotor, 9, -1.0);

        sleep(1500);


        //goBack to wall
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(60), -0.6);

        //parking
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1.0);
        sleep(500);
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(50), 0.5);





    }
}
