package org.firstinspires.ftc.teamcode.GENETX_ENVIRON;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.TypexChart;

import java.lang.reflect.Type;

@Autonomous(name = "GE_HYBRID_BLUE")
public class GE_HYBRID_BLUE extends autoBaseV5 {

    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        int internalPhase = 0;

        Runnable sensorSubSystem = new sensorSS();
        Runnable primaryOdometrySubSystem = new odometryPrimary();
        Runnable secondaryOdometrySubSystem = new odometrySecondary();
        Runnable elevationSubSystem = new elevationSS();

        Thread sSS = new Thread(sensorSubSystem);
        Thread pOSS = new Thread(primaryOdometrySubSystem);
        Thread sOSS = new Thread(secondaryOdometrySubSystem);
        Thread eSS = new Thread(elevationSubSystem);

//        sSS.start();
        pOSS.start();
        sOSS.start();
        eSS.start();

        waitForStart();
        while (opModeIsActive()) {
            globalPhase++;
            internalPhase++;

            if (globalPhase == 1) {
                goToPositionForward(distance2encoderNew(28), 0.8);
                rest();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                }

                globalPhase++;

            }

            if (globalPhase == 2) {
                callCorrectionRight();
                encoderStrafeRight(distance2encoderNew(8), 0.4);
                rest();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                }

                globalPhase++;
            }

            if (globalPhase == 3) {
                strafeLeft(0.4);
                while (opModeIsActive() && !SkyStoneReBornRight(map.colorSensorRight)) {

                }
                rest();
                globalPhase++;
            }

            if (globalPhase == 4) {
                callCorrectionRight();
                encoderStrafeRight(distance2encoderNew(5.5), 0.4);

                while (!elevDown) {

                }

                goToPositionForward(distance2encoderNew(3.3), 0.35);

                dropDL();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }

                globalPhase++;
            }

            if (globalPhase == 5) {
                goToPositionBackward(distance2encoderNew(8), 0.7);
                globalPhase++;
            }

            if (globalPhase == 6) {
                strafeLeft(0.4);
                while (opModeIsActive() && !tapeFound) {

                }
                encoderStrafeLeft(distance2encoderNew(18), 0.4);
                globalPhase++;
            }

            if (globalPhase == 7) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }

                callCorrectionRight();
                strafeRight(0.45);
                while (opModeIsActive() && !tapeFound) {

                }
                globalPhase++;
            }

            if (globalPhase == 8) {
                callCorrectionRight();
                encoderStrafeRight(distance2encoderNew(75), 0.45);
                globalPhase++;
            }
        }
    }
}
