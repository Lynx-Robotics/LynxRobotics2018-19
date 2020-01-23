package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "tapeSpotter", group = "test")
//@Disabled
public class parkTest extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        goForward(1.0);
        /*while(!tapeSpotted()){

        }*/
        rest();
    }
}
