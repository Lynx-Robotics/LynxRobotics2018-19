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
            goToPositionForward(distance2encoderNew(23.6), 1.0);

            rest();
            sleep(200);
            correctionRight(distance2encoderNew(1.0), 0.5);
            goToPositionForward(distance2encoderNew(4), 0.4);

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
           // elevMotorDown(chart.elevMotor, 3.8, -1.0);
            encoderStrafeRight(distance2encoderNew(3), 0.48);
            elevMotorDown(chart.elevMotor, 5, -1.0);
            //go forward
            goToPositionForward(distance2encoderNew(1.7), 0.4);
           // correctionLeft(distance2encoderNew(0.2), 0.3);
            //grab

           //sleep(200);
            dropDL();

            sleep(450);
            //raise elevMotor
            elevControl(chart.elevMotor, 300, 1.0);
            //go back
           // correctionLeft(distance2encoderNew(2), 0.4);
            goToPositionBackward(distance2encoderNew(12), 1);
            rest();
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //goToPosition(chart.BL,chart.TR,distance2encoder(38),0.4,false);
            goToPositionStrafeBackLeft( chart.TL,chart.TR, distance2encoderNew(28), 0.50);
            rest();
            correctionLeft(distance2encoderNew(0.8), 0.6);
            //encoderStrafeRight(distance2encoderNew(5), 0.8);

            encoderStrafeLeft(distance2encoderNew(5.5), 0.8);


            //correctionRight(distance2encoderNew(1.8), 0.6);
            goToPositionForward(distance2encoderNew(8), 1);
            rest();
            sleep(200);
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            goForward(0.4);
            while(opModeIsActive() && ( !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor))){

            }
            rest();

            goToPositionForward(distance2encoderNew(6), 1);

            rest();

            raiseDL();
            elevMotorDown(chart.elevMotor, 13, -1.0);

           // goToPosition(chart.elevMotor, 14, 1.0);
            encoderStrafeLeft(distance2encoderNew(1.6), 1);
            //correctionLeft( distance2encoder(2), 1);
            sleep(200);
           // sleep(450);
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            goToPositionBackward(distance2encoderNew(35), 1.0);
            rest();
            encoderStrafeLeft(distance2encoderNew(3), 1);
            rest();
            sleep(300);
            correctionLeft(distance2encoder(0.4), 1);
            rest();
           // sleep(200);
            goToPositionBackward( distance2encoderNew(35), 1.0);
            rest();
            correctionLeft( 8, 1);
            goToPositionBackward( distance2encoderNew(10), 0.6);

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
            goToPositionStrafeBackLeft( chart.TR,chart.TL, distance2encoderNew(38), 0.45);
            sleep(200);
            elevControl(chart.elevMotor, 500, 1.0);
            sleep(100);
            encoderStrafeRight(distance2encoderNew(6), 0.6);

            sleep(300);
            correctionLeft(distance2encoderNew(2.0),1);
            encoderStrafeLeft(distance2encoderNew(1),0.8);
            rest();

            goForward(0.5);
            while(opModeIsActive() && ((chart.colorSensorRight.alpha()<818) && (chart.colorSensorLeft.alpha()<818))){

            }
            rest();
            goToPositionBackwardRealFast(distance2encoderNew(2),0.8);

            encoderStrafeRight(distance2encoderNew(4), 0.8);
            //strafe left
            sleep(250);
            chart.runtime.reset();
            strafeLeft(0.40);
            while(opModeIsActive() && (!SkyStoneReBornRight(chart.colorSensorRight))){

            }
            rest();



            encoderStrafeRight( distance2encoderNew(6), 0.30);

            correctionLeft( distance2encoderNew(0.2), 0.4);
            //grab


            elevMotorDown(chart.elevMotor, 5, -1.0);
            //go forward
            goToPositionForward(distance2encoderNew(1.7), 0.4);

            dropDL();

            //grab
           // dropDL();
            sleep(450);
            //raise elevMotor
            elevControl(chart.elevMotor, 13, 0.8);
            //go back
            goToPositionBackward(distance2encoderNew(13), 0.7);
            rest();
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //I changed here
            goToPosition(chart.BL,chart.TR,distance2encoderNew(34.8),0.4,false);
            rest();
            goToPositionBackward( distance2encoderNew(1), 0.7);
            rest();
            //encoderStrafeLeft( distance2encoderNew(1.5), 0.5);
            correctionLeft(distance2encoderNew(2),1);
            goToPositionForward(distance2encoderNew(20), 1);
            rest();
           // encoderStrafeLeft( distance2encoderNew(1), 0.3);
            //srest();

            goToPositionForward( distance2encoderNew(40), 1);
            raiseDL();
            sleep(200);

            elevControl(chart.elevMotor, 10, 1.0);

            goForward(-0.4);
            while(opModeIsActive() && ( !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor)));
            rest();

        }
}
