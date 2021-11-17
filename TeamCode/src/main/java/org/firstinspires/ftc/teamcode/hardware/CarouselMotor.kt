package org.firstinspires.ftc.teamcode.hardware

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.*
import kotlin.math.sign

class CarouselMotor(hardwareMap: HardwareMap, val telemetry: Telemetry) {

    val motor = Motor(hardwareMap, CAROUSEL_MOTOR_NAME, Motor.GoBILDA.RPM_1620)
    val MAX_TICKS = 10000

    init {
        motor.setRunMode(Motor.RunMode.RawPower)
        // So it stays in place when no power is applied
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE)

        motor.resetEncoder()
    }

    fun move() {
        val speed = 0.1
        while ( motor.currentPosition <= MAX_TICKS)
        {
            motor.set(speed)

        }
        motor.resetEncoder()
    }



}