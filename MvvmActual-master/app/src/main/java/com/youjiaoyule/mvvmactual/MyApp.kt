package com.youjiaoyule.mvvmactual

import com.hjq.toast.ToastUtils
import com.youjiaoyule.mvvmactual.base.BaseApplication

/**
 *  @author RenGX on 2020/6/10
 *
 */
class MyApp: BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        //初始化Toast
        ToastUtils.init(this)
    }

}