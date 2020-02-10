package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "odometryReadOut", group = "test")
//@Disabled
public class odometryChecker extends autoBaseV4 {

    acceleration acc = new acceleration();

    double correctionTL, correctionTR, correctionBL, correctionBR;
    double targetVoltage = 1.0, targetVelocity = 10000;

    double reportedChangeTL, reportedChangeTR, reportedChangeBL, reportedChangeBR;
    
    int phase = 2;

    double power;
    
    @Override
    public void runOpMode() throws InterruptedException {

        chart.init(hardwareMap);
        
        reportedChangeTL = changePerTime(chart.TL.getCurrentPosition());
        reportedChangeTR = changePerTime(chart.TR.getCurrentPosition());
        reportedChangeBL = changePerTime(chart.BL.getCurrentPosition());
        reportedChangeBR = changePerTime(chart.BR.getCurrentPosition());


        Runnable odometrySubSystem = new odomChecker();
        Thread thread = new Thread(odometrySubSystem);

        thread.start();

        waitForStart();
        while(opModeIsActive()){

            reportedChangeTL = changePerTime(chart.TL.getCurrentPosition());
            reportedChangeTR = changePerTime(chart.TR.getCurrentPosition());
            reportedChangeBL = changePerTime(chart.BL.getCurrentPosition());
            reportedChangeBR = changePerTime(chart.BR.getCurrentPosition());

            telemetry.addData("Power Sent: ", power);
            telemetry.addData("Reported Change (TL): ", reportedChangeTL + "enc/ms");
            telemetry.addData("Reported Change (TR): ", reportedChangeTR + "enc/ms");
            telemetry.addData("Reported Change (BL): ", reportedChangeBL + "enc/ms");
            telemetry.addData("Reported Change (BR): ", reportedChangeBR + "enc/ms");
            telemetry.update();

        }
    }

    public class odomChecker implements Runnable{
        @Override
        public void run() {
            while(!isStopRequested()){
                if(gamepad1.a) phase++;
                if(gamepad1.dpad_up){
                    power = power + 0.05;
                    while(gamepad1.dpad_up);
                }
                if(gamepad1.dpad_up){
                    power = power - 0.05;
                    while(gamepad1.dpad_up);
                }

                if(phase%2 == 0){
                    chart.TL.setPower(power);
                    chart.TR.setPower(power);
                    chart.BL.setPower(power);
                    chart.BR.setPower(power);
                }
                else{
                    rest();
                }
            }
        }
    }
}
