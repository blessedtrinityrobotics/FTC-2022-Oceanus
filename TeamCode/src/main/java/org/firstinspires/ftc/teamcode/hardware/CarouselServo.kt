package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.CAROUSEL_SERVO_NAME
import org.firstinspires.ftc.teamcode.CAROUSEL_SERVO_SPEED

class CarouselServo(hardwareMap: HardwareMap, val telemetry: Telemetry) {
    val servo = hardwareMap.crservo.get(CAROUSEL_SERVO_NAME)

    fun spin() {
        servo.power = CAROUSEL_SERVO_SPEED
        telemetry.addLine("Carousel Spinning...")
    }

    fun spinPower(power: Double) {
        servo.power = CAROUSEL_SERVO_SPEED * power
    }

    fun stop() {
        servo.power = 0.0
    }

    fun reverse() {
        servo.power = -CAROUSEL_SERVO_SPEED
    }
}