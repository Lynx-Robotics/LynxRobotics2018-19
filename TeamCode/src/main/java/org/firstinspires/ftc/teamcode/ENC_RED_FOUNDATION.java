package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_RED_FOUNDATION")
public class ENC_RED_FOUNDATION extends autoBaseV3 {

    int phase = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){
                phase++;
                while(gamepad1.a){

                }
            }

            if(phase == 1){
                elevControl(chart.elevMotor, 500, 1.0);
                phase++;
            }

            if(phase == 1){
                goToPositionForward(distance2encoderNew(15), 0.6);
                phase++;
            }

            if(phase == 3){
                encoderStrafeRight(distance2encoderNew(50), 0.35);
                chart.globalTime.reset();
                phase++;
            }

            if(phase == 4){
                goToPositionForward(distance2encoderNew(18), 0.6);
                phase++;
            }

            if((phase == 4) && chart.globalTime.seconds() > 1.0){
                elevMotorDown(chart.elevMotor, 5, -1.0);
                phase++;
            }

            if(phase == 6){
                goToPositionBackward(distance2encoderNew(55), 0.8);
                phase++;
            }

            if(phase == 7){
                elevControl(chart.elevMotor, 500, 1.0);
                chart.middleGrab.setPosition(0.5);
                goToPositionForward(distance2encoderNew(3), 0.4);
                encoderStrafeLeft(distance2encoderNew(48), 0.4);

                correctionLeft(0.95, 0.6);
                goToPositionForward(distance2encoderNew(10), 0.6);

                strafeLeft(0.38);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(chart.bottomColorSensor)){

                }
                rest();
                phase++;
            }
        }
    }
}
