package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "DIEGO_TWO_STONE_RED")
public class TwoStoneDIEGO_RED extends autoBaseV3 {

    int iteration = 0;
    double timeUntilDetect;

    double SR_1;

    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);

        waitForStart();
        iteration++;

        while (opModeIsActive() && !(iteration == 90)){
            if (iteration == 1) {
                elevControl(chart.elevMotor, 500, 1.0);
                goToPositionForward(distance2encoderNew(30), 0.8); //go to blocks
                sleep(350);
                iteration++;
            }

            if (iteration == 2) {
                correctionRight(distance2encoderNew(1.95), 0.6);
                encoderStrafeLeft(distance2encoderNew(9), 0.35);
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
                SR_1 = 4.3;
            } else if (isInRange(timeUntilDetect, 200, 1348.6765)) {
                SR_1 = 4.3;
            } else {
                SR_1 = 4.3;
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
                goToPositionBackwardRealFast(distance2encoderNew(10), 1.0); //can be replaced if causes troubles}
                iteration++;
            }

            if (iteration == 8) {
                correctionRight(distance2encoderNew(0.5), 0.6);
                strafeRight(0.4);
                while (opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor)) {

                }
                rest();
                iteration++;
            }

            /*
            YO WE GOT TO THE TAPE
             */

            if(iteration == 9){
                encoderStrafeRight(distance2encoderNew(9), 0.35);
                raiseDL();
                sleep(250);
                chart.middleGrab.setPosition(0.55);
                iteration++ ;
            }

            if(iteration == 10){
                strafeLeft(0.38);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor)){

                }
                rest();
                iteration++;
            }

            if (iteration == 11){
                correctionRight(distance2encoderNew(4.5), 0.35);
                iteration++;
            }

            if(iteration == 12){
                encoderStrafeLeft(distance2encoderNew(86), 0.55);

                sleep(200);
//                correctionRight(distance2encoderNew(5.5), 0.8);
                iteration++;
            }

            if(iteration == 13){
//                encoderStrafeRight(distance2encoderNew(44), 0.5);
                sleep(250);
//                correctionRight(distance2encoderNew(2.5), 0.8);
                iteration++;
            }

            if (iteration == 14){
                elevControl(chart.elevMotor,700, 1.0);
                raiseDL();
                goToPositionForward(distance2encoderNew(8.5), 0.5);
//                goForward(0.5);
                /*while(opModeIsActive() && ((chart.colorSensorRight.alpha()<900) && (chart.colorSensorLeft.alpha()<900))){

                }
                rest();*/
                iteration++;
            }

            if(iteration == 15){
//                goToPositionBackward(distance2encoderNew(3), 0.6);
                encoderStrafeRight(distance2encoderNew(3.0), 0.5);
                chart.globalTime.reset();
                iteration = 17;
            }

            /*if(iteration == 16){
                strafeRight(0.35);
                while(opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)){
                    timeUntilDetect = chart.globalTime.milliseconds();
                }
                timeUntilDetect = chart.globalTime.milliseconds();
                if(timeUntilDetect < (1348.6765 - 200)){
                    iteration++;
                }
                else if(timeUntilDetect>(1348.6765-200)&& SkyStoneReBornRight(chart.colorSensorRight) && timeUntilDetect > (1348.6765 - 200)){
                    iteration = iteration ++;
                }
                else if((!SkyStoneReBornRight(chart.colorSensorRight))&& (timeUntilDetect>(1348.6765-200))){
                    iteration=18;
                }
            }*/

            if(iteration == 17){
//                encoderStrafeRight(distance2encoderNew(1.5), 0.4);
                elevMotorDown(chart.elevMotor, 5, -1.0);
                goToPositionForward(distance2encoderNew(3.2), 0.4);
                dropDL();
                sleep(500);
                elevControl(chart.elevMotor, 450, 1.0);
                iteration = 19;
            }

            if(iteration == 18){
                encoderStrafeRight(distance2encoder(4),0.4);
                elevMotorDown(chart.elevMotor,5,-1.0);
                goToPositionForward(distance2encoderNew(3.2),0.4);
                dropDL();
                rest();
                sleep(500);

                iteration = 19;
            }

            if(iteration == 19){
                goToPositionBackward(distance2encoderNew(10), 1.0);
//                correctionLeft(distance2encoderNew(1), 1.0);
                correctionRight(distance2encoderNew(4.5), 0.4);
                iteration++;
            }

            if(iteration == 20){
                encoderStrafeRight(distance2encoderNew(50), .6);
                iteration++;
            }

            if(iteration == 21){
                strafeRight(0.4);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor));
                rest();
                iteration++;
                encoderStrafeRight(distance2encoderNew(15), 0.5);
                rest();
                raiseDL();
//                elevMotorDown(chart.elevMotor, 300, -1.0);
                sleep(500);
                chart.middleGrab.setPosition(0.55);
                strafeLeft(0.4);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor));
                rest();
                iteration++;
            }


        }
    }
}
