/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.timer

import com.deltarobotics9351.kotlin.asyncExecute
import com.qualcomm.robotcore.hardware.HardwareMap

class SuperAsyncTimer(hardwareMap: HardwareMap) : SuperSyncTimer(hardwareMap) {

    /**
     * Destroy this SuperAsyncTimer asynchronously.
     * IT NEEDS TO BE CALLED AT THE END OF YOUR OPMODE
     */
    fun asyncDestroy() {
        asyncExecute { destroy() }
    }

}