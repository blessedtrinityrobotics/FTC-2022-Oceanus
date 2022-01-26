package org.firstinspires.ftc.teamcode.auto;

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.ConfigConstants
import org.firstinspires.ftc.teamcode.hardware.*

@Autonomous(name = "Red Parking", group = "Auto")
class RedParking : LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    private lateinit var scoop: Scoop

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, this, null)
        scoop = Scoop(hardwareMap, telemetry)

        waitForStart()

        scoop.servo.position = ConfigConstants.pickupAngle
        sleep(1000)
        drivetrain.setRunMode(Motor.RunMode.PositionControl)
        drivetrain.forwardByDistance(0.5, 60.0)
        sleep(1000)
        drivetrain.strafeByDistance(0.5,30.0 ,this)
        sleep(1000)
        drivetrain.forwardByDistance(0.5, 40.0)


        while (opModeIsActive()) {
            telemetry.addData("Distance", drivetrain.backLeft.currentPosition)
            telemetry.addData("isRunning", opModeIsActive())
            telemetry.update()
        }
    }
}
