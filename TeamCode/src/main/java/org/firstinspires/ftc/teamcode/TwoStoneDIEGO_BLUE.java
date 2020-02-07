package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "DIEGO_TWO_STONE")
public class TwoStoneDIEGO_BLUE extends autoBaseV3 {

    int iteration = 0;
    double timeUntilDetect;

    double SR_1;

    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);

        waitForStart();
        iteration++;

        while (opModeIsActive()) {
            if (iteration == 1) {
                elevControl(chart.elevMotor, 500, 1.0);
                goToPositionForward(distance2encoderNew(30), 0.8); //go to blocks
                sleep(350);
                iteration++;
            }

            if (iteration == 2) {
                correctionRight(distance2encoderNew(1.95), 0.6);
                encoderStrafeRight(distance2encoderNew(5), 0.35);
                iteration++;
            }

            if (iteration == 3) {
                chart.globalTime.reset();
                strafeLeft(0.3);
                while (opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)) {
                }
                timeUntilDetect = chart.globalTime.milliseconds();
                rest();
                iteration++;
            }

            if (timeUntilDetect < 434.65) {
                SR_1 = 2.3;
            } else if (isInRange(timeUntilDetect, 200, 1348.6765)) {
                SR_1 = 2.5;
            } else {
                SR_1 = 2.5;
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
                strafeLeft(0.38);
                while (opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor)) {

                }
                rest();
                iteration++;
            }

            /*
            YO WE GOT TO THE TAPE
             */

            if(iteration == 9){
                encoderStrafeLeft(distance2encoderNew(15), 0.35);
                raiseDL();
                sleep(250);
                chart.middleGrab.setPosition(0.55);
                iteration++;
            }

            if(iteration == 10){
                strafeRight(0.38);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor)){

                }
                rest();
                iteration++;
            }

            if (iteration == 11){
                correctionRight(distance2encoderNew(2.45), 0.8);
                iteration++;
            }

            if(iteration == 12){
                encoderStrafeRight(distance2encoderNew(75), 0.55);

                sleep(200);
//                correctionRight(distance2encoderNew(5.5), 0.8);
                iteration++;
            }

            if(iteration == 13){
//                encoderStrafeRight(distance2encoderNew(44), 0.5);
                sleep(250);
                correctionRight(distance2encoderNew(2.5), 0.8);
                iteration++;
            }

            if (iteration == 14){
                elevControl(chart.elevMotor,700, 1.0);
                raiseDL();
                goForward(0.65);
                while(opModeIsActive() && ((chart.colorSensorRight.alpha()<855) && (chart.colorSensorLeft.alpha()<855))){

                }
                rest();
                iteration++;
            }

            if(iteration == 15){
                goToPositionBackward(distance2encoderNew(3), 0.6);
                chart.globalTime.reset();
                iteration++;
            }

            if(iteration == 16){
                strafeLeft(0.35);
                while(opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)){
                    timeUntilDetect = chart.globalTime.milliseconds();
                }
                timeUntilDetect = chart.globalTime.milliseconds();
                if(timeUntilDetect < (1348.6765 - 200)){
                    iteration++;
                }
                else if(!SkyStoneReBornRight(chart.colorSensorRight) && timeUntilDetect > (1348.6765 - 200)){
                    iteration = iteration +2;
                }
            }

            if(iteration == 17){
                encoderStrafeLeft(distance2encoderNew(6), 0.4);
                elevMotorDown(chart.elevMotor, 5, -1.0);
                goToPositionForward(distance2encoderNew(3.4), 0.4);
                dropDL();
                sleep(400);
                iteration = 19;
            }

            if(iteration == 18){
                rest();

                iteration = 19;
            }

            if(iteration == 19){
                goToPositionBackward(distance2encoderNew(10), 1.0);
                iteration++;
            }

            if(iteration == 20){
                encoderStrafeLeft(distance2encoderNew(50), 1.0);
                iteration++;
            }

            if(iteration == 21){
                strafeLeft(0.4);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor));
                rest();
                encoderStrafeLeft(distance2encoderNew(15), 0.5);
                rest();
                raiseDL();
                elevMotorDown(chart.elevMotor, 450, -1.0);
                chart.middleGrab.setPosition(0.55);
                strafeRight(0.4);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor));
                rest();
                iteration++;
            }


        }
    }
}
