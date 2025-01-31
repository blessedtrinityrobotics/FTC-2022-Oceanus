package org.firstinspires.ftc.teamcode.test;

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.ConfigConstants
import org.firstinspires.ftc.teamcode.hardware.*

@Autonomous(name = "Use me", group = "Test")
class useMe : LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    private lateinit var scoop: Scoop

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, this, null)
        scoop = Scoop(hardwareMap, telemetry)
//        drivetrain.setRunMode(Motor.RunMode.PositionControl)
//        drivetrain.backLeft.resetEncoder()
//        drivetrain.backRight.resetEncoder()
//        drivetrain.frontLeft.resetEncoder()
//        drivetrain.frontRight.resetEncoder()

        waitForStart()

        scoop.servo.position = ConfigConstants.pickupAngle
        sleep(1000)
        drivetrain.setRunMode(Motor.RunMode.PositionControl)
        drivetrain.forwardByDistance(0.5, 60.0)

        while (opModeIsActive()) {

            telemetry.addData("Distance", drivetrain.backLeft.currentPosition)
            telemetry.addData("isRunning", opModeIsActive())
            telemetry.update()
        }

    }
}
