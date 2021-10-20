package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.util.MathUtils
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

/**
 * Interfaces with the motors directly so you don't have to!
 * Never access the motors, just add functions here to add functionality
 */
class Drivetrain (hardwareMap: HardwareMap, val telemetry: Telemetry) {
    val frontLeft = hardwareMap.dcMotor.get(FL_MOTOR_NAME)
    val backLeft = hardwareMap.dcMotor.get(BL_MOTOR_NAME)
    val frontRight = hardwareMap.dcMotor.get(FR_MOTOR_NAME)
    val backRight = hardwareMap.dcMotor.get(BR_MOTOR_NAME)

    init {
        frontLeft.direction = DcMotorSimple.Direction.FORWARD
        backLeft.direction = DcMotorSimple.Direction.FORWARD

        frontRight.direction = DcMotorSimple.Direction.REVERSE
        backRight.direction = DcMotorSimple.Direction.REVERSE
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
        frontLeft.power = v1
        frontRight.power = v2
        backLeft.power = v3
        backRight.power = v4

        telemetry.addData("FL Power", v1)
        telemetry.addData("FR Power", v2)
        telemetry.addData("BL Power", v3)
        telemetry.addData("BR Power", v4)
    }
}