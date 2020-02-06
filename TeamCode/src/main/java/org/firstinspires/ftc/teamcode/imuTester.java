package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "imuTester")
public class imuTester extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);

//        while(!chart.imu.isGyroCalibrated() && opModeIsActive()){
//
//        }
        telemetry.addData("Done Calibrating", true);
        telemetry.update();

        waitForStart();
//        rotate(90, 0.4);

    }
}
