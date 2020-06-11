package com.youjiaoyule.mvvmactual.base

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import com.youjiaoyule.mvvmactual.common.callback.EmptyCallBack
import com.youjiaoyule.mvvmactual.common.callback.ErrorCallBack
import com.youjiaoyule.mvvmactual.common.callback.LoadingCallBack
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

/**
 *  @author RenGX on 2020/6/10
 *
 */
abstract class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        //AutoSize 屏蔽DP  Sp   MM 为单位适配
        AutoSizeConfig.getInstance()
            .setBaseOnWidth(true)
            .unitsManager
            .setSupportDP(false)
            .setSupportSP(false).supportSubunits = Subunits.MM

        //初始化加载
        LoadSir.beginBuilder()
            .addCallback(ErrorCallBack())
            .addCallback(LoadingCallBack())
            .addCallback(EmptyCallBack())
            .commit()

    }

}