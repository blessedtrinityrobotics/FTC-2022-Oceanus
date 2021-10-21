package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
@Autonomous(name = "Carousel Park Auto")
class CarouselParkAuto : LinearOpMode() {
    lateinit var drivetrain: Drivetrain

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, Motor.RunMode.PositionControl)

        waitForStart()

    }
}