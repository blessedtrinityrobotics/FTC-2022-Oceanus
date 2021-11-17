package org.firstinspires.ftc.teamcode.hardware

import com.arcrobotics.ftclib.hardware.RevIMU
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.hardware.motors.MotorGroup
import com.arcrobotics.ftclib.util.MathUtils
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.*
import kotlin.math.*

/**
 * Interfaces with the motors directly so you don't have to!
 * Never access the motors, just add functions here to add functionality
 */
const val WHEEL_DIAMETER = 3.95 // Inches6

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

        frontRight.inverted = true
        backRight.inverted = true
    }

    fun setRunMode(mode: Motor.RunMode) {
        frontLeft.setRunMode(mode)
        frontRight.setRunMode(mode)
        backLeft.setRunMode(mode)
        backRight.setRunMode(mode)


        backLeft.setDistancePerPulse(DISTANCE_PER_PULSE)
        backRight.setDistancePerPulse(DISTANCE_PER_PULSE)
        frontLeft.setDistancePerPulse(DISTANCE_PER_PULSE)
        frontRight.setDistancePerPulse(DISTANCE_PER_PULSE)
    }

    /**
     * Drives motors with mecanum wheels
     * Changes power directly
     * x and y should be from the left joystick, while yaw is from the right
     * x and y are the linear motion, yaw is rotation
     */
    fun mecanumDrive(x: Double, y: Double, yaw: Double, mult: Double) {
        telemetry.addData("Overdrive", mult)
        val r = hypot(x, y) * ConfigConstants.motorMult * mult
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
     * Uses built in motor encoders to go to a position in inches
     * @param power Raw power to give the motors as it goes (HAS TO BE POSITIVE)
     * @param dist distance to travel in inches
     */
    fun forwardByDistance(power: Double, dist: Double, op: LinearOpMode) {
        frontLeft.resetEncoder()
        backLeft.resetEncoder()
        frontRight.resetEncoder()
        backLeft.resetEncoder()

        frontLeft.setTargetDistance(dist)
        backLeft.setTargetDistance(dist)
        frontRight.setTargetDistance(dist)
        backRight.setTargetDistance(dist)

        while (op.opModeIsActive() && !frontLeft.atTargetPosition() && !backLeft.atTargetPosition()
            && !frontRight.atTargetPosition() && !backRight.atTargetPosition()) {
            frontLeft.set(power)
            backLeft.set(power)
            frontRight.set(power)
            backRight.set(power)
            telemetry.addData("Left Position", backLeft.currentPosition)
            telemetry.addData("Right Position", backRight.currentPosition)
            telemetry.update()
        }

        leftMotors.stopMotor()
        rightMotors.stopMotor()
    }

    fun strafeByDistance(power: Double, dist: Double, op: LinearOpMode) {
        frontLeft.resetEncoder()
        backLeft.resetEncoder()
        frontRight.resetEncoder()
        backLeft.resetEncoder()

        frontLeft.setTargetDistance(dist)
        backLeft.setTargetDistance(-dist)
        frontRight.setTargetDistance(-dist)
        backRight.setTargetDistance(dist)

        while (op.opModeIsActive() && !frontLeft.atTargetPosition() && !backLeft.atTargetPosition()
            && !frontRight.atTargetPosition() && !backRight.atTargetPosition()) {
            frontLeft.set(power)
            backLeft.set(power)
            frontRight.set(power)
            backRight.set(power)
            telemetry.addData("Left Position", backLeft.currentPosition)
            telemetry.addData("Right Position", backRight.currentPosition)
            telemetry.update()
        }

        leftMotors.stopMotor()
        rightMotors.stopMotor()
    }

    fun turnByDegrees(power: Double, degrees: Double) {

    }

}