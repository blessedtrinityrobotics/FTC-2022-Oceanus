package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp(name="2nd Hub Test") // Compatible with Java annotations
class exHubTest : OpMode() {
    // the lateinit modifier allows you to wait to initialize a variable in a neat fashion
    private lateinit var test: DcMotor

    override fun init() {
        test = hardwareMap.dcMotor.get("test")
    }

    override fun loop() {
        if (gamepad1.a) {
            test.power = 1.0
        } else if (gamepad1.b) {
            test.power = -1.0
        }
    }
}






