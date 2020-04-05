/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.event.gamepad

import com.deltarobotics9351.kotlin.deltaevent.gamepad.GamepadDataPacket
import com.deltarobotics9351.kotlin.deltaevent.gamepad.button.Button
import com.deltarobotics9351.kotlin.deltaevent.gamepad.button.Buttons
import com.deltarobotics9351.kotlin.deltaevent.event.Event
import java.util.*

open class GamepadEvent : Event {

    var left_stick_x = 0.0
    var left_stick_y = 0.0

    var right_stick_x = 0.0
    var right_stick_y = 0.0

    var left_trigger = 0.0
    var right_trigger = 0.0

    val A = Button.A
    val B = Button.B
    val X = Button.X
    val Y = Button.Y

    val DPAD_UP = Button.DPAD_UP
    val DPAD_DOWN = Button.DPAD_DOWN
    val DPAD_LEFT = Button.DPAD_LEFT
    val DPAD_RIGHT = Button.DPAD_RIGHT

    val LEFT_BUMPER = Button.LEFT_BUMPER
    val RIGHT_BUMPER = Button.RIGHT_BUMPER

    val LEFT_TRIGGER = Button.LEFT_TRIGGER
    val RIGHT_TRIGGER = Button.RIGHT_TRIGGER

    val LEFT_STICK_BUTTON = Button.LEFT_STICK_BUTTON
    val RIGHT_STICK_BUTTON = Button.RIGHT_STICK_BUTTON

    val BUTTONS_BEING_PRESSED = Buttons.Type.BUTTONS_BEING_PRESSED
    val BUTTONS_PRESSED = Buttons.Type.BUTTONS_PRESSED
    val BUTTONS_RELEASED = Buttons.Type.BUTTONS_RELEASED

    override fun execute(arg1: Any, arg2: Any) {
        execute(arg1)
    }

    override fun execute(arg1: Any) {
        require(arg1 is GamepadDataPacket) { "Object is not a GamepadDataPacket" }
        val gdp = arg1
        left_stick_x = gdp.left_stick_x
        left_stick_y = gdp.left_stick_y
        right_stick_x = gdp.right_stick_x
        right_stick_y = gdp.right_stick_y
        left_trigger = gdp.left_trigger
        right_trigger = gdp.right_trigger
        loop(gdp)
        performEvent(gdp)
    }

    open fun performEvent(gdp: GamepadDataPacket) { }

    override fun execute(args: ArrayList<Any>) {
        for (obj in args) {
            execute(obj)
        }
    }

    override fun execute(args: HashMap<Any, Any>) {
        for ((key, value) in args) {
            execute(key, value)
        }
    }

    /**
     * Method to be executed REPETITIVELY every time the SuperGamepad updates.
     * @param gdp the last GamepadDataPacket
     */
    open fun loop(gdp: GamepadDataPacket) {}

}