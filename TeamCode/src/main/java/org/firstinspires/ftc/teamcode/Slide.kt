package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.PI

class Slide(hardwareMap: HardwareMap, val telemetry: Telemetry) {
    val motor = Motor(hardwareMap, SLIDE_MOTOR_NAME, Motor.GoBILDA.RPM_223)

    val MAX_TICKS = 1200
    val MIN_TICKS = 1

    init {
        motor.setRunMode(Motor.RunMode.RawPower)
        // So it stays in place when no power is applied
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE)

        motor.resetEncoder()
    }

    fun move(power: Double) {
        // checks if we are within range
        if (motor.currentPosition in MIN_TICKS until MAX_TICKS)
            motor.set(power)
        else
            motor.set(0.0)
    }

    fun test(power: Double) {
        motor.set(power)
    }

    fun logPosition() {
        telemetry.addData("Slide Position", motor.currentPosition)
    }

}