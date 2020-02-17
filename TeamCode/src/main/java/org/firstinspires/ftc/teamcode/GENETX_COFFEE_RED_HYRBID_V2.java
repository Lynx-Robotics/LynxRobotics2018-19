package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "GENETX_COFFEE_RED_HYBRID_V2")
public class GENETX_COFFEE_RED_HYRBID_V2 extends autoBaseV5A {

    boolean STRAFE = false;
    boolean TAPE = true;

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
                correctionRight(distance2encoderNew(0.65), 0.35);
                globalPhase++;
            }

            if(globalPhase == 2){
                encoderStrafeLeft(distance2encoderNew(12), 0.45);
                globalPhase++;
            }

            if(globalPhase == 3){
                strafeRight(0.45);
                while(opModeIsActive() && !SkyStoneReBornRight(map.colorSensorRight));
                rest();
                correctionRight(distance2encoderNew(1.0), 0.35);
                encoderStrafeRight(distance2encoderNew(7.3), 0.45);
                correctionRight(distance2encoderNew(0.8), 0.35);

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
                goToPositionBackward(distance2encoderNew(14.30), 0.45);
                correctionRight(distance2encoderNew(0.5), 0.5);
                globalPhase++;
            }

            if(globalPhase == 7){
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e){}
                turnRight(0.8);
                globalPhase++;
            }

            if(globalPhase == 8){
                goForward(0.45);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor));
                rest();
                globalPhase++;
            }

            if(globalPhase == 9){
                goToPositionForward(1, .8);
                goToPositionForward(distance2encoderNew(40), 0.8);
                turnLeft(0.38);
                rest();
                globalPhase++;
            }

            if(globalPhase == 10){
                goToPositionForward(distance2encoderNew(15), 0.5);

                globalPhase++;
            }

            if(globalPhase == 11){
                raiseDL();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){}
                goToPositionBackward(distance2encoderNew(8), 0.35);
                goToPositionForward(distance2encoderNew(2), 0.30);
                raiseDL();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                encoderStrafeLeft(distance2encoderNew(0.7), 0.45);
                goToPositionBackward(distance2encoderNew(7), 0.7);
//                turnRight(0.40);
                goToPositionBackward(distance2encoderNew(48), 0.7);
                globalPhase++;
            }

            if((globalPhase == 12) && STRAFE){
                goToPositionForward(distance2encoderNew(3), 0.45);
                encoderStrafeLeft(distance2encoderNew(43), 0.45);
                goToPositionForward(distance2encoderNew(18), 0.8);
                encoderStrafeRight(distance2encoderNew(12), 0.45);
                globalPhase++;
            }

            if((globalPhase == 12) && TAPE){
                map.TAPEROT.setPosition(0.75);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){}
                map.TAPEMOTOR.setPower(-1.0);
                map.runtime.reset();
                while(opModeIsActive() && map.runtime.seconds() < 1.0){

                }
                map.TAPEMOTOR.setPower(0);
                globalPhase++;
            }

            if((globalPhase == 13) && STRAFE){
                goToPositionForward(1, 0.7);
                goToPositionForward(distance2encoderNew(0.5), 0.8);
                strafeLeft(0.5);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor));
                rest();
                elevMotorDown(map.TAPEMOTOR, 700, -1.0);
//                map.TAPEMOTOR.setPower(-1.0);
                globalPhase++;
            }

        }
    }
}
