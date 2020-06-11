package com.wjx.android.wanandroidmvvm.common.state

import androidx.annotation.StringRes
import com.youjiaoyule.mvvmactual.common.StateType

/**
 * Created with Android Studio.
 * Description: 状态类
 * @author: Wangjianxian
 * @date: 2020/02/22
 * Time: 15:28
 */
data class State(var code : StateType, var message : String = "", @StringRes var tip : Int = 0)