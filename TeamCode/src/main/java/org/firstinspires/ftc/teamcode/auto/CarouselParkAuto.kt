package org.firstinspires.ftc.teamcode.auto

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.hardware.CarouselServo
import org.firstinspires.ftc.teamcode.hardware.Drivetrain

@Autonomous(name = "Carousel Park Auto")
class CarouselParkAuto : LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var carousel: CarouselServo

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, null, null)
        carousel = CarouselServo(hardwareMap, telemetry)

        drivetrain.setRunMode(Motor.RunMode.PositionControl)

        waitForStart()

        sleep(500)
        drivetrain.forwardByDistance(0.1, 42.0)

        carousel.spin()
        sleep(5000)
        carousel.stop()
    }
}