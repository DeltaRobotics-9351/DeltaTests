/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.gamepad.button

import com.deltarobotics9351.kotlin.deltaevent.gamepad.button.Button
import java.util.*

class Buttons {

    private var buttons = HashMap<Button, Int>()
    private var type: Type = Type.UNKNOWN

    enum class Type {
        BUTTONS_PRESSED, BUTTONS_RELEASED, BUTTONS_BEING_PRESSED, UNKNOWN
    }

    constructor (buttons: HashMap<Button, Int>, type: Type) {
        this.buttons = buttons
        this.type = type
    }

    fun contains(btt: Button): Boolean {
        return buttons.containsKey(btt)
    }

    fun ticks(btt: Button): Int {
        return if (buttons.containsKey(btt)) {
            buttons[btt]!!
        } else {
            0
        }
    }

    fun type(): Type {
        return type
    }


}