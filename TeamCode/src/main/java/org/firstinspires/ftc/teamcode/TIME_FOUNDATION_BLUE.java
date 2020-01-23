package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "TIME_FOUNDATION_BLUE", group = "TIME")
public class TIME_FOUNDATION_BLUE extends autoBase {

    double timePhase1 = 0.42;
    double timePhase2 = 1.0 ;
    double timePhase3 = 3.0;
    double timePhase4 = 1.1;
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

        strafeLeft(0.45);
        wait(timePhase1);

        /*
        --------------------------------------------------------------------------------------
        Phase 2
        --------------------------------------------------------------------------------------
         */

        goForward(0.4);
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

        goBack(0.3);
        wait(timePhase4);
        rest();

        /*
        --------------------------------------------------------------------------------------
        Phase 5
        --------------------------------------------------------------------------------------
         */

        strafeRight(0.4);
        wait(timePhase5);
        rest();

        /*
        --------------------------------------------------------------------------------------
        Phase 6
        --------------------------------------------------------------------------------------
         */
    }

}
