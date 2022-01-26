package org.firstinspires.ftc.teamcode.hardware


import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.*


class CarouselMotor(hardwareMap: HardwareMap, val telemetry: Telemetry) {

    val motor = Motor(hardwareMap, CAROUSEL_MOTOR_NAME, Motor.GoBILDA.RPM_1620)

    private val rotationCycle = 700 // used to hold the distance need to get the duck off of the Carousel

    init {
        motor.setRunMode(Motor.RunMode.RawPower)
        // So it stays in place when no power is applied
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE)

        motor.setDistancePerPulse(DISTANCE_PER_PULSE)
        motor.resetEncoder()
    }

    // used for moving duck from Carousel

    //used for when you are on the red alliance
    fun moveRed(){
        while (motor.currentPosition > -rotationCycle){
            if (motor.currentPosition > -rotationCycle/2) {
                motor.set(-0.25)
                telemetry.addData("together we made it", true)
            }
            else {
                motor.set(-0.21)
            }
        }
        motor.stopMotor()
        motor.resetEncoder()
    }

    // used for when you are on the blue alliance
    fun moveBlue(){
        while (motor.currentPosition < rotationCycle){
            if (motor.currentPosition < rotationCycle/2) {
                motor.set(0.25)
                telemetry.addData("together we made it", true)
            }
            else {
                motor.set(0.21)
            }
        }
        motor.stopMotor()
        motor.resetEncoder()
    }

}