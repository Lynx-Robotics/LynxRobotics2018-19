package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_HYBRID_REBORN")
public class ENC_BLUE_HYBRID_REBORN extends autoBaseV3 {

    double timeUntilDetection;

    int step = 1;
    int phase = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        chart.globalTime.reset();

        //raise motor up until we get pretty high so sensors can sense
        while (opModeIsActive()) {
            if (gamepad1.a) {
                phase++;
                while (opModeIsActive() && gamepad1.a) ;
            }

            if (gamepad1.y) {
                step++;
                while (opModeIsActive() && gamepad1.y) ;
            }

            if (phase == 1) {
                elevControl(chart.elevMotor, 500, 1.0);
                phase++;
            }
            //go forward to the blocks

            if (phase == 2) {
                goToPositionForward(distance2encoderNew(30), 0.8);
                sleep(350);
                phase++;
            }

            //strafe to the right for a certain distance
            if (phase == 3) {
                correctionRight(distance2encoderNew(1.95), 0.6);
                encoderStrafeRight(distance2encoderNew(8), 0.35);
                phase++;
            }

            //strafe left until we see a block
            if (phase == 4) {
                chart.globalTime.reset();
                strafeLeft(0.3);
                while (opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)) {
                }
                phase++;
            }
            rest();
            timeUntilDetection = chart.globalTime.milliseconds();

            double RIGHT_DIST;
            if(timeUntilDetection < 750){
                RIGHT_DIST = 4.5;
            } else{
                RIGHT_DIST = 4.0;
            }

            //strafe right for a certain distance
            if(phase == 5) {
                encoderStrafeRight(distance2encoderNew(RIGHT_DIST), 0.3);
                phase++;
            }

            //extract the block
            if(phase == 6) {
                elevMotorDown(chart.elevMotor, 5, -1.0);
                goToPositionForward(distance2encoderNew(1.7), 0.4);
                dropDL();
                sleep(500);
                phase++;
            }

            //raise the block and secure from java
            if(phase==7) {
                elevControl(chart.elevMotor, 360, 1.0);
                phase++;
            }

            //reposition backwards so we can strafe
            if(phase==8) {
                correctionLeft(distance2encoderNew(0.95), 0.6);
                goToPositionBackward(distance2encoderNew(10), 1.0);
                phase++;
            }

            //strafe until we see tape
            if(phase==9) {
                strafeLeft(0.6);
                while (opModeIsActive() && !bottomTapeSensorDetectedBlueReborn(chart.bottomColorSensor)) {

                }
                rest();
                phase++;
            }
            //correct if needed

            if(phase == 11){
                encoderStrafeLeft(distance2encoderNew(64), 0.35);
                phase++;
            }

            if(phase == 13){
                goToPositionForward(distance2encoderNew(24), 0.6);
                phase++;
            }

            if(phase == 15){
                raiseDL();
                sleep(500);
                elevMotorDown(chart.elevMotor, 8, -1.0);
                phase++;
            }

            if(phase == 17){
                goToPositionBackward(distance2encoderNew(55), 0.8);
                phase++;
            }

            if(phase == 19){
                elevControl(chart.elevMotor, 500, 1.0);
                goToPositionForward(distance2encoderNew(3), 0.4);
                encoderStrafeRight(distance2encoderNew(48), 0.4);
                phase++;
            }
        }
    }

    public void waitUntilEnd() {
        while (opModeIsActive() && !gamepad1.a) {
            telemetry.addData("Time Until Detection: (ms)", timeUntilDetection);
            telemetry.update();

        }
    }
}
