package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="Test TeleOp", group="Test")
class TestTeleop : OpMode() {
    lateinit var slide: Slide

    override fun init() {
        slide = Slide(hardwareMap, telemetry)
    }

    override fun loop() {
        if (gamepad1.dpad_up)
            slide.test(0.25)
        else if (gamepad1.dpad_down)
            slide.test(-0.25)
        else
            slide.test(0.0)

        slide.logPosition()
        telemetry.update()
    }
}