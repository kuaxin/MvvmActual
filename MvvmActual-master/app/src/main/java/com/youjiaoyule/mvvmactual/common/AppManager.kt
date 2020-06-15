package com.youjiaoyule.mvvmactual.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

/**
 *  @author RenGX on 2020/6/10
 *
 */
object AppManager {
    private val activityStack: Stack<Activity> = Stack()

    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    fun removeActivity(activity: Activity){
        activityStack.remove(activity)
    }

    private fun finishAllActivity(){
        for(activity in activityStack){
            activity.finish()
        }

        activityStack.clear()
    }

    //退出App
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        exitProcess(0)
    }

    /**
     * 当前Activity是否为堆栈最后一个Activity
     *
     * @return
     */
    fun isLastActivity(activity: Activity?): Boolean {
        return activityStack.size == 1 && activityStack.indexOf(activity) == 0
    }


}