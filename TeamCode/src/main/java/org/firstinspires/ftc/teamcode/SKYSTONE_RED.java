/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "SKYSTONE_RED", group = "Experiment")
//@Disabled
public class SKYSTONE_RED extends autoBase {
    public boolean distanceSensorWorking = false;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);
        while (!chart.imu.isGyroCalibrated() && !isStopRequested()){
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", chart.imu.getCalibrationStatus().toString());
        telemetry.update();

        distanceSensorWorking = timeoutDistSensor(); //if true then sensor is working - false then it's not

        waitForStart();
        chart.globalTime.reset();
        chart.runtime.reset();

        if (distanceSensorWorking){
            goForward(0.55);
            wait(1.4);
            rest();

            sleep(700);

            chart.runtime.reset();
            strafeLeft(0.3);
            wait(1.9);
            rest();

            sleep(700);

            goForward(0.55);
            while(opModeIsActive() && !tripWireActive(14)){
                telemetry.addData("going forward to the stone", true);
                telemetry.addData("Distance: ", chart.distanceSensor.getDistance(DistanceUnit.CM));
                telemetry.addData("Distance Sensor Working?: ", distanceSensorWorking);
                telemetry.update();
            }
            rest();

            strafeLeft();
            while(opModeIsActive() && !SkyStoneSpotted(chart.colorSensorLeft, 10)){
                telemetry.addData("spotting stone", true);
                telemetry.addData("Distance: ", chart.distanceSensor.getDistance(DistanceUnit.CM));
                telemetry.addData("Distance Sensor Working?: ", distanceSensorWorking);

                telemetry.update();
            }
            rest();

            strafeRight(0.3);
            while(opModeIsActive() && SkyStoneSpotted(chart.colorSensorLeft, 10)){
                telemetry.addData("moving right to the robot", true);
                telemetry.addData("Distance: ", chart.distanceSensor.getDistance(DistanceUnit.CM));
                telemetry.addData("Distance Sensor Working?: ", distanceSensorWorking);
                telemetry.update();
            }
            sleep(300);
            rest();
            goForward();
            wait(0.2);

            grabServo();
            wait(0.6);

            goBack();
            wait(3.8);

            goForward();
            wait(0.2);

            rotate(-90, chart.power);
            rest();

            goForward();
            while (tripWireActive(30)){

            }
            rest();

            while(chart.globalTime.seconds()<19){

            }

            park(20);
        }
        else {
            goForward(0.55);
            wait(1.4);
            rest();

            sleep(700);

            //strafe left
            chart.runtime.reset();
            chart.TL.setPower(0.3 + joltControl(chart.runtime));
            chart.TR.setPower(-0.3);
            chart.BL.setPower(-0.3);
            chart.BR.setPower(0.3 + joltControl(chart.runtime));
            wait(1.9);
            rest();

            goForward(0.55);
            wait(0.5);
            rest();

            chart.runtime.reset();
            chart.TL.setPower(chart.powerUp + joltControl(chart.runtime));
            chart.TR.setPower(chart.powerDown);
            chart.BL.setPower(chart.powerDown + joltControl(chart.runtime));
            chart.BR.setPower(chart.powerUp);



            //sleep(2000);

            while(opModeIsActive()&&!SkyStoneSpotted(chart.colorSensorLeft, 10)){
                telemetry.addData("Status: ", SkyStoneSpotted(chart.colorSensorLeft, 10));
                telemetry.update();

            }

            rest();
            //sleep(2000);

            chart.TL.setPower(-0.3 + joltControl(chart.runtime));
            chart.TR.setPower(0.3);
            chart.BL.setPower(0.3);
            chart.BR.setPower(-0.3 );
            while(opModeIsActive()&&SkyStoneSpotted(chart.colorSensorLeft,10 )){
                telemetry.addData("Status: ", SkyStoneSpotted(chart.colorSensorLeft,10 ));
                telemetry.update();
            }
            sleep(300);
            rest();
            goForward();
            wait(0.2, "pushing forward");

            grabServo();

            wait(0.6, "grabbing Servo (PHASE 2)");

            goBack();
            wait(3.8, "go back (PHASE 3)");
            rest();

            goForward();
            wait(0.2);

            rotate(-90, chart.power);

            rest();

            raiseDL();
            wait(2.0);

            goForward();
            wait(4.3);
            rest();

            goBack();
            wait(1.5);
            rest();

            park(10);

        }
    }
}
*/
