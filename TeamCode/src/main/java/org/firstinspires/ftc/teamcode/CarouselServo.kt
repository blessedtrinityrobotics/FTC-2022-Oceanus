package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry

class CarouselServo(hardwareMap: HardwareMap, val telemetry: Telemetry) {
    val servo = hardwareMap.crservo.get(CAROUSEL_SERVO_NAME)

    fun spin() {
        servo.power = CAROUSEL_SERVO_SPEED
        telemetry.addLine("Carousel Spinning...")
    }

    fun stop() {
        servo.power = 0.0
    }

    fun reverse() {
        servo.power = -CAROUSEL_SERVO_SPEED
    }
}