package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.GENETX_ENVIRON.hardwareMap;

public abstract class TeleBase extends LinearOpMode {

    CONSTANTS constants = new CONSTANTS();
    hardwareMap map = new hardwareMap();
    acceleration acc = new acceleration();

    double correctionTL, correctionTR, correctionBL, correctionBR;
    double targetVelocity = 9700;

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public void toggleGlobalVar(boolean toggled) {
        toggled = !toggled;
    }

    public double changePerTime(double num) {
        double firstRead = num;
        map.globalTime.reset();
        while (map.globalTime.milliseconds() < 20) {

        }
        map.globalTime.reset();
        double lastRead = num;

        return (lastRead - firstRead) / 20.0; //unit per milliseconds
    }

    public class odometrySubSystem implements Runnable{

        double TL_VEL = changePerTime(map.TL.getCurrentPosition());
        double TR_VEL = changePerTime(map.TL.getCurrentPosition());
        double BL_VEL = changePerTime(map.TL.getCurrentPosition());
        double BR_VEL = changePerTime(map.TL.getCurrentPosition());

        @Override
        public void run() {
            while (!isStopRequested()) {
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
}
