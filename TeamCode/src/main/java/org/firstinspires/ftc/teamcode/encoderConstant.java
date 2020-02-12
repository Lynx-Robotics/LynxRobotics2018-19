package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "encoderConstant")
public class encoderConstant extends autoBaseV5A {

    double controlDist = 0;
    double controlPower = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        map.init(hardwareMap);

        Runnable odomSubSystem = new odometryPrimary();
        Thread ossP = new Thread(odomSubSystem);

        ossP.start();

        waitForStart();
        while(opModeIsActive()){

            if(gamepad1.a){
                controlPower = controlPower - 0.1;
                while(gamepad1.a);
            }

            if(gamepad1.y){
                controlPower = controlPower + 0.1;
                while(gamepad1.y);
            }

            if(gamepad1.x){
                controlDist = controlDist + 5;
                while(gamepad1.x);
            }

            if(gamepad1.b){
                controlDist = controlDist - 5;
                while(gamepad1.b);
            }

            if(gamepad1.dpad_up){
                telemetry.addData("Avg Encoder Positiong", goToPositionForward(distance2encoderNew(controlDist), controlPower));
            }
            if(gamepad1.dpad_down){
                telemetry.addData("Avg Encoder Positiong", goToPositionBackward(distance2encoderNew(controlDist), controlPower));
            }

            telemetry.update();


        }
    }
}
