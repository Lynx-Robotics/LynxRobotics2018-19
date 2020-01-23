/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "FOUNDATION_BLUE", group = "Experiment")
//@Disabled
public class FOUNDATION_BLUE extends autoBase {
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

        telemetry.addData("Is the sensor working: ", timeoutDistSensor());
        telemetry.update();

        if (distanceSensorWorking){

            statCheck();

            strafeLeft(0.7); //strafes left to center with foundation
            wait(0.4); //duration of strafe
            rest(); //stops motor power

            statCheck();

            sleep(700); //waits 0.7 of a second

            statCheck();

            goForward(0.5); //goes forward at 0.5 power
            while (!tripWireActive(13) && opModeIsActive()){
                //waits until tripwire is activated
            }

            statCheck();
            rest(); //stops sending power to motors
            sleep(700); //waits for 0.7 of second
            statCheck();

            goForward(0.3); //goes forward at 0.3 power
            while (!tripWireActive(9.9)){
                //waits until tripwire is activated
            }
            rest(); //stops sending power to motors

            statCheck();

            dropDL(); //drops the hooks for the foundations
            wait(1.0); //waits one second

            statCheck();

            goBack(); //goes back towards the wall
            wait(4.0); //waits 3.5 seconds to do the backing up
            rest(); //stops sending power to the motors

            statCheck();

            raiseDL(); //raise the grabbers

            statCheck();

            sleep(1000); //waits for half a second

            goForward(0.3); //goes slighly forward to prepare for strafe
            wait(0.2); //waits for 0.2 seconds
            rest(); //stops sending power to motors

            statCheck();

            rotate(-90, chart.power);

            statCheck();

            goForward();
            while (tripWireActive(9)){
                statCheck();
            }
            rest();

            statCheck();
            while(chart.globalTime.seconds()<19){
                statCheck();
            }


            park(10);
            statCheck();
            rest();

        }
        else {
            */
/*
            PHASE 3 Description: Strafes to the left and centers itself with the foundation
             *//*

            chart.TL.setPower(chart.powerUp + joltControl(chart.runtime));
            chart.TR.setPower(chart.powerDown);
            chart.BL.setPower(chart.powerDown);
            chart.BR.setPower(chart.powerUp);

            wait(0.7);
            rest();

            */
/*
            PHASE 4 Description: Goes forward until it gets to the foundation
             *//*

            goForward();
            while (opModeIsActive() && (!tripWireActive(9.5))) {
                telemetry.addData("Status: ", "going towards foundation (PHASE 4)");
                telemetry.update();
            }
            rest();

        */
/*
        PHASE 5 Description: Drops the hookers
         *//*

            dropDL();
            wait(2.0, "dropping hookers (PHASE 5)");

        */
/*
        PHASE 6 Description: Goes back towards the depot
         *//*

            //resetAngle();
            goBack();
            wait(3.0, "going backwards towards depot (PHASE 6)");
            rest();
            sleep(2000);

        */
/*
        PHASE 7: Raises the Hookers and prepares to move our of the way
         *//*

            raiseDL();
            wait(2.0);

        */
/*
        PHASE 8 Description: makes sure that the robot is clear of the foundation
         *//*

            goBack();
            wait(0.5, "repositioning (PHASE 8)");
            rest();

            goForward();
            wait(0.2);

        */
/*
        PHASE 9 Description: strafes to the right and parks
         *//*

            strafeRight();
            wait(4.0, "strafing out and parking (PHASE 8)");
            rest();


            telemetry.addData("Status: ", "MISSION COMPLETE (PHASE 10)");
            telemetry.update();
        }
    }

    public void statCheck(){
        telemetry.addData("going forward to the stone", true);
        telemetry.addData("Distance: ", chart.distanceSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("Distance Sensor Working?: ", distanceSensorWorking);
        telemetry.update();
    }
}
*/
