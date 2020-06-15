package com.youjiaoyule.mvvmactual

import com.hjq.toast.ToastUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.youjiaoyule.mvvmactual.base.BaseApplication

/**
 *  @author RenGX on 2020/6/10
 *
 */
class MyApp: BaseApplication() {

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            return@setDefaultRefreshHeaderCreator ClassicsHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            return@setDefaultRefreshFooterCreator ClassicsFooter(context)
        }
    }

    override fun onCreate() {
        super.onCreate()

        //初始化Toast
        ToastUtils.init(this)
    }
}