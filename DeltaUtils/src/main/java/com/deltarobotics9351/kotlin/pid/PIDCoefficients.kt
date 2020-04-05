/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.pid

class PIDCoefficients(P: Double, I: Double, D: Double) {

    /**
     * PID Coefficients
     */
    var kP = 0.0
    var kI = 0.0
    var kD = 0.0

    /**
     * Constructor for PIDCoefficients class
     * @param kP the Proportional coefficient
     * @param kI the Integral coefficient
     * @param kD the Derivative coefficient
     */
    init {
        this.kP = P
        this.kI = I
        this.kD = D
    }

}