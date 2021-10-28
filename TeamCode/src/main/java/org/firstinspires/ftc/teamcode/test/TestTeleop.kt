package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Scoop
import org.firstinspires.ftc.teamcode.Slide

@TeleOp(name="Test TeleOp", group="Test")
class TestTeleop : OpMode() {
    lateinit var slide: Slide
    lateinit var scoop: Scoop

    override fun init() {
        slide = Slide(hardwareMap, telemetry)
        scoop = Scoop(hardwareMap, telemetry)
    }

    override fun loop() {
        if (gamepad1.dpad_up)
            slide.test(0.25)
        else if (gamepad1.dpad_down)
            slide.test(-0.25)
        else
            slide.test(0.0)

        if (gamepad1.y)
            scoop.pickup()
        else if (gamepad1.x)
            scoop.reset()
        else if (gamepad1.a)
            scoop.dump()

        slide.logPosition()
        telemetry.update()
    }
}