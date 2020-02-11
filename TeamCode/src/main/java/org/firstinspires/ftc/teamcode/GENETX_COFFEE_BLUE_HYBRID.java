package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "GENETX COFFEE BLUE HYBRID")
public class GENETX_COFFEE_BLUE_HYBRID extends autoBaseV4 {

    int globalPhase = 0;
    double targetVoltage = 1.0, targetVelocity = 9500;
    double correctionTL, correctionTR, correctionBL, correctionBR;
    acceleration acc = new acceleration();

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        Runnable odometrySubSystem = new odomChecker();
        Runnable elevatorSubSystem = new elevatorChecker();

        Thread oss = new Thread(odometrySubSystem);
        Thread ess = new Thread(elevatorSubSystem);
//        Thread css = new Thread()

        oss.start();
        ess.start();


        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.a){
                while(gamepad1.a){

                }
                globalPhase++;
            }
            if(globalPhase == 1) {
                goToPositionForward(distance2encoderNew(28), 0.8);
                globalPhase++;
            }

            if(globalPhase == 2){
                correctionRight(distance2encoderNew(2.0), 0.3);

                encoderStrafeRight(distance2encoderNew(8), 0.38);
                globalPhase++;
            }

            if(globalPhase == 3){
                strafeLeft(0.4);
                while(opModeIsActive() && !SkyStoneReBornRight(chart.colorSensorRight)){

                }
                rest();
                globalPhase++;
            }

            if(globalPhase == 4){
                encoderStrafeRight(distance2encoderNew(5), 0.38);
                globalPhase++;
            }
        }
    }

    public class elevatorChecker implements Runnable{
        int internalPhase = 0;

        @Override
        public void run() {
            while(!isStopRequested()){
                if(globalPhase == 0 && internalPhase == 0){
                    internalPhase++;
                }
                if(globalPhase == 1 && internalPhase == 1){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e){
                        requestOpModeStop();
                    }
                    elevControl(chart.elevMotor, 500, 1.0);
                    internalPhase++;
                }
                if (globalPhase == 4 && internalPhase == 2){

                }
            }
        }
    }

    public class odomChecker implements Runnable{
        @Override
        public void run() {
            while(!isStopRequested()){
                double TL_VEL = changePerTime(chart.TL.getCurrentPosition());
                double TR_VEL = changePerTime(chart.TL.getCurrentPosition());
                double BL_VEL = changePerTime(chart.TL.getCurrentPosition());
                double BR_VEL = changePerTime(chart.TL.getCurrentPosition());



                if(((TL_VEL+TR_VEL+BL_VEL+BR_VEL)/4) != targetVelocity){
                    correctionTL = acc.acceleratorCorrector(targetVelocity, TL_VEL);
                    correctionTR = acc.acceleratorCorrector(targetVelocity, TR_VEL);
                    correctionBL = acc.acceleratorCorrector(targetVelocity, BL_VEL);
                    correctionBR = acc.acceleratorCorrector(targetVelocity, BR_VEL);
                }
                else {
                    correctionTR = correctionTL = correctionBR = correctionBL = 0;
                }
            }
        }
    }

    public void goToPositionForward(double position, double power) {
        double correction = 0.002;

        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(power + correctionTL);
        chart.TR.setPower(power + correctionTR);
        chart.BL.setPower(power + correctionBL);
        chart.BR.setPower(power + correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }

    public void correctionRight(double position, double power) {
        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPositionTL = chart.TL.getCurrentPosition();
        int encoderPositionBR = chart.BR.getCurrentPosition();

        double avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
        double avgEncoderPosFix = Math.floor(avgEncoderPos);

        chart.BR.setPower(-power + correctionBR);
        chart.TL.setPower(power - correctionTL);

        while (opModeIsActive() && (avgEncoderPosFix < position)) {
            encoderPositionTL = chart.TL.getCurrentPosition();
            encoderPositionBR = chart.BR.getCurrentPosition();

            avgEncoderPos = (double) (Math.abs(encoderPositionTL) + Math.abs(encoderPositionBR)) / 2.0;
            avgEncoderPosFix = Math.floor(avgEncoderPos);

            telemetry.addData("Average Encoder Positions: ", avgEncoderPosFix);
            telemetry.update();
        }

        rest();
    }

    public void encoderStrafeRight(double position, double power) {
        double correction = 0.002;

        resetEncoders(chart.TL);
        resetEncoders(chart.BR);
        resetEncoders(chart.TR);
        resetEncoders(chart.BL);

        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPosTR = chart.TR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosBR = chart.BR.getCurrentPosition();

        double avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
        double avgEncPosFixed = Math.floor(avgEncPos);

        chart.TL.setPower(power - correctionTL);
        chart.TR.setPower(-power + correctionTR);
        chart.BL.setPower(-power + correctionBL);
        chart.BR.setPower(power - correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
        }
        rest();
    }

    public void strafeRight(double power){
        chart.TL.setPower(power - correctionTL);
        chart.TR.setPower(-power + correctionTR);
        chart.BL.setPower(-power + correctionBL);
        chart.BR.setPower(power - correctionBR);
    }

    public void strafeLeft(double power){
        chart.TL.setPower(-power + correctionTL);
        chart.TR.setPower(power - correctionTR);
        chart.BL.setPower(power - correctionBL);
        chart.BR.setPower(-power + correctionBR);
    }


}
