package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENCODER_FOUNDATION_BLUE_V3")
public class Encoder_Foundation_Blue_v3 extends autoBaseV2 {
    double distPhase1 = 40; //go forwards fast
    double distPhase1a = 7; //go forwards slower and grab
    double distPhase2 = 22; //strafe left
    double distPhase3 = 44; //go backwards
    double distPhase4 = 48; //park

    double upPos = 503, downPos = 13;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);
        double distPhase1Revised = distance2encoder(distPhase1);
        double distPhase1aRevised = distance2encoder(distPhase1a);

        waitForStart();
        /*
        Pre Run Phase
         */
        goToPosition(chart.elevMotor, upPos, 1.0);

        /*
        PHASE 1: Go Forward
         */
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distPhase1Revised, 1.0);
        sleep(500);

        driveForward(0.23);
        while(opModeIsActive() && FoundationDetected(chart.colorSensorLeft)){
            telemetry.addData("Going to the foundation", FoundationDetected(chart.colorSensorLeft));
            telemetry.update();
        }
        rest();

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distPhase1aRevised, 0.18);



        goToPositionDown(chart.elevMotor, downPos, -1.0);

        sleep(1500);

        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distPhase1Revised, -1.0);

        while(opModeIsActive() && chart.DebugSwitch){
            telemetry.addData("Got to the end", true);
        }
    }
}