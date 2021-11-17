package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Scoop
import org.firstinspires.ftc.teamcode.hardware.Slide

@TeleOp(name="Test TeleOp", group="Test")
@Disabled
class TestTeleop : OpMode() {
    lateinit var slide: Slide
    lateinit var scoop: Scoop

    override fun init() {
        slide = Slide(hardwareMap, telemetry)
        scoop = Scoop(hardwareMap, telemetry)
    }
/*
* top limit 2363
* bottom: -23
* */
    override fun loop() {
        if (gamepad1.dpad_up)
            slide.move(0.25)
        else if (gamepad1.dpad_down)
            slide.move(-0.25)
        else
            slide.move(0.0)


        if (gamepad1.x)
            scoop.reset()

        slide.logPosition()
        telemetry.update()
    }
}