package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "parking")
public class parking extends autoBaseV5A {

    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        Runnable odometryPrimary = new odometryPrimary();
        Thread oss = new Thread(odometryPrimary);
        oss.start();

        waitForStart();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        map.globalTime.reset();
        map.TAPEMOTOR.setPower(-1.0);
        while (map.globalTime.seconds() < 0.5) {

        }
        map.TAPEMOTOR.setPower(0);

    }
}
