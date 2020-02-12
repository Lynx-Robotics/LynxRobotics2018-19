package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "schedule")
public class trual extends autoBaseV5A {

    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        Runnable odometryPrimary = new odometryPrimary();
        Thread oss = new Thread();
        oss.start();

        waitForStart();
        globalPhase++;
        while(opModeIsActive()){
            goToPositionForward(distance2encoderNew(29), 0.8);

            encoderStrafeLeft(distance2encoderNew(12), 0.4);
            strafeRight(0.45);
            while(opModeIsActive() && !SkyStoneReBornRight(map.colorSensorRight));
            rest();

            encoderStrafeRight(distance2encoderNew(4.5), 0.45);

            goToPositionForward(distance2encoderNew(4), 0.45);

            goToPositionBackward(distance2encoderNew(10), 0.8);

            turnRight(0.6);

            goForward(0.45);
            while(opModeIsActive() && !bottomTapeSensorDetectedRedReborn1(map.bottomColorSensor));

            goToPositionForward(distance2encoderNew(51), 0.8);
            rest();
            while (opModeIsActive()){

            }
        }
    }
}
