package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "COLOR_FOUNDATION_BLUEV2")
public class COLOR_FOUNDATION_BLUE extends autoBase {
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();

        strafeLeft(0.4);
        wait(0.4);
        rest();

        sleep(5000);

        goForward(0.2);

        runtime.reset();
        /*while(opModeIsActive() && !objDetected){
            telemetry.addData("Finding an object", "...");
            telemetry.addData("Object Found?",objDetectedAlpha(-0.05, chart.colorSensorRight, chart.colorSensorLeft));
            telemetry.addData("ObjDetected Variable: ", objDetected);
            telemetry.addData( "ObjDetected Yellow Block: ",objDetectedColor());
            telemetry.update();

            //toggleObjDetected(objDetectedAlpha(-0.05, chart.colorSensorRight, chart.colorSensorLeft));
            toggleObjDetected(!objDetectedColor());
        }*/
        double timeTravelled = runtime.seconds();
        runtime.reset();
        rest();

        dropDL();
        sleep(5000);

        goForward(-0.2);
        wait(timeTravelled);

    }
}
