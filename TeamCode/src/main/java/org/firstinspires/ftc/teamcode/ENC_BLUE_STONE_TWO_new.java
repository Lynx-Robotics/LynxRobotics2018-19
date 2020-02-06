package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_STONE_TWO_new")
public class ENC_BLUE_STONE_TWO_new extends autoBaseV3 {
    int side = 1;

        boolean spotLeftSensor = false, spotRightSensor = false;

        boolean parkWall = true;


        public void runOpMode() throws InterruptedException {
            chart.init(hardwareMap);

            waitForStart();
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
            elevControl(chart.elevMotor, 360, 1.0);
            goToPositionForward(distance2encoderNew(23.5), 1.0);

            rest();
            sleep(250);
            correctionRight(distance2encoderNew(2.0), 0.5);
            goToPositionForward(distance2encoderNew(3.5), 0.4);

            correctionRight(distance2encoderNew(1), 0.5);
           // sleep(150);
            //correctionRight(2, 0.8);
            rest();
            //correctionRight(0.2,0.8);
           //sleep(250);

            //strafe left
            chart.runtime.reset();
            strafeRight(0.4);
            while(opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))){

            }
            rest();
            elevMotorDown(chart.elevMotor, 5, -1.0);
            encoderStrafeRight(distance2encoderNew(3), 0.50);

            //go forward
            goToPositionForward(distance2encoderNew(5), 0.45);
            correctionLeft(distance2encoderNew(0.4), 0.8);
            //grab
            dropDL();

            sleep(250);
            //raise elevMotor
            elevControl(chart.elevMotor, 14, 1.0);
            //go back
           // correctionLeft(distance2encoderNew(2), 0.4);
            goToPositionBackward(distance2encoderNew(13), 1);
            rest();
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //goToPosition(chart.BL,chart.TR,distance2encoder(38),0.4,false);
            goToPositionStrafeBackLeft( chart.TL,chart.TR, distance2encoderNew(32.2), 0.50);
            rest();
           // correctionLeft(distance2encoderNew(1), 0.6);
            encoderStrafeRight(distance2encoderNew(5), 0.8);

            encoderStrafeLeft(distance2encoderNew(6.2), 0.8);


            correctionRight(distance2encoderNew(2.3), 0.6);
            goToPositionForward(distance2encoderNew(10), 1);
            rest();
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            goForward(0.4);
            while(opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor))){

            }
            rest();

            goToPositionForward(distance2encoderNew(7), 1);

            rest();

            raiseDL();
           // goToPosition(chart.elevMotor, 14, 1.0);
            encoderStrafeLeft(distance2encoderNew(2), 0.7);
            correctionLeft( 1, 0.6);
            sleep(200);
           // sleep(450);
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            goToPositionBackward(distance2encoderNew(20), 1.0);
            rest();
            correctionLeft(distance2encoderNew(2.5), 1.0);
            goToPositionBackward( distance2encoderNew(20), 1.0);
            rest();
            encoderStrafeLeft(distance2encoderNew(3), 0.8);
            goToPositionBackward( distance2encoderNew(39), 1.0);
            rest();
//-------------------
// oToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(20), -1.0);
//            rest();

//            goToPosition(chart.BR, chart.TL, distance2encoderNew(2.5), 1.0, false);
//            goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNew(63), -1.0);
//            rest();
////--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//            goToPositionStrafeBackLeft( chart.TR,chart.TL, distance2encoderNew(35), 0.45);
//            sleep(200);
//
// -------------------------------------------------------------------------------------------------------------------------------------------------------------
            goToPositionStrafeBackLeft( chart.TR,chart.TL, distance2encoderNew(36), 0.45);
            sleep(200);
            elevControl(chart.elevMotor, 360, 1.0);
            sleep(100);
            encoderStrafeRight(distance2encoderNew(9), 0.7);
           sleep(200);


            goToPositionForward(distance2encoderNew(4), 0.8);
//         rest();
            sleep(250);
            //goToPosition(chart.BR, chart.TL, distance2encoderNew(0.5), 1.0, false);
            //sleep(250);
            goToPositionForward(distance2encoderNew(2.5), 0.3);
            encoderStrafeRight(distance2encoderNew(4), 0.5);
            //strafe left
            sleep(450);
            chart.runtime.reset();
            strafeLeft(0.40);
            while(opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))){

            }
            rest();
            elevMotorDown(chart.elevMotor, 5, -1.0);


            encoderStrafeRight( distance2encoderNew(6), 0.30);
           //go forward
            goToPositionForward(distance2encoderNew(4), 0.45);

            correctionLeft( distance2encoderNew(0.2), 0.8);
            //grab


            sleep(250);

            //grab
            dropDL();
            sleep(250);
            //raise elevMotor
            elevControl(chart.elevMotor, 13, 0.8);
            //go back
            goToPositionBackward(distance2encoderNew(13), 0.7);
            rest();
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            goToPosition(chart.BL,chart.TR,distance2encoder(39),0.4,false);
            rest();
            goToPositionBackward( distance2encoderNew(1), 0.7);
            rest();
            encoderStrafeLeft( distance2encoderNew(1), 0.3);
            goToPositionForward(distance2encoderNew(20), 1);
            rest();
            encoderStrafeLeft( distance2encoderNew(2), -0.3);
            rest();

            goToPositionForward( distance2encoderNew(40), 1);
            raiseDL();
            sleep(250);

            elevControl(chart.elevMotor, 10, 1.0);

            goForward(-0.4);
            while(opModeIsActive() && (!bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)));
            rest();

        }
}
