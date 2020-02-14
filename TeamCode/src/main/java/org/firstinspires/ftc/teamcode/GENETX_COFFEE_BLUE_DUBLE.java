package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "COFFEE_BLUE_DOUBLE_SYNTH")
@Disabled
public class GENETX_COFFEE_BLUE_DUBLE extends autoBaseV5A {

    @Override
    public void runOpMode() throws InterruptedException {

        map.init(hardwareMap);

        Runnable odomPrim = new odometryPrimary();
        Thread oss = new Thread(odomPrim);
        oss.start();

        Runnable elevPrim = new elevationSS();
        Thread ess = new Thread(elevPrim);
        ess.start();

        waitForStart();

        globalPhase++;

        while(opModeIsActive() && !isStopRequested()){

            if(gamepad1.a){
                globalPhase++;
                while(gamepad1.a);
            }

            if(globalPhase == 1) {
                goToPositionForward(distance2encoderNew(27), 0.8);
//                correctionLeft(distance2encoderNew(0.15), 0.35);
                globalPhase++;
            }

            if(globalPhase == 2){
                encoderStrafeRight(distance2encoderNew(12), 0.45);
                globalPhase++;
            }

            if(globalPhase == 3){
                strafeLeft(0.45);
                while(opModeIsActive() && !SkyStoneReBornRight(map.colorSensorRight));
                rest();
//                correctionLeft(distance2encoderNew(0.4), 0.35);
                encoderStrafeRight(distance2encoderNew(2.5), 0.45);
//                correctionLeft(distance2encoderNew(0.64), 0.35);

                globalPhase++;
            }

            if(globalPhase == 4){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e ){
                }

                goToPositionForward(distance2encoderNew(5.5), 0.3);
                globalPhase++;
            }

            if(globalPhase == 5){
                dropDL();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e){}
                rest();
                globalPhase++;
            }

            if(globalPhase == 6){
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e){}
                goToPositionBackward(distance2encoderNew(15.30), 0.45);
                globalPhase++;
            }

            if(globalPhase == 7){
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e){}
                turnLeft(0.8);
                globalPhase++;
            }

            if(globalPhase == 8){
                goForward(0.45);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(map.bottomColorSensor));
                rest();
                globalPhase++;
            }

            if(globalPhase == 9){
                goToPositionForward(1, .8);
                goToPositionForward(distance2encoderNew(41), 0.8);
                turnRight(0.38);
                rest();
                globalPhase++;
            }

            if(globalPhase == 10){
                goToPositionForward(distance2encoderNew(20), 0.5);

                globalPhase++;
            }

            if(globalPhase == 11){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){}
                goToPositionBackward(distance2encoderNew(8), 0.35);
                goToPositionForward(distance2encoderNew(4), 0.35);
                raiseDL();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                encoderStrafeRight(distance2encoderNew(3), 0.45);
                goToPositionBackward(distance2encoderNew(7), 0.9);
//                turnRight(0.40);
                goToPositionBackward(distance2encoderNew(48), 0.9);
                globalPhase++;
            }

            if(globalPhase == 12){
                goToPositionForward(distance2encoderNew(3), 0.45);
                encoderStrafeRight(distance2encoderNew(37), 0.45);
                goToPositionForward(distance2encoderNew(16), 0.8);
                encoderStrafeLeft(distance2encoderNew(12), 0.45);
                globalPhase++;
            }

            if(globalPhase == 13){
                goToPositionForward(distance2encoderNew(5), 0.7);
                strafeRight(0.5);
                while(opModeIsActive() && !bottomTapeSensorDetectedBlueReborn1(map.bottomColorSensor));
                rest();
//                elevMotorDown(map.TAPEMOTOR, 800, -1.0);
//                map.TAPEMOTOR.setPower(-1.0);

                globalPhase++;
            }

        }
    }
}
