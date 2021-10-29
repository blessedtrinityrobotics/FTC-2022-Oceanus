package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.hardware.motors.MotorGroup
import com.arcrobotics.ftclib.util.MathUtils
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.*

/**
 * Interfaces with the motors directly so you don't have to!
 * Never access the motors, just add functions here to add functionality
 */
const val WHEEL_DIAMETER = 100
const val TICKS_PER_REVOLUTION = 537.7
const val DISTANCE_PER_PULSE = WHEEL_DIAMETER * PI / TICKS_PER_REVOLUTION


class Drivetrain (hardwareMap: HardwareMap, val telemetry: Telemetry) {
    val frontLeft = Motor(hardwareMap, FL_MOTOR_NAME, Motor.GoBILDA.RPM_312)
    val backLeft = Motor(hardwareMap, BL_MOTOR_NAME, Motor.GoBILDA.RPM_312)
    val frontRight = Motor(hardwareMap, FR_MOTOR_NAME, Motor.GoBILDA.RPM_312)
    val backRight = Motor(hardwareMap, BR_MOTOR_NAME, Motor.GoBILDA.RPM_312)

    val leftMotors = MotorGroup(frontLeft, backLeft)
    val rightMotors = MotorGroup(frontRight, backRight)

    /**
     * TODO: Migrate to the actual Mecanum drive class of FTC Lib
     */
    init {

        rightMotors.inverted = true

        leftMotors.setDistancePerPulse(DISTANCE_PER_PULSE)
        rightMotors.setDistancePerPulse(DISTANCE_PER_PULSE)
    }

    fun setRunMode(mode: Motor.RunMode) {
        leftMotors.setRunMode(mode)
        rightMotors.setRunMode(mode)
    }

    /**
     * Drives motors with mecanum wheels
     * Changes power directly
     * x and y should be from the left joystick, while yaw is from the right
     * x and y are the linear motion, yaw is rotation
     */
    fun mecanumDrive(x: Double, y: Double, yaw: Double) {
        val r = hypot(x, y) * ConfigConstants.motorMult
        val robotAngle = atan2(y, x) - Math.PI / 4
        // Really cool trig to get this working
        val v1 = MathUtils.clamp(r * cos(robotAngle) + yaw, -1.0, 1.0)
        val v2 = MathUtils.clamp(r * sin(robotAngle) - yaw, -1.0, 1.0)
        val v3 = MathUtils.clamp(r * sin(robotAngle) + yaw, -1.0, 1.0)
        val v4 = MathUtils.clamp(r * cos(robotAngle) - yaw, -1.0, 1.0)

        frontLeft.set(v1)
        frontRight.set(v2)
        backLeft.set(v3)
        backRight.set(v4)

        telemetry.addData("FL Power", v1)
        telemetry.addData("FR Power", v2)
        telemetry.addData("BL Power", v3)
        telemetry.addData("BR Power", v4)
    }

    fun stop() {
        leftMotors.stopMotor()
        rightMotors.stopMotor()
    }

    /**
     * Uses built in motor encoders to go to a position in mm
     * @param power Raw power to give the motors as it goes
     * @param dist distance to travel in mm
     */
    fun driveByDistance(power: Double, dist: Double) {
        leftMotors.setTargetDistance(dist)
        rightMotors.setTargetDistance(dist)

        while (!leftMotors.atTargetPosition() && !rightMotors.atTargetPosition()) {
            leftMotors.set(power)
            rightMotors.set(power)
        }

        leftMotors.stopMotor()
        rightMotors.stopMotor()
    }


}