package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RED Foundation")
public class RED_FOUNDATION extends autoBaseV5A {

    @Override
    public void runOpMode() throws InterruptedException {

        map.init(hardwareMap);

        Runnable odomPrim = new odometryPrimary();
        Thread oss = new Thread(odomPrim);
        oss.start();

        Runnable elevationSubSystem = new elevationFoundation();
        Thread ess = new Thread(elevationSubSystem);
        ess.start();

        waitForStart();
        globalPhase++;
        while(opModeIsActive() && !isStopRequested()){
            if (globalPhase == 1){
                goToPositionForward(distance2encoderNew(27), 0.8);
                globalPhase++;
            }

            if(globalPhase == 2){
                correctionRight(distance2encoderNew(0.45), 0.45);
                encoderStrafeRight(distance2encoderNew(8), 0.35);
                globalPhase++;
            }

            if(globalPhase == 3){
                goToPositionForward(distance2encoderNew(8), 0.35);
                globalPhase++;
            }

            if(globalPhase == 4){
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e){

                }

                elevMotorDown(map.elevMotor, 5.0, -1.0);

                goToPositionBackward(distance2encoderNew(55), 0.7);
                globalPhase++;
            }

            if(globalPhase == 5){
                elevControl(map.elevMotor, 300, 1.0);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){}
                goToPositionForward(distance2encoderNew(3), 0.4);
                encoderStrafeLeft(distance2encoderNew(35), 0.45);
                globalPhase++;
            }

            if(globalPhase == 6){
                goToPositionForward(distance2encoderNew(15), 0.55);
                globalPhase++;
            }

            if(globalPhase == 7){
                encoderStrafeRight(distance2encoderNew(12), 0.45);
                globalPhase++;
            }

            if(globalPhase == 8){
//                encoderStrafeRight(distance2encoderNew(30), 0.45);
                turnRight(0.45);
                map.capServo.setPosition(0.0);
                map.runtime.reset();
                map.TAPEMOTOR.setPower(-1);
                while(opModeIsActive() && map.runtime.seconds() < 0.30){

                }
                map.TAPEMOTOR.setPower(0);
                globalPhase++;
                globalPhase++;
            }


        }
    }
}
