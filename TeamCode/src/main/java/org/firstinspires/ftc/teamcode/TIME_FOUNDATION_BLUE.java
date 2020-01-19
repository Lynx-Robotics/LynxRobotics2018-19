package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "TIME_FOUNDATION_BLUE", group = "TIME")
public class TIME_FOUNDATION_BLUE extends autoBase {

    double timePhase1 = 0.3;
    double timePhase2 = 2.0;
    double timePhase3 = 2.0;
    double timePhase4 = 0.5;
    double timePhase5 = 3.0;
    double timePhase6;
    double timePhase7;
    double timePhase8;
    double timePhase9;
    double timePhase10;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        while(chart.imu.isGyroCalibrated() && opModeIsActive()){
            telemetry.addData("Calibrating", "...");
            telemetry.update();
        }


        waitForStart();

        /*
        --------------------------------------------------------------------------------------
        PHASE 1
        --------------------------------------------------------------------------------------
         */

        strafeLeft();
        wait(timePhase1);

        /*
        --------------------------------------------------------------------------------------
        Phase 2
        --------------------------------------------------------------------------------------
         */

        goForward(0.3);
        wait(timePhase2);
        rest();

        /*
        --------------------------------------------------------------------------------------
        Phase 3
        --------------------------------------------------------------------------------------
         */

        dropDL();
        wait(timePhase3);

        /*
        --------------------------------------------------------------------------------------
        Phase 4
        --------------------------------------------------------------------------------------
         */

        goBack(-0.3);
        wait(timePhase4);
        rest();

        /*
        --------------------------------------------------------------------------------------
        Phase 5
        --------------------------------------------------------------------------------------
         */

        strafeRight();
        wait(timePhase5);
        rest();

        /*
        --------------------------------------------------------------------------------------
        Phase 6
        --------------------------------------------------------------------------------------
         */
    }

    public void strafeLeft() {
        chart.TL.setPower(constants.powerDown);
        chart.BL.setPower(constants.powerUp);
        chart.TR.setPower(constants.powerUp);
        chart.BR.setPower(constants.powerDown);
    }

    public void strafeRight(){
        chart.TL.setPower(constants.powerUp);
        chart.BL.setPower(constants.powerDown);
        chart.TR.setPower(constants.powerDown);
        chart.BR.setPower(constants.powerUp);
    }

}
