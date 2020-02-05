package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "strafeTesterVB")
public class strafeTesterVB extends autoBaseV3 {

    int tick = 1;
    double power = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Power Value: ", power);
            telemetry.update();

            if(tick % 2 == 0){
                goToPositionForward((distance2encoderNew(35) - distance2encoderNew(0.3)), 0.4);
                goToPositionBackward((distance2encoderNew(35) - distance2encoderNew(0.3)), 0.4);
                rest();

//                encoderStrafeRight(distance2encoderNew(12), 0.35);

                tick++;
            }

            if(gamepad1.a){
                tick++;
                while(opModeIsActive() && gamepad1.a){

                }
            }

            if(gamepad1.dpad_down){
                power = power * -1;
                while(opModeIsActive() && gamepad1.dpad_down);
            }
        }
    }
}
