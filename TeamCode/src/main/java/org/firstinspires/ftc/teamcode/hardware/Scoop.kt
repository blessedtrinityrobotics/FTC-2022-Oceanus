package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.ConfigConstants
import org.firstinspires.ftc.teamcode.SCOOP_SERVO_NAME

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