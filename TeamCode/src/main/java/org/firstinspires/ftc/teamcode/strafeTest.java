package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "strafeTest", group = "TEST")
public class strafeTest extends LinearOpMode {

    public DcMotor TL, TR, BL, BR;

    public boolean aBtn;

    public int state = 0;
    public double powerUp, powerDown;

    @Override
    public void runOpMode() throws InterruptedException {
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        TR = hardwareMap.get(DcMotor.class, "TR");
        TL = hardwareMap.get(DcMotor.class, "TL");

        TL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        aBtn = gamepad1.a;
        powerUp = 0.5;
        powerDown = -0.5;


        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("POWER LEVEL: ", "powerUp is at "+powerUp);
            telemetry.addData("POWER LEVEL: ", "powerDown is at "+powerDown);

            if(gamepad1.dpad_right){
                TL.setPower(powerUp+joltControl());
                TR.setPower(powerDown);
                BL.setPower(powerDown);
                BR.setPower(powerUp);
            }

            else if (gamepad1.dpad_left){
                TL.setPower(powerDown);
                TR.setPower(powerUp);
                BL.setPower(powerUp);
                BR.setPower(powerDown);
            }

            else {
                TL.setPower(gamepad1.left_stick_y);
                TR.setPower(gamepad1.right_stick_y);
                BL.setPower(gamepad1.left_stick_y);
                BR.setPower(gamepad1.right_stick_y);
            }


            telemetry.addData("POWER LEVEL: ", "TL - "+(TL.getPower()));
            telemetry.update();
        }

    }

    public double joltControl() {
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();

        if (runtime.seconds()<1.2){
            return 0.05;
        }
        else {
            return 0.0;
        }
    }

}
