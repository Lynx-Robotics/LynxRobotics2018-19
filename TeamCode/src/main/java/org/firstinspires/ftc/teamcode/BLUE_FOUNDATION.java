package org.firstinspires.ftc.teamcode;

public class BLUE_FOUNDATION extends autoBaseV5A {

    @Override
    public void runOpMode() throws InterruptedException {

        Runnable odomPrim = new odometryPrimary();
        Thread oss = new Thread(odomPrim);
        oss.start();


        waitForStart();
        globalPhase++;
        while(opModeIsActive() && !isStopRequested()){
            if (globalPhase == 1){
                goToPositionForward(distance2encoderNew(27), 0.8);
                globalPhase++;
            }

            if(globalPhase == 2){
                encoderStrafeLeft(distance2encoderNew(25), 0.35);
            }

            if(globalPhase == 3){
                goToPositionForward(distance2encoderNew(8), 0.35);
            }

            if(globalPhase == 4){
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e){

                }

                goToPositionBackward(distance2encoderNew(55), 0.7);
            }

            if(globalPhase == 5){
                encoderStrafeRight(distance2encoderNew(30), 0.35);
            }

            if(globalPhase == 6){
                goToPositionForward(distance2encoderNew(13), 0.55);
            }

            if(globalPhase == 7){
                encoderStrafeLeft(distance2encoderNew(8), 0.45);
            }

            if(globalPhase == 8){
                encoderStrafeRight(distance2encoderNew(10), 0.45);
            }


        }
    }
}
