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

    public DcMotor BL;
    public DcMotor BR;
    public DcMotor TR;
    public DcMotor TL;

    public Servo hooker1;
    public Servo hooker2;

    final double strafeSpeed = 1.0;
    final double closePosition = 1.0;
    final double openPosition = 0.0;

    private int upperBound = 1600;
    private int lowerBound = 0;

    private double speedMultip;

    Servo middleGrab;

    @Override
    public void runOpMode() throws InterruptedException {
        middleGrab = hardwareMap.get(Servo.class, "middleGrab");
        hooker1 = hardwareMap.get(Servo.class, "hookRight");
        hooker2 = hardwareMap.get(Servo.class, "hookLeft");

        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        TR = hardwareMap.get(DcMotor.class, "TR");
        TL = hardwareMap.get(DcMotor.class, "TL");

        TL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        speedMultip = 1.0;

        waitForStart();

        while(opModeIsActive()){



            if (gamepad1.a){
                middleGrab.setPosition(1.0);
            }
            else if(gamepad1.y) {
                middleGrab.setPosition(0.6);
            }

            TL.setPower(gamepad1.right_stick_y);
            BL.setPower(gamepad1.right_stick_y);
            TR.setPower(gamepad1.left_stick_y);
            BR.setPower(gamepad1.left_stick_y);

            if (gamepad1.dpad_left){
                TL.setPower(1.0);
                BL.setPower(-1.0);
                BR.setPower(-1.0);
                TR.setPower(1.0);
            }
            else if (gamepad1.dpad_right) {
                TL.setPower(-1.0);
                BL.setPower(1.0);
                BR.setPower(1.0);
                TR.setPower(-1.0);
            }

            if (gamepad1.left_bumper) {
                hooker1.setPosition(0.1);
                hooker2.setPosition(0.1);
            }

            if (gamepad1.right_bumper) {
                hooker1.setPosition(0.9);
                hooker2.setPosition(0.9);
            }
        }
    }
}