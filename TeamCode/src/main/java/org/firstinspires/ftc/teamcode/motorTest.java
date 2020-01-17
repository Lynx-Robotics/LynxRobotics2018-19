package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "motorTest")
public class motorTest extends TeleBase {

    public DcMotor TL;

    @Override
    public void runOpMode() throws InterruptedException {
        TL = hardwareMap.get(DcMotor.class, "TL");

        waitForStart();
        while(opModeIsActive()){
            chart.TL.setPower(gamepad1.left_stick_y);
        }
    }
}
