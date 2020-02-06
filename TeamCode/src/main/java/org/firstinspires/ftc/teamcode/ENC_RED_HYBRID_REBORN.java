package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_RED_HYBRID_REBORN")
public class ENC_RED_HYBRID_REBORN extends autoBaseV3 {

    double timeUntilDetection;

    int step = 1;
    int phase = 0;

    double RIGHT_DIST_STRAFE_ONE, CORRECTION_BEFORE_BACKWARDS_LEFT, CORRECTION_AFTER_TAPE_SPOTTED;
    double DISTANCE_GO_TO_FOUNDATION;


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
                correctionLeft(distance2encoderNew(1.95), 0.6);
                encoderStrafeLeft(distance2encoderNew(8), 0.35);
                phase++;
            }

            //strafe left until we see a block
            if (phase == 4) {
                chart.globalTime.reset();
                strafeRight(0.3);
                while (opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)) {
                }
                phase++;
            }
            rest();
            timeUntilDetection = chart.globalTime.milliseconds();

            /*
            VARIABLE DECLARATION
            ---------------------------------------------------------------------------
             */
            if(timeUntilDetection < 750){ //if first block spotted is skystone set following param
                RIGHT_DIST_STRAFE_ONE = 4.5;
                CORRECTION_BEFORE_BACKWARDS_LEFT = 0.95;
                CORRECTION_AFTER_TAPE_SPOTTED = 0;
                DISTANCE_GO_TO_FOUNDATION = 64;
            } else{
                RIGHT_DIST_STRAFE_ONE = 4.0;
                CORRECTION_BEFORE_BACKWARDS_LEFT = 0.95;
                CORRECTION_AFTER_TAPE_SPOTTED = 0;
                DISTANCE_GO_TO_FOUNDATION = 64;
            }
            /*
            ---------------------------------------------------------------------------
             */

            //strafe right for a certain distance
            if(phase == 5) {
                encoderStrafeRight(distance2encoderNew(RIGHT_DIST_STRAFE_ONE), 0.3);
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

            //raise the block and secure from Lava
            if(phase==7) {
                elevControl(chart.elevMotor, 360, 1.0);
                phase++;
            }

            //reposition backwards so we can strafe
            /*
            @9:40PM 2/5/2020 Diego has done the following changes:
            - replaced the phase 8 found at the bottom with a phase 7
            - added a sleep variable so that the motor can raise and lower at the same time.
            - decreased the max voltage goToPositionBackward(10), 1.0 to go a dist of 8
            - added a new distance of 2 with half speed
                - Should decrease error in stoppage
             */
            if(phase==7) {
                sleep(500);
//                correctionLeft(distance2encoderNew(0.95), 0.6);
                correctionRight(distance2encoderNew(CORRECTION_BEFORE_BACKWARDS_LEFT), 0.6);
                goToPositionBackwardRealFast(distance2encoderNew(10), 1.0); //can be replaced if causes troubles
                phase++;
                phase++;//should be removed if the phase of this condition is returned to 8
            }

            //strafe until we see tape
            if(phase==9) {
                strafeRight(0.6);
                while (opModeIsActive() && !bottomTapeSensorDetectedRedReborn(chart.bottomColorSensor)) {

                }
                rest();
                phase++;
            }

            //correct if needed and position to middle of foundation
            if(phase == 10){
                correctionLeft(distance2encoderNew(CORRECTION_AFTER_TAPE_SPOTTED), 0.6);
                encoderStrafeRight(distance2encoderNew(64), 0.35);
                phase++;
            }

            //go towards the foundation
            if(phase == 11){
                goToPositionForward(distance2encoderNew(DISTANCE_GO_TO_FOUNDATION), 0.6);
                phase++;
            }

            //deploy block and grab the foundation
            if(phase == 12){
                raiseDL();
                sleep(500);
                elevMotorDown(chart.elevMotor, 8, -1.0);
                phase++;
            }

            //go backwards with foundation
            if(phase == 13){
                goToPositionBackward(distance2encoderNew(55), 0.8);
                phase++;
            }

            //park
            if(phase == 14){
                elevControl(chart.elevMotor, 500, 1.0);
                goToPositionForward(distance2encoderNew(3), 0.4);
                encoderStrafeLeft(distance2encoderNew(48), 0.4);
                goToPositionForward(distance2encoderNew(22), 0.6);
                strafeLeft(0.4);
                while(opModeIsActive() && phase == 14 && !bottomTapeSensorDetectedRedReborn(chart.bottomColorSensor)){

                }
                rest();
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
