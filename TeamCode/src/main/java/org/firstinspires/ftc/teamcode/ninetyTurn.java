package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ninetyTurn")
@Disabled
public class ninetyTurn extends autoBaseV5A {

    int globalPhase = 0;
    double targetVoltage = 1.0, targetVelocity = 9500;
    double correctionTL, correctionTR, correctionBL, correctionBR;
    acceleration acc = new acceleration();


    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        Runnable odometrySubSystem = new odomChecker();
        Thread oss = new Thread(odometrySubSystem);
        oss.start();

        waitForStart();
        while(opModeIsActive()){
            correctionLeft(distance2encoderNew(17.31), 0.45);
            break;
        }
    }

    public class odomChecker implements Runnable{
        @Override
        public void run() {
            while(!isStopRequested()){
                double TL_VEL = changePerTime(map.TL.getCurrentPosition());
                double TR_VEL = changePerTime(map.TL.getCurrentPosition());
                double BL_VEL = changePerTime(map.TL.getCurrentPosition());
                double BR_VEL = changePerTime(map.TL.getCurrentPosition());



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
}
