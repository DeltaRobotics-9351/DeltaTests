/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.gamepad

import com.deltarobotics9351.kotlin.deltaevent.gamepad.button.Button
import com.qualcomm.robotcore.hardware.Gamepad
import java.util.*

class GamepadDataPacket {

    var buttonsBeingPressed = HashMap<Button, Int>()
    var buttonsReleased = HashMap<Button, Int>()
    var buttonsPressed = HashMap<Button, Int>()

    var left_stick_x = 0.0
    var left_stick_y = 0.0

    var right_stick_x = 0.0
    var right_stick_y = 0.0

    var left_trigger = 0.0
    var right_trigger = 0.0

    var gamepad: Gamepad = Gamepad()


}