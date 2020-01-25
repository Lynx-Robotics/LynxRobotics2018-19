package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "accelerationTester")
public class accelerationTester extends autoBaseV2 {
    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();

        if(gamepad1.a) goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoder(40), acceleratePowerReturnExponential(1.0));
        if(gamepad1.b) goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoder(40), acceleratPowerReturn(1.0));
    }
}
