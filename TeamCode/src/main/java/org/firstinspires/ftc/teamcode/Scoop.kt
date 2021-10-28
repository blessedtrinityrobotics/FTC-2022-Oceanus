package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry

/**
 * The scooping servo connected to the plow/bucket thing at the front of the robot
 */

class Scoop(hardwareMap: HardwareMap, val telemetry: Telemetry) {
    val servo = hardwareMap.servo.get(SCOOP_SERVO_NAME)

    fun reset() {
        servo.position = ConfigConstants.neutralAngle
    }

    fun dump() {
        servo.position = ConfigConstants.dumpAngle
    }

    fun pickup() {
        servo.position = ConfigConstants.pickupAngle
    }

}