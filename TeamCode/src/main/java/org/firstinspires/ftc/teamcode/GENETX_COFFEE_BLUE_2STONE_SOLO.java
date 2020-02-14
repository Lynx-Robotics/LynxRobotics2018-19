package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "GENETX_COFFEE_BLUE_2STONE_SOLO")
public class GENETX_COFFEE_BLUE_2STONE_SOLO extends autoBaseV5A {

    public boolean pingInElev = false;

    @Override
    public void runOpMode() throws InterruptedException {

        map.init(hardwareMap);

//        Runnable odometrySubSystem = new

        Runnable elevSubSystem = new elevatorControlSystemDouble();
        Thread ess = new Thread(elevSubSystem);
        ess.start();


        waitForStart();
        globalPhase++;
        while (opModeIsActive()) {

            if (globalPhase == 1) {
                goToPositionForward(distance2encoderNew(26.8), 0.8);
                correctionRight(distance2encoderNew(0.45), 0.35);
                globalPhase++;
            }

            if (globalPhase == 2) {
                encoderStrafeLeft(distance2encoderNew(12), 0.45);
                globalPhase++;
            }

            if (globalPhase == 3) {
                correctionRight(distance2encoderNew(0.4), 0.45);
                strafeRight(0.45);
                while (opModeIsActive() && !SkyStoneReBornRight(map.colorSensorRight)) ;
                rest();
                correctionRight(distance2encoderNew(0.4), 0.35);
                encoderStrafeRight(distance2encoderNew(4.0), 0.45);
                correctionRight(distance2encoderNew(0.54), 0.35);

                globalPhase++;
            }

            if (globalPhase == 4) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }

                goToPositionForward(distance2encoderNew(5.5), 0.3);
                globalPhase++;
            }

            if (globalPhase == 5) {
                dropDL();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                }
                rest();
                globalPhase++;
            }

            if (globalPhase == 6) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                }
                goToPositionBackward(distance2encoderNew(15.30), 0.45);
                globalPhase++;
            }

            if (globalPhase == 7) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                }
                turnRight(0.8);
                globalPhase++;
            }

            if (globalPhase == 8) {
                goForward(0.45);
                while (opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor))
                    ;
                rest();
                globalPhase++;
            }

            if (globalPhase == 9) {
                goToPositionForward(distance2encoderNew(6), 0.5);
                globalPhase++;
            }

            if (globalPhase == 10) {
                goToPositionBackward(distance2encoderNew(85), 0.8);
                globalPhase++;
            }

            if (globalPhase == 11) {
                goToPositionForward(distance2encoderNew(3), 0.3);
                globalPhase++;
            }

            if (globalPhase == 12) {
                turnLeft(0.45);
                goToPositionBackward(distance2encoderNew(20), 0.7);
                globalPhase++;
            }

            if (globalPhase == 13) {
                encoderStrafeLeft(distance2encoderNew(6), 0.45);
                globalPhase++;
            }

            if(globalPhase == 14){
                goToPositionForward(distance2encoderNew(26.1),0.6);
                map.runtime.reset();

                globalPhase++;
            }

            if(globalPhase == 15){
                correctionRight(distance2encoderNew(0.4), 0.45);
                encoderStrafeRight(distance2encoderNew(4), 0.45);
                goToPositionForward(distance2encoderNew(5), 0.45);
                dropDL();
                globalPhase++;
            }

            if(globalPhase == 16){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){}
                goToPositionForward(distance2encoderNew(5), 0.45);
                dropDL();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e){}

                elevControl(map.elevMotor, 300, 1.0);

                goToPositionBackward(distance2encoderNew(12), 0.8);
                turnRight(0.5);
                correctionLeft(distance2encoderNew(3.4), 0.5);
                goToPositionForward(distance2encoderNew(80), 0.45);
                raiseDL();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){}
//                goToPositionBackward(distance2encoderNew(8), 0.45);
                map.runtime.reset();
                map.TAPEMOTOR.setPower(-1);
                while(opModeIsActive() && map.runtime.seconds() < 0.30){

                }
                map.TAPEMOTOR.setPower(0);
                globalPhase++;
            }
        }
    }
}
