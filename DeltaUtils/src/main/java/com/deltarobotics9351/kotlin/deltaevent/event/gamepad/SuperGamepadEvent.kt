/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.event.gamepad

import com.deltarobotics9351.kotlin.deltaevent.gamepad.GamepadDataPacket
import com.deltarobotics9351.kotlin.deltaevent.gamepad.button.Buttons

open class SuperGamepadEvent : GamepadEvent() {

    override fun performEvent(gdp: GamepadDataPacket) {
        if (gdp.buttonsBeingPressed.isNotEmpty()) {
            buttonsBeingPressed(Buttons(gdp.buttonsBeingPressed, BUTTONS_BEING_PRESSED))
        }
        if (!gdp.buttonsPressed.isEmpty()) {
            buttonsPressed(Buttons(gdp.buttonsPressed, BUTTONS_PRESSED))
        }
        if (!gdp.buttonsReleased.isEmpty()) {
            buttonsReleased(Buttons(gdp.buttonsReleased, BUTTONS_RELEASED))
        }
    }

    /**
     * Method to be executed ONCE when at least one button is pressed
     * @param buttons the pressed buttons
     */
    fun buttonsPressed(buttons: Buttons) {}

    /**
     * Method to be executed ONCE when at least one button is released
     * @param buttons the released buttons
     */
    fun buttonsReleased(buttons: Buttons) {}


    /**
     * Method to be executed REPETITIVELY when at least one button is pressed until it is released
     * @param buttons the being pressed buttons
     */
    fun buttonsBeingPressed(buttons: Buttons) {}


}