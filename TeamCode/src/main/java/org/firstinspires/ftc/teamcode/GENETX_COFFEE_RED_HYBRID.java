package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "GENETX COFFEE RED HYBRID")
public class GENETX_COFFEE_RED_HYBRID extends autoBaseV5A {

    int globalPhase = 0;
    double targetVoltage = 1.0, targetVelocity = 9500;
    double correctionTL, correctionTR, correctionBL, correctionBR;
    acceleration acc = new acceleration();

    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        Runnable odometrySubSystem = new odomChecker();
        Runnable elevatorSubSystem = new elevatorChecker();

        Thread oss = new Thread(odometrySubSystem);
        Thread ess = new Thread(elevatorSubSystem);
//        Thread css = new Thread()

        oss.start();
       // ess.start();


        waitForStart();

        while (opModeIsActive()) {
            globalPhase++;
            if (globalPhase == 1) {
                goToPositionForward(distance2encoderNew(24), 0.8);
                globalPhase++;
            }

            if (globalPhase == 2) {
//                correctionLeft(distance2encoderNew(1.05), 0.3);
                correctionRight(distance2encoderNew(0.3), 0.35);

                encoderStrafeLeft(distance2encoderNew(14.6), 0.4);
                globalPhase++;
            }

            if (globalPhase == 3) {
//                correctionRight(1.3, 0.35);
                strafeRight(0.4);
                while (opModeIsActive() && !SkyStoneReBornRight(map.colorSensorRight)) {

                }
                rest();
                globalPhase++;
            }

            if (globalPhase == 4) {
                encoderStrafeRight(distance2encoderNew(4.6), 0.4);
                globalPhase++;
            }

            if (globalPhase == 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                goToPositionForward(distance2encoderNew(5), 0.4);
                globalPhase++;
            }

            if (globalPhase == 6) {
                dropDL();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                globalPhase++;
            }

            if (globalPhase == 7) {
//                goToPositionBackward(distance2encoderNew(3), 0.35);
                goToPositionBackward(distance2encoderNew(9.7), 0.5);
                globalPhase++;
            }

            if (globalPhase == 8) {
                /*correctionRight(distance2encoderNew(4.6), 0.45);
                strafeRight(0.45);
                while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor)){

                }
                rest();
                globalPhase++;*/

                turnRight(0.5);
                goForward(0.5);
                while (opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor));
                try{
                    Thread.sleep(400);
                }catch (InterruptedException e){

                }
                //rest();
                globalPhase++;
            }
            if (globalPhase == 9) {

                sleep(200);
                goToPositionForward(distance2encoderNew(5), 0.7);
                goToPositionForward(distance2encoderNew(60), 0.7);
                globalPhase++;

            }


            if (globalPhase == 10) {
                rest();
                globalPhase++;
            }

            if (globalPhase == 11) {
                raiseDL();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                goToPositionBackward(distance2encoderNew(10), 0.35);
                goToPositionBackward(distance2encoderNew(50), 0.6);
                globalPhase++;
            }

            if (globalPhase == 12) {
                encoderStrafeLeft(distance2encoderNew(39), 0.434);
                globalPhase++;
            }

            if (globalPhase == 13) {
                goToPositionForward(distance2encoderNew(17), 0.45);
                encoderStrafeRight(distance2encoderNew(15), 0.45);
                strafeLeft(0.40);
                while (opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor)) {

                }
                rest();
                turnLeft(0.6);
                turnLeft(0.6);
                globalPhase++;
            }


        }
    }

    public class elevatorChecker implements Runnable {
        int internalPhase = 0;

        @Override
        public void run() {
            while (!isStopRequested()) {
                if (globalPhase == 0 && internalPhase == 0) {
                    internalPhase++;
                }
                if (globalPhase == 1 && internalPhase == 1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        requestOpModeStop();
                    }
                    elevControl(map.elevMotor, 500, 1.0);
                    internalPhase++;
                }
                if (globalPhase == 5 && internalPhase == 2) {
                    elevMotorDown(map.elevMotor, 0, -1.0);
                    internalPhase++;
                }

                if (globalPhase == 7 && internalPhase == 3) {
                    try {
                        Thread.sleep(170);
                    } catch (InterruptedException e) {
                    }
                    elevControl(map.elevMotor, 480, 1.0);
                    internalPhase++;
                }

                if (globalPhase == 8 && internalPhase == 4) {
//                    correctionLeft(distance2encoderNew(0.5), 0.35);
                    internalPhase++;
                }

                if (globalPhase == 11 && internalPhase == 5) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    elevMotorDown(map.elevMotor, 0, -1.0);
                    internalPhase++;
                }

                if (globalPhase == 13 && internalPhase == 6) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                    }
                    raiseDL();
                    elevControl(map.elevMotor, 150, 1.0);
                    internalPhase++;
                }
            }
        }
    }

    public class odomChecker implements Runnable {
        @Override
        public void run() {
            while (!isStopRequested()) {
                double TL_VEL = changePerTime(map.TL.getCurrentPosition());
                double TR_VEL = changePerTime(map.TL.getCurrentPosition());
                double BL_VEL = changePerTime(map.TL.getCurrentPosition());
                double BR_VEL = changePerTime(map.TL.getCurrentPosition());


                if (((TL_VEL + TR_VEL + BL_VEL + BR_VEL) / 4) != targetVelocity) {
                    correctionTL = acc.acceleratorCorrector(targetVelocity, TL_VEL);
                    correctionTR = acc.acceleratorCorrector(targetVelocity, TR_VEL);
                    correctionBL = acc.acceleratorCorrector(targetVelocity, BL_VEL);
                    correctionBR = acc.acceleratorCorrector(targetVelocity, BR_VEL);
                } else {
                    correctionTR = correctionTL = correctionBR = correctionBL = 0;
                }
            }
        }
    }

    public double goToPositionForward(double position, double power) {
        double correction = 0.002;

        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPosTL = map.TL.getCurrentPosition();
        int encoderPosTR = map.TR.getCurrentPosition();
        int encoderPosBL = map.BL.getCurrentPosition();
        int encoderPosBR = map.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        map.TL.setPower(power + correctionTL);
        map.TR.setPower(power + correctionTR);
        map.BL.setPower(power + correctionBL);
        map.BR.setPower(power + correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = map.TL.getCurrentPosition();
            encoderPosTR = map.TR.getCurrentPosition();
            encoderPosBL = map.BL.getCurrentPosition();
            encoderPosBR = map.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
        return avgEncPos;
    }

    public void correctionRight(double position, double power) {
        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPositionTL = map.TL.getCurrentPosition();
        int encoderPositionBR = map.BR.getCurrentPosition();

        double avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
        double avgEncoderPosFix = Math.floor(avgEncoderPos);

        map.BR.setPower(-power + correctionBR);
        map.TL.setPower(power - correctionTL);

        while (opModeIsActive() && (avgEncoderPosFix < position)) {
            encoderPositionTL = map.TL.getCurrentPosition();
            encoderPositionBR = map.BR.getCurrentPosition();

            avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
            avgEncoderPosFix = Math.floor(avgEncoderPos);

            telemetry.addData("Average Encoder Positions: ", avgEncoderPosFix);
            telemetry.update();
        }

        rest();
    }

    public void encoderStrafeRight(double position, double power) {
        double correction = 0.002;

        resetEncoders(map.TL);
        resetEncoders(map.BR);
        resetEncoders(map.TR);
        resetEncoders(map.BL);

        int encoderPosTL = map.TL.getCurrentPosition();
        int encoderPosTR = map.TR.getCurrentPosition();
        int encoderPosBL = map.BL.getCurrentPosition();
        int encoderPosBR = map.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        map.TL.setPower(power - correctionTL);
        map.TR.setPower(-power + correctionTR);
        map.BL.setPower(-power + correctionBL);
        map.BR.setPower(power - correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = map.TL.getCurrentPosition();
            encoderPosTR = map.TR.getCurrentPosition();
            encoderPosBL = map.BL.getCurrentPosition();
            encoderPosBR = map.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }

    public void strafeRight(double power) {
        map.TL.setPower(power - correctionTL);
        map.TR.setPower(-power + correctionTR);
        map.BL.setPower(-power + correctionBL);
        map.BR.setPower(power - correctionBR);
    }

    public void strafeLeft(double power) {
        map.TL.setPower(-power + correctionTL);
        map.TR.setPower(power - correctionTR);
        map.BL.setPower(power - correctionBL);
        map.BR.setPower(-power + correctionBR);
    }


}
