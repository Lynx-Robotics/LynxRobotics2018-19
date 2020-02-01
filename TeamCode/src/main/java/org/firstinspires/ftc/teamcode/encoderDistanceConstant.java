package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "encoderDistanceConstant")
@Disabled
public class encoderDistanceConstant extends autoBaseV2 {

    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);

        waitForStart();

        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoderNewFullVolt(96.2), 1.0);

        while(opModeIsActive()){
            telemetry.addData("Encoder for TL: ", chart.TL.getCurrentPosition());
            telemetry.addData("Encoder for TR: ", chart.TR.getCurrentPosition());
            telemetry.addData("Encoder for BL: ", chart.BL.getCurrentPosition());
            telemetry.addData("Encoder for BR: ", chart.BR.getCurrentPosition());
            telemetry.update();
        }

    }
}
