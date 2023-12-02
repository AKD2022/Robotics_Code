//package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Servo", group="OpMode")
public class Servo extends OpMode {

    private Servo ClawServo;
    private boolean xButtonPressed;

    @Override
    public void init() {
        ClawServo = hardwareMap.servo.get(Servo.class, "Name"); // Replace Name Later
    }

    @Override 
    public void loop() {
        if (gamepad1.x) {
            if(!xButtonPressed) {
                xButtonPressed = true;

                ClawServo.setPosition(0.0);
            }
            ClawServo.setPosition(0.5);
        } else {
            xButtonPressed = false;
            ClawServo.setPosition(0.0);
        }
    }
}