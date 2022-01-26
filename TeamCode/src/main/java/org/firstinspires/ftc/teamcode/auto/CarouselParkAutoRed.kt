package org.firstinspires.ftc.teamcode.auto

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.hardware.CarouselServo
import org.firstinspires.ftc.teamcode.hardware.*

@Disabled
@Autonomous(name = "Carousel Park Auto")
class CarouselParkAutoRed : LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var carousel: CarouselServo

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, this, null)
        carousel = CarouselServo(hardwareMap, telemetry)

        drivetrain.setRunMode(Motor.RunMode.PositionControl)

        waitForStart()

        sleep(500)
//        drivetrain.forwardByDistance(0.1, 54.0)

        carousel.spin()
        sleep(5000)
        carousel.stop()

        drivetrain.strafeByDistance(1.0, 36.0, this)
    }
}