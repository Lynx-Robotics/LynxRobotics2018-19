package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENCODER_FOUNDATION_BLUE_V3")
public class Encoder_Foundation_Blue_v3 extends autoBaseV2 {
    double distPhase1 = 44; //go forwards
    double distPhase2 = 22; //strafe left
    double distPhase3 = 44; //go backwards
    double distPhase4 = 48; //park

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        /*
        PHASE 1: Go Forward
         */
        //encoderDrive(18000, 0.5);
        /*chart.TL.setPower(chart.TR.getPower());
        chart.BL.setPower(chart.TR.getPower());
        chart.BR.setPower(chart.TR.getPower());*/
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, 1018, 1.0);

        while(opModeIsActive() && chart.DebugSwitch){

        }




        /*
        PHASE 2: Strafe left
         */
        //encoderStrafeLeft(distance2encoder(distPhase2), 0.5);

        /*
        PHASE 3: Sink the teeth
         */
        //dropDL();
        //wait(1.2, "Phase 3: Dropping Hooks");

        /*
        PHASE 4: going backwards
         */
        //encoderDrive(distance2encoder(distPhase3), -0.5);

        /*
        PHASE 5: strafing right
         */
        //encoderStrafeRight(distance2encoder(distPhase4), 0.5);
        //chart.DebugSwitch2 = true;

        //while(opModeIsActive() && chart.DebugSwitch2);

    }
}
