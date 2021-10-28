package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.drivebase.MecanumDrive
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.util.MathUtils
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.*

/**
 * Interfaces with the motors directly so you don't have to!
 * Never access the motors, just add functions here to add functionality
 */
class Drivetrain (hardwareMap: HardwareMap, val telemetry: Telemetry, mode: Motor.RunMode) {
    val frontLeft = Motor(hardwareMap, FL_MOTOR_NAME, Motor.GoBILDA.RPM_312)
    val backLeft = Motor(hardwareMap, BL_MOTOR_NAME, Motor.GoBILDA.RPM_312)
    val frontRight = Motor(hardwareMap, FR_MOTOR_NAME, Motor.GoBILDA.RPM_312)
    val backRight = Motor(hardwareMap, BR_MOTOR_NAME, Motor.GoBILDA.RPM_312)

    val DISTANCE_PER_PULSE = 100 * PI / 537.7

    /**
     * TODO: Migrate to the actual Mecanum drive class of FTC Lib
     */
    init {
        frontLeft.setRunMode(mode)
        frontRight.setRunMode(mode)
        backLeft.setRunMode(mode)
        backRight.setRunMode(mode)

        frontLeft.motor.direction = DcMotorSimple.Direction.FORWARD
        backLeft.motor.direction = DcMotorSimple.Direction.FORWARD

        frontRight.motor.direction = DcMotorSimple.Direction.REVERSE
        backRight.motor.direction = DcMotorSimple.Direction.REVERSE

        frontLeft.setDistancePerPulse(DISTANCE_PER_PULSE)
        frontRight.setDistancePerPulse(DISTANCE_PER_PULSE)
        backLeft.setDistancePerPulse(DISTANCE_PER_PULSE)
        backRight.setDistancePerPulse(DISTANCE_PER_PULSE)

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

        // Instead of using getters/setters, just let kotlin do it for you!
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
        frontLeft.set(0.0)
        frontRight.set(0.0)
        backLeft.set(0.0)
        backRight.set(0.0)
    }


    fun encoderDrive(dist: Double) {
        frontLeft.setTargetDistance(dist)
        frontLeft.setTargetDistance(dist)
        frontLeft.setTargetDistance(dist)
        frontLeft.setTargetDistance(dist)
    }


}