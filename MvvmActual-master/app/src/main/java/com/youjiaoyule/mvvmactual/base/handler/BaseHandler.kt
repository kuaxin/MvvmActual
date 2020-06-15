package com.youjiaoyule.mvvmactual.base.handler

import android.os.Handler
import android.os.Message
import android.util.Log
import com.youjiaoyule.mvvmactual.base.BaseActivity
import com.youjiaoyule.mvvmactual.base.BaseFragment
import java.lang.ref.WeakReference


/**
 *  @author RenGX on 2020/6/11
 *
 */
class BaseHandler private constructor(): Handler() {

    private var mActivity:WeakReference<BaseActivity>? = null
    private var mFragment:WeakReference<BaseFragment>? = null

    constructor(activity: BaseActivity):this(){
        mActivity = WeakReference(activity)
    }

    constructor(fragment: BaseFragment):this(){
        mFragment = WeakReference(fragment)
    }


    override fun handleMessage(msg: Message) {
        if(mActivity != null && mActivity!!.get() != null && !mActivity!!.get()!!.isFinishing){
            mActivity!!.get()!!.onHandlerReceive(msg)
        }else if(mFragment != null && mFragment!!.get() != null && !mFragment!!.get()!!.isRemoving){
            // Fragment处理
            mFragment!!.get()!!.onHandlerReceive(msg)
        }else{
            Log.d("BaseHandler","消息不发送")
        }

    }


}