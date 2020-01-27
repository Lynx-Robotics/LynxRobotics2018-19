package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_V2")
public class EncoderBlueOneStoneFullV2 extends autoBaseV2 {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(32), 0.6);

        rest();
        sleep(2000);

        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.5), 1.0);

        sleep(1000);

        //strafe left
        chart.runtime.reset();
        strafe(-0.3);
        while(opModeIsActive() && !SkyStoneReBorn(chart.colorSensorLeft)){

        }

        //Strafe left s'more
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3.4), -0.3);

        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(2000);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(13), -1.0);

        //strafe left more until foundation
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(54), -0.3);
        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.2), 1.0);

        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(54), -0.5);



        //raise until foundation
        goToPosition(chart.elevMotor, 500, 1.0);


        //go to foundation
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(10), 0.4);

        //goDown elevMotor
        goToPositionDown(chart.elevMotor, 5, -1.0);
        raiseDL();

        sleep(2000);


        //goBack to wall
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(60), -1.0);





    }
}
