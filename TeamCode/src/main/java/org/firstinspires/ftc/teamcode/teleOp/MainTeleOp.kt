package org.firstinspires.ftc.teamcode.teleOp

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.*

@TeleOp(name="Main TeleOp")
class MainTeleOp : OpMode() {
    // the lateinit modifier allows you to wait to initialize a variable in a neat fashion

    // I've made specific classes to control the functionality of the hardware on the robot
    // So I can reuse the code between Auto's and Teleops
    private lateinit var drivetrain: Drivetrain
    private lateinit var carousel: CarouselMotor
    private  lateinit var slide: Slide
    private lateinit var scoop: Scoop

    override fun init() {
        carousel = CarouselMotor(hardwareMap, telemetry)
        drivetrain = Drivetrain(hardwareMap, telemetry, null, null) // We don't have a linear op Mode to give to the drivetrain
        slide = Slide(hardwareMap, telemetry)
        scoop = Scoop(hardwareMap, telemetry)

        drivetrain.setRunMode(Motor.RunMode.RawPower)
    }

    override fun loop() {
        val forward = -gamepad1.left_stick_x
        val lateral = gamepad1.left_stick_y
        val yaw = -gamepad1.right_stick_x



        val mult = gamepad1.right_trigger + 1

        drivetrain.mecanumDrive(forward.toDouble(), lateral.toDouble(), yaw.toDouble(), mult.toDouble())

        val power = -gamepad2.left_stick_y * 0.75
        slide.move(power.toDouble())

        if (gamepad2.y)
            scoop.pickup()
        else if (gamepad2.x)
            scoop.reset()
        else if (gamepad2.a)
            scoop.dump()

        if (gamepad2.dpad_right)
            carousel.moveRed()
        else if (gamepad2.dpad_left)
            carousel.moveBlue()




        telemetry.addData("max = ", slide.MAX_TICKS)
        telemetry.addData("slide = ", slide.motor.currentPosition)
        telemetry.addData("Servo angle = ", scoop.servo.position)
        telemetry.addData("Duck encoder = ", carousel.motor.currentPosition)
        telemetry.update()
    }

}