package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "imuTester")
public class imuTester extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);

        waitForStart();
        rotate(90, 0.4);

    }
}
