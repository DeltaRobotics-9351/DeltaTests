/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.event.gamepad

import com.deltarobotics9351.kotlin.deltaevent.gamepad.GamepadDataPacket
import com.deltarobotics9351.kotlin.deltaevent.gamepad.button.Button

class IntensiveSuperGamepadEvent : GamepadEvent(){

    private var gdp: GamepadDataPacket = GamepadDataPacket()

    override fun performEvent(gdp: GamepadDataPacket) {
        this.gdp = gdp
        for ((key, value) in gdp.buttonsBeingPressed) {
            buttonBeingPressed(key, value)
        }
        for ((key, value) in gdp.buttonsPressed) {
            buttonPressed(key, value)
        }
        for ((key, value) in gdp.buttonsReleased) {
            buttonReleased(key, value)
        }
    }

    /**
     * Method to be executed ONCE when a button is pressed
     * @param button the pressed button
     */
    open fun buttonPressed(button: Button, ticks: Int) {}

    /**
     * Method to be executed ONCE when a button is released
     * @param button the released button
     */
    open fun buttonReleased(button: Button, ticks: Int) {}


    /**
     * Method to be executed REPETITIVELY when a button is pressed until it is released
     * @param button the being pressed button
     */
    open fun buttonBeingPressed(button: Button, ticks: Int) {}


}