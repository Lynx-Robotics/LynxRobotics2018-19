package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.reflect.Type;

@TeleOp(name = "manualOp", group = "DeadLine")
//@Disabled
public class BasicTele extends LinearOpMode {

    Boolean switches = false;

    public DcMotor BL;


    //Servo middleGrab;

    @Override
    public void runOpMode() throws InterruptedException {
        //middleGrab = hardwareMap.get(Servo.class, "middleGrab");
        BL = hardwareMap.get(DcMotor.class, "BL");

        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Current Position in motor: ", BL.getCurrentPosition());
            telemetry.update();
        }
    }

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public double sqaureRootPowerControl(DcMotor motor, int target, double tuning){
        //tuning should be three for experimental
        int currentPos = motor.getCurrentPosition();
        double pError = (double)(currentPos - target) / (double)target;

        double underSqrt = tuning * pError;
        double squareRootResult = Math.sqrt(underSqrt);
        double correctionPower = 1 / (squareRootResult);

        return 1 - correctionPower;


    }

    public void goToPosition(DcMotor motor, int position, double power) {
        resetEncoders(motor);

        int currentPos = motor.getCurrentPosition();
        double pError = (double)(currentPos - position) / (double)position;
        int motorPosition = motor.getCurrentPosition();

        motor.setPower(power);

        while ((motorPosition <= position) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", motor.getCurrentPosition());
            telemetry.update();

            motorPosition = motor.getCurrentPosition();
        }
        motor.setPower(0);

        switches = true;
    }

}