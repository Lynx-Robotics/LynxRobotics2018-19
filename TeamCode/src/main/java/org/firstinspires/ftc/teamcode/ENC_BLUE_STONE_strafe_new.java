package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_STONE_strafe_new")
public class ENC_BLUE_STONE_strafe_new extends autoBaseV3 {
         int side = 1;
         double timeUntilDetection;
        boolean spotLeftSensor = false, spotRightSensor = false;

        boolean parkWall = true;


        public void runOpMode() throws InterruptedException {
            chart.init(hardwareMap);

            waitForStart();
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
            elevControl(chart.elevMotor, 400, 1.0);
            goToPositionForward(distance2encoderNew(31), 0.8);
            sleep(250);

            correctionRight(distance2encoderNew(1.95), 0.6);
            encoderStrafeRight(distance2encoderNew(8), 0.35);

            correctionRight(distance2encoderNew(1), 0.5);
            chart.globalTime.reset();
            strafeLeft(0.4);
            while (opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)) {
            }

            rest();
            timeUntilDetection = chart.globalTime.milliseconds();

            double RIGHT_DIST;
            if(timeUntilDetection < 750){
                RIGHT_DIST = 4.5;
            } else{
                RIGHT_DIST = 4.0;
            }
            encoderStrafeRight(distance2encoderNew(RIGHT_DIST), 0.5);

            elevMotorDown(chart.elevMotor, 5, -1.0);
            goToPositionForward(distance2encoderNew(1.7), 0.4);
            dropDL();
            sleep(400);
            elevControl(chart.elevMotor, 360, 1.0);
            correctionLeft(distance2encoderNew(0.95), 0.6);
            goToPositionBackward(distance2encoderNew(10), 1.0);
            strafeLeft(0.6);
            while (opModeIsActive() && !greyDetected(chart.bottomColorSensor)) {

            }
            rest();

            encoderStrafeLeft(distance2encoderNew(6), 0.8);
            raiseDL();
            sleep(300);
            elevMotorDown(chart.elevMotor, 8, -1.0);

            //---------------------------------------------------------------

            encoderStrafeRight(distance2encoderNew(80), 0.50);

            elevControl(chart.elevMotor, 400, 1.0);
            goToPositionForward(distance2encoderNew(10), 0.8);
            sleep(250);

            correctionRight(distance2encoderNew(1.95), 0.6);
            encoderStrafeRight(distance2encoderNew(8), 0.35);

            correctionRight(distance2encoderNew(1), 0.5);
            chart.globalTime.reset();
            strafeLeft(0.4);
            while (opModeIsActive() && !SkyStoneReBornLeft(chart.colorSensorRight)) {
            }

            rest();
            timeUntilDetection = chart.globalTime.milliseconds();

             double LEFT_DIST;
            if(timeUntilDetection < 750){
                LEFT_DIST= 4.5;
            } else{
                LEFT_DIST = 4.0;
            }
            encoderStrafeLeft(distance2encoderNew(LEFT_DIST), 0.5);

            elevMotorDown(chart.elevMotor, 5, -1.0);
            goToPositionForward(distance2encoderNew(1.7), 0.4);
            dropDL();
            sleep(400);
            elevControl(chart.elevMotor, 360, 1.0);
            correctionLeft(distance2encoderNew(0.95), 0.6);
            goToPositionBackward(distance2encoderNew(10), 1.0);
            strafeLeft(0.6);
            while (opModeIsActive() && !greyDetected(chart.bottomColorSensor)) {

            }
            rest();

            encoderStrafeLeft(distance2encoderNew(6), 0.8);
            raiseDL();
            sleep(300);

            strafeRight(0.6);
            while (opModeIsActive() && !greyDetected(chart.bottomColorSensor)) {

            }

            rest();

        }
}
