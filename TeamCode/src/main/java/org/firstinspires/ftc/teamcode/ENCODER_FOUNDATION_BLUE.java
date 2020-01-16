package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "ENCODER_FOUNDATION_BLUE", group = "encoder")
public class ENCODER_FOUNDATION_BLUE extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();

        //goes forward slightly so it can grab the foundation
        driveWithEncoder(encoderTicksForDistance(90.015), 0.3);
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        wait(5.0);

        //turns left so it can realign with the foundation
        rotate(90, chart.power);
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        rest();
        wait(5.0);

        //goes towards the center of the foundation
        driveWithEncoder(encoderTicksForDistance(29.18079), 0.3);
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        wait(5.0);

        //turns right so it can grab the foundation
        rotate(-90, chart.power);
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        rest();
        wait(5.0);

        //drives closer to the foundation so it can grab
        driveWithEncoder(encoderTicksForDistance(22.86), 0.3);
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        wait(5.0);

        //drops the hookers onto the foundation
        dropDL();
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        wait(5.0);

        //goes back with foundation
        driveWithEncoder(encoderTicksForDistance(150), -0.6);
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        wait(5.0);

        //goes forward slightly so it doesn't get caught on the edge of the wall
        goForward(0.5);
        wait(0.5);
        rest();

        //strafes right so it can later turn
        strafeRightFullThrottle();
        wait(1.5);
        rest();
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        wait(5.0);

        //turns right to get ready for parking
        rotate(-90, chart.power);
        telemetry.addData("Currently analyzing results", "");
        telemetry.update();
        rest();

        /*
        if there is an object within the next 30 centimeters then we tell it to simply stop (for now).
        In later iterations of the code it will move to the left and then it will park in the empty spot.
         */
        if(chart.distanceSensor.getDistance(DistanceUnit.CM)<30){
            rest();
        }
        /*
        In the case that there is not an object then it will go full throttle until it sees the tape
         */
        else{
            goForward(1.0);
            while(opModeIsActive()&&!tapeSpotted()){
                telemetry.addData("Looking for the tape", !tapeSpotted());
            }
            wait(0.2);
            rest();
        }
    }
}
