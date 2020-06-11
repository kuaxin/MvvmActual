package com.youjiaoyule.mvvmactual.common.callback

import com.kingja.loadsir.callback.Callback
import com.youjiaoyule.mvvmactual.R

/**
 * Created with Android Studio.
 * Description:
 * @author: 王拣贤
 * @date: 2020/02/22
 * Time: 14:37
 */
class LoadingCallBack : Callback() {
    override fun onCreateView(): Int = R.layout.layout_loading
}