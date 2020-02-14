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

            /*if(globalPhase == 2){
                encoderStrafeLeft(distance2encoderNew());
            }*/


        }
    }
}
