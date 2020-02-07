package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "COFFEE_RUN_BLUE_HYBRID")
public class ENC_HYBRID_BLUE_V4 extends autoBaseV3 {

    int iteration = 0;

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
                strafeLeft(0.3);
                while (opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)) {
                }
                rest();
                iteration++;
            }

            if (iteration == 4) {
                encoderStrafeRight(distance2encoderNew(2.5), 0.3);
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
                elevControl(chart.elevMotor, 360, 1.0);
                iteration++;
            }

            /*
            BEFORE GOING BACK AND GETTING TAPE
             */

            if (iteration == 7) {
                correctionLeft(distance2encoderNew(0.95), 0.6);
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
        }
    }
}
