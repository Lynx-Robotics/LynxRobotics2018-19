package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "encoderTheory", group = "exper")
public class encoderTheory extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        chart.TL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chart.TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        chart.TL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        chart.TR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        chart.TL.setPower(-0.1);
        encoderWait(chart.TL, 3000);

        rest();



    }

    public void DriveWithEncoder(int encoderNum, double power){
        int encoderPosBR = chart.BR.getCurrentPosition();
        int encoderPosBL = chart.BL.getCurrentPosition();
        int encoderPosTL = chart.TL.getCurrentPosition();
        int encoderPesTR = chart.TR.getCurrentPosition();

        double tempPower = chart.power;

        while(!isInRange(encoderPosTL,2, encoderNum)&& !isInRange(encoderPesTR, 2, encoderNum)
                && !isInRange(encoderPosBR, 2, encoderNum) && !isInRange(encoderPosBL, 2, encoderNum)){
            telemetry.addData("Percentage Complete: (Left Side) ", (
                    ((double)chart.TL.getCurrentPosition()/(double)encoderNum)*100));
            telemetry.addData("Percentage Complete: (Right Side) ",
                    (((double)chart.TR.getCurrentPosition()/(double)encoderNum)*100));
            telemetry.addData("Position of Left Side: ", chart.TL.getCurrentPosition());
            telemetry.addData("Position of Right Side: ", chart.TR.getCurrentPosition());
            telemetry.update();

            chart.power = power;

            encoderPosTL = chart.TL.getCurrentPosition();
            encoderPesTR = chart.TR.getCurrentPosition();
            encoderPosBL = chart.BL.getCurrentPosition();
            encoderPosBR = chart.BR.getCurrentPosition();
        }
        chart.power = tempPower;
        rest();
    }

    public void encoderWait(DcMotor motor, int targetEnc){
        int encoderPos= motor.getCurrentPosition();


        while(!isInRange(encoderPos, 15, targetEnc)){
            telemetry.addData("Position of Left Side: ", chart.TL.getCurrentPosition());
            telemetry.addData("Position of Right Side: ", chart.TR.getCurrentPosition());
            telemetry.update();

            encoderPos = motor.getCurrentPosition();

        }
    }
}
