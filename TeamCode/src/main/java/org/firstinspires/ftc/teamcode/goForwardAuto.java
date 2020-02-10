package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "goForwardAuto", group = "test")
//@Disabled
public class goForwardAuto extends autoBaseV4 {

    double targetVoltage = 1.0, targetVelocity = 10000;
    acceleration acc = new acceleration();

    double correctionTL, correctionTR, correctionBL, correctionBR;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        Runnable odomSubSystem = new odomChecker();
        Thread odometrySubSystem = new Thread(odomSubSystem);

        waitForStart();
        odometrySubSystem.start();

        while(opModeIsActive()){
            goToPositionForward(distance2encoderNew(13), 0.4);
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



                if(TL_VEL != targetVelocity){
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

}

