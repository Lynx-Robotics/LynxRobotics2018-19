package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "acc3")
@Disabled
public class acc3 extends autoBaseV4 {

    double correctionTL, correctionTR, correctionBL, correctionBR;
    acceleration acc = new acceleration();

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);
        
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Change in TL: ", changePerTime(chart.TL.getCurrentPosition()));
            telemetry.update();
        }
    }

    public class armRaiseSubSystem implements Runnable{
        @Override
        public void run() {
            
        }
    }

    public class odometrySubSystem implements Runnable{
        @Override
        public void run() {
            while(!isStopRequested()){
                
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

        correctionTL = acc.acceleratorCorrector(power, chart.TL.getPower());
        correctionTR = acc.acceleratorCorrector(power, chart.TR.getPower());
        correctionBL = acc.acceleratorCorrector(power, chart.BL.getPower());
        correctionBR = acc.acceleratorCorrector(power, chart.BR.getPower());

        chart.TL.setPower(power + correctionTL);
        chart.TR.setPower(power + correctionTR);
        chart.BL.setPower(power + correctionBL);
        chart.BR.setPower(power + correctionBR);

        while (opModeIsActive() && (avgEncPosFixed < position)) {
            telemetry.addData("In Rotation: ", true);
            telemetry.update();

            correctionTL = acc.acceleratorCorrector(power, chart.TL.getPower());
            correctionTR = acc.acceleratorCorrector(power, chart.TR.getPower());
            correctionBL = acc.acceleratorCorrector(power, chart.BL.getPower());
            correctionBR = acc.acceleratorCorrector(power, chart.BR.getPower());

            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPosTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();

            avgEncPos = (double) (Math.abs(encoderPosTL) + Math.abs(encoderPosTR) + Math.abs(encoderPosBL) + Math.abs(encoderPosBR)) / 4.0;
            avgEncPosFixed = Math.floor(avgEncPos);
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
}
