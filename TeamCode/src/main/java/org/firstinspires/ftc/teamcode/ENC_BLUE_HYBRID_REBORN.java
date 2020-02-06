package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "ENC_BLUE_HYBRID_REBORN")
public class ENC_BLUE_HYBRID_REBORN extends autoBaseV3 {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        //go forward to the blocks
        goToPositionForward(distance2encoderNewFullVolt(27.2), 1.0);

        sleep(350);

        //strafe right to make sure that we are within  scanning distance of the final block
        encoderStrafeRight(distance2encoderNew(8), 0.36);





    }
}
