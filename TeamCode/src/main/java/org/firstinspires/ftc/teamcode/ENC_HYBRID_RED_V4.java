package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "COFFEE_RUN_RED_HYBRID")
public class ENC_HYBRID_RED_V4 extends autoBaseV3 {

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
            
            if(timeUntilDetect < 434.65){
                SR_1 = 2.3;
            }
            else if(isInRange(timeUntilDetect, 200, 1348.6765)){
                SR_1 = 2.5;
            }
            else {
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
                correctionLeft(distance2encoderNew(0.69), 0.6);
                goToPositionBackwardRealFast(distance2encoderNew(10), 1.0); //can be replaced if causes troubles}
                iteration++;
            }

            if(iteration == 8){
                strafeLeft(0.38);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor)){

                }
                rest();
                iteration++;
            }

            /*
            YO WE GOT TO THE TAPE
             */

            if(iteration == 9){
                correctionLeft(distance2encoderNew(0.5), 0.6);
                encoderStrafeLeft(distance2encoderNew(67.0), 0.4);
                rest();
                iteration++;
                iteration++;
            }

            if(iteration == 11){
                goToPositionForward(distance2encoderNew(15), 0.6);
                iteration++;
            }

            if(iteration == 12){
                raiseDL();
                sleep(500);
                elevMotorDown(chart.elevMotor, 8, -1.0);
                iteration++;
            }

            if(iteration == 13){
                goToPositionBackward(distance2encoderNew(5), 0.5);
                goToPositionBackward(distance2encoderNew(53), 0.8);
                iteration++;
            }

            if(iteration == 14){
                elevControl(chart.elevMotor, 500, 1.0);
                chart.middleGrab.setPosition(0.55);
                goToPositionForward(distance2encoderNew(4), 0.4);
                encoderStrafeRight(distance2encoderNew(37), 0.4);

                correctionRight(distance2encoderNew(0.5), 0.6);
                goToPositionForward(distance2encoderNew(13), 0.6);

                encoderStrafeRight(distance2encoderNew(6), 0.8);
                goToPositionForward(distance2encoderNew(7), 0.6);


                correctionRight(distance2encoderNew(6), 0.6);
                strafeRight(0.38);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(chart.bottomColorSensor)){

                }
                rest();
                iteration++;
            }
        }
    }
}
