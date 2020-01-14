package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "BasicAuto", group = "Basic")
public class BasicAuto extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();

    DcMotor tm;

    int targetPoint = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        tm = hardwareMap.get(DcMotor.class, "t");
        tm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(opModeIsActive()) {


        }
    }

}
