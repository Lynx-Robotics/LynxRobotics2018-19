package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ENC_BLUE_STONE")
public class ENC_BLUE_STONE extends autoBaseV2 {
    int side = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        //raise elev motor
        goToPosition(chart.elevMotor, 360, 1.0);

        //go forward until we get to the stones and prepare for acquisition of first skystone
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(27.0), 1.0);
        rest();
        sleep(450);

        //strafe to the right with csLeft active
        strafe(0.35);
        while(opModeIsActive() && !SkyStoneReBorn(chart.colorSensorLeft)){

        }
        rest();

        //after left sensor has detected move slightly to the left to readjust and properly grab
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), -0.4);

        //go slightly forward to position for acquisition
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(4), 0.4);

        //grab with the grabber
        dropDL();
        sleep(770);

        //go back once grabbed
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(15), -1.0);
        sleep(750);

        //strafe until we see the tape
        strafe(-0.3);
        while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)){

        }
        //update the side value for field positioning knowledge
        side++;

        //strafe left slightly more so we can count as scored
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(9), -0.4);

        //drop the block
        raiseDL();
        sleep(770);

        //go to the right until we see tape
        strafe(0.3);
        while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)){

        }
        side++;

        //possible adjustment would go here

        //strafe to the right
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(40), -0.6);

        //another possible adjustment would go here


        //go forward until we get to another block
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(10), 0.5);

        //strafeLeft


    }
}
