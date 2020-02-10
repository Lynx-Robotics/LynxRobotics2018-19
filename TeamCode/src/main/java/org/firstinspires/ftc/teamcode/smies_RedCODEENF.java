package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "DIEGO_TWO_STONE_BLUE_SEMI")
public class smies_RedCODEENF extends autoBaseV3 {

    int iteration = 0;
    double timeUntilDetect;

    double SR_1;

    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);

        waitForStart();
        iteration++;

        while (opModeIsActive() && !(iteration == 12)) {
            if (iteration == 1) {
                elevControl(chart.elevMotor, 500, 1.0);
                rest();
                chart.globalTime.reset();
                while(chart.globalTime.seconds() < 10){

                }
                chart.globalTime.reset();
                encoderStrafeLeft(distance2encoderNew(25), 0.4);
                goToPositionForward(distance2encoderNew(30), 0.8); //go to blocks
                sleep(350);
                iteration++;
            }

            if (iteration == 2) {
                correctionLeft(distance2encoderNew(1.95), 0.6);
                encoderStrafeLeft(distance2encoderNew(14), 0.35);
                iteration++;
            }

            if (iteration == 3) {
                chart.globalTime.reset();
                strafeRight(0.3);
                while (opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)) {
                }
                timeUntilDetect = chart.globalTime.milliseconds();
                rest();
                iteration++;
            }

            if (timeUntilDetect < 434.65) {
                SR_1 = 6.3;
            } else if (isInRange(timeUntilDetect, 200, 1348.6765)) {
                SR_1 = 6.5;
            } else {
                SR_1 = 6.5;
            }

            if (iteration == 4) {
//                encoderStrafeRight(distance2encoderNew(2.5), 0.3);
                encoderStrafeRight(distance2encoderNew(SR_1), 0.3);
                iteration++;
            }

            if (iteration == 5) {
                elevMotorDown(chart.elevMotor, 5, -1.0);
                goToPositionForward(distance2encoderNew(1.7), 0.4);
                dropDL();
                sleep(500);
                iteration++;
            }

            //raise the block and secure from Lava
            if (iteration == 6) {
                elevControl(chart.elevMotor, 500, 1.0);
                iteration++;
            }

            /*
            BEFORE GOING BACK AND GETTING TAPE
             */

            if (iteration == 7) {
                correctionLeft(distance2encoderNew(0.49), 0.6);
                goToPositionBackwardRealFast(distance2encoderNew(22), 1.0); //can be replaced if causes troubles}
                iteration++;
            }

            if (iteration == 8) {
                correctionRight(distance2encoderNew(0.5), 0.6);
                strafeRight(0.38);
                while (opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor)) {

                }
                rest();
                iteration++;
            }

            /*
            YO WE GOT TO THE TAPE
             */

            if(iteration == 9){
                goToPositionBackward(distance2encoderNew(20), 1.0);
                iteration++;
            }

            if(iteration == 10){
                encoderStrafeRight(distance2encoderNew(15), 0.35);
                raiseDL();
                sleep(250);
                chart.middleGrab.setPosition(0.55);
                iteration++;
            }

            if(iteration == 11){
                strafeLeft(0.38);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor)){

                }
                rest();
                iteration++;
            }




        }
    }
}
