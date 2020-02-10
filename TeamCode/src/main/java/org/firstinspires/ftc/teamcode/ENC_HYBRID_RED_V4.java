package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "COFFEE_RUN_RED_HYBRID_ACTUAL")
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
//                correctionLeft(distance2encoderNew(.95), 0.6);
                encoderStrafeLeft(distance2encoderNew(8), 0.35);
                iteration++;
            }

            if (iteration == 3) {
                chart.globalTime.reset();
                correctionRight(distance2encoderNew(0.43), 1.0);
                strafeRight(0.3);
                while (opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)) {
                }
                timeUntilDetect = chart.globalTime.milliseconds();
                rest();
                iteration++;
            }

            if(timeUntilDetect < 434.65){
                SR_1 = 4.05;
            }
            else if(isInRange(timeUntilDetect, 200, 1348.6765)){
                SR_1 = 4.05;
            }
            else {
                SR_1 = 4.05;
            }

            if (iteration == 4) {
//                encoderStrafeRight(distance2encoderNew(2.5), 0.3);
                correctionRight(distance2encoderNew(3.5), 1.0);
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
                correctionRight(distance2encoderNew(0.49), 0.4);
                goToPositionBackwardRealFast(distance2encoderNew(11), 1.0); //can be replaced if causes troubles}
                iteration++;
            }

            if(iteration == 8){
//                correctionLeft(distance2encoderNew(0.5), 0.6);
                correctionRight(distance2encoderNew(2),1);
                strafeRight(0.35);

                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor)){

                }
                rest();
                iteration++;
            }

            /*
            YO WE GOT TO THE TAPE
             */

            if(iteration == 9){
                correctionRight(distance2encoderNew(10.7), 0.4);
                encoderStrafeRight(distance2encoderNew(28.0), 0.35);
                rest();
//                sleep(500);
                goToPositionForward(distance2encoderNew(2), 0.5);
                correctionRight(distance2encoderNew(6), 0.4);
                encoderStrafeRight(distance2encoderNew(38.0), 0.35);

                iteration++;
                iteration++;
            }

            if(iteration == 11){
                correctionRight(distance2encoderNew(2.6), 1.0);
                goToPositionForward(distance2encoderNew(24), 0.6);
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
                goToPositionBackwardRealFast(distance2encoderNewFullVolt(48), 1.0);
                iteration++;
            }

            if(iteration == 14){
                elevControl(chart.elevMotor, 500, 1.0);
                chart.middleGrab.setPosition(0.55);
                goToPositionForward(distance2encoderNew(4), 0.4);
                encoderStrafeLeft(distance2encoderNew(30), 0.4);

//                correctionRight(distance2encoderNew(0.5), 0.6);
//                goToPositionForward(distance2encoderNew(13), 0.6);

                encoderStrafeLeft(distance2encoderNew(8), 0.8);
                correctionLeft(distance2encoderNew(4), 0.6);
                goToPositionForward(distance2encoderNew(15), 0.6);


                correctionLeft(distance2encoderNew(3.9), 0.6);
                strafeLeft(0.38);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor)){

                }
                rest();
                iteration++;
            }
        }
    }
}
