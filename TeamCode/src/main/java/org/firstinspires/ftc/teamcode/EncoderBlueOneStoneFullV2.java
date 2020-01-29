package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_V2")
public class EncoderBlueOneStoneFullV2 extends autoBaseV2 {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(28), 1.0);

        rest();
        sleep(450);

        //repositioning for strafe
        goToPosition(chart.BR, chart.TL, distance2encoderNew(0.6), 1.0, true);

        sleep(450);

        //strafe left
        chart.runtime.reset();
        strafe(-0.35);
        while(opModeIsActive() && !SkyStoneReBorn(chart.colorSensorLeft)){

        }


        //Strafe left s'more
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4.0), -0.3);

        //go forward
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab
        dropDL();
        sleep(1500);

        //raise elevMotor
        goToPosition(chart.elevMotor, 13, 1.0);

        //go back
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(12.7), -1.0);

        //strafe left more until foundation
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(54), -0.9);
        goToPosition(chart.BR, chart.TL, distance2encoderNew(2.4), 1.0, false);

        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(44), -0.9);



        //raise until foundation
        goToPosition(chart.elevMotor, 500, 1.0);


        //go to foundation
        goToPosition(chart.BR, chart.TL, distance2encoderNew(1.5), 1.0, false);

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(13.58), 0.4);

        //goDown elevMotor
        raiseDL();
        goToPositionDown(chart.elevMotor, 140, -1.0);

        sleep(500);


        //goBack to wall
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(43), -1.0);

        //parking
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(3), 1.0);
        sleep(500);
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(50), 0.5);





    }
}
