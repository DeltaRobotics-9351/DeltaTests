/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin

import com.deltarobotics9351.java.AsyncUtil.asyncExecute
import com.deltarobotics9351.java.DeltaAppUtil
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.RobotCoreLynxUsbDevice
import com.qualcomm.robotcore.util.RobotLog
import org.firstinspires.ftc.robotcore.internal.system.AppUtil
import org.firstinspires.ftc.robotcore.internal.ui.UILocation
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

    fun restartAppCausedByError(hardwareMap: HardwareMap, globalErrorMessage: String, toast: String){
        val lastDitchEffortFailsafeDone = CountDownLatch(1);

        asyncExecute(Runnable {
            for(module in hardwareMap.getAll(RobotCoreLynxUsbDevice::class.java)){
                module.lockNetworkLockAcquisitions();
                module.failSafe();
            }
            lastDitchEffortFailsafeDone.countDown();
        });

        try {
            if (lastDitchEffortFailsafeDone.await(250, TimeUnit.MILLISECONDS)) { //wait for failSafe command to be sent, with a timeout of 250 ms
                RobotLog.e("DeltaUtils - Successfully sent failsafe commands to all Lynx modules before the app restarts")
            } else {
                RobotLog.e("DeltaUtils - Timed out to send failsafe commands to all Lynx modules before the app restarts")
            }
        } catch (e: InterruptedException) {
        } //ignore the exception

        RobotLog.setGlobalErrorMsg(globalErrorMessage) //show error messages

        RobotLog.e(globalErrorMessage)
        AppUtil.getInstance().showToast(UILocation.BOTH, toast)

        threadStackTracesDump() //show all stacktraces, for debugging purposes.

        try {
            Thread.sleep(3000) //wait a bit for the messages to be seen
        } catch (e: InterruptedException) {
        } //ignore the exception again


        AppUtil.getInstance().restartApp(-1) //use the FTC SDK's app util class to restart the app


    }

    fun threadStackTracesDump() {
        RobotLog.e("DeltaUtils - Thread dump start")
        for ((key, value) in Thread.getAllStackTraces()) {
            RobotLog.logStackTrace(key, value)
        }
        RobotLog.e("DeltaUtils - Thread dump end")
    }



