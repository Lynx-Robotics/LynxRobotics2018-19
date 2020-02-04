package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_STONE_TWO")
public class ENC_BLUE_STONE_TWO extends autoBaseV2 {
    int side = 1;

        boolean spotLeftSensor = false, spotRightSensor = false;

        boolean parkWall = true;


        public void runOpMode() throws InterruptedException {
            chart.init(hardwareMap);

            waitForStart();

            //strafe Right
            //goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(5), -0.3);

            //go forward
            goToPosition(chart.elevMotor, 360, 1.0);
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(27.0), 1.0);

            rest();
            sleep(450);

            goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(36), 0.35);

            //repositioning for strafe
            goToPosition(chart.BR, chart.TL, distance2encoderNew(0.3), 1.0, false);

            sleep(450);

            //strafe left
            chart.runtime.reset();
            strafe(-0.30);
            while(opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))){

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
            goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(10), -1.0);

            //strafe left more until foundation
            //goToPositionNinety(chart.BL,chart.TR,distance2encoderNew(8),1,chart.TL,chart.BR,distance2encoderNew(5),-1,false);
            goToPosition(chart.BL,chart.TR,distance2encoder(45),1,false);

            goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(20), -1.0);
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(75), 0.8);
           // while(opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)));
            rest();
            raiseDL();
            sleep(450);

            goToPosition(chart.elevMotor, 13, 1.0);
            goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(45), -1.0);
           // goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(35), 0.7);
           //goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(40), -1.0);
            rest();
            goToPosition(chart.BR,chart.TL,distance2encoder(50),0.6,false);
            rest();
            goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(10), 0.4);



        }
}
