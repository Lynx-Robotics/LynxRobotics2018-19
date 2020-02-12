package org.firstinspires.ftc.teamcode;

public class GENETX_COFFEE_RED_DUBLE extends autoBaseV5A {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            globalPhase++;
            if(globalPhase == 1) {
                goToPositionForward(distance2encoderNew(24), 0.8);
                globalPhase++;
            }

            if(globalPhase == 2){
//                correctionLeft(distance2encoderNew(1.05), 0.3);
                correctionRight(distance2encoderNew(1.3), 0.35);

                encoderStrafeLeft(distance2encoderNew(14.6), 0.4);
                globalPhase++;
            }

            if(globalPhase == 3){
                correctionRight(1.3, 0.35);
                strafeRight(0.4);
                while(opModeIsActive() && !SkyStoneReBornRight(map.colorSensorRight)){

                }
                rest();
                globalPhase++;
            }

            if(globalPhase == 4){
                encoderStrafeRight(distance2encoderNew(4.3), 0.38);
                globalPhase++;
            }

            if(globalPhase == 5){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e){}
                goToPositionForward(distance2encoderNew(5), 0.4);
                globalPhase++;
            }

            if (globalPhase == 6){
                dropDL();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){}
                globalPhase++;
            }

            if(globalPhase == 7){
//                goToPositionBackward(distance2encoderNew(3), 0.35);
                goToPositionBackward(distance2encoderNew(9.7), 0.5);
                globalPhase++;
            }

            if(globalPhase == 8){
                correctionRight(distance2encoderNew(4.6), 0.45);
                strafeRight(0.45);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor)){

                }
                rest();
                globalPhase++;
            }
        }
    }
}
