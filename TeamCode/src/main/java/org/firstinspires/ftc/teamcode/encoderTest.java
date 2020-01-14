package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class encoderTest extends autoBase {
    public final int TICKSPERROTATION = 1120;


    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        chart.TL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chart.TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chart.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chart.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

    }
}
