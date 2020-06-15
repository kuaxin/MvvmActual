package com.youjiaoyule.mvvmactual.base

import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hjq.toast.ToastUtils
import com.kingja.loadsir.callback.SuccessCallback
import com.wjx.android.wanandroidmvvm.common.state.State
import com.youjiaoyule.mvvmactual.common.StateType
import com.youjiaoyule.mvvmactual.common.CommonUtil
import com.youjiaoyule.mvvmactual.common.callback.EmptyCallBack
import com.youjiaoyule.mvvmactual.common.callback.ErrorCallBack
import com.youjiaoyule.mvvmactual.common.callback.LoadingCallBack
import java.util.logging.Logger

/**
 *  @author RenGX on 2020/6/10
 *
 */
abstract class BaseLifeCycleActivitty<VM :BaseViewModel<*>>: BaseActivity() {
    protected lateinit var mViewModel: VM

    override fun initView() {
        //展示loading
        showLoading()
        //初始化ViewModel
        mViewModel = ViewModelProvider(this).get(CommonUtil.getClass(this))

        mViewModel.loadState.observe(this,observer)

        //初始化View的Observer
        initDataObserver()
    }

    //开始请求数据
    abstract fun initDataObserver()


    open fun showLoading() {
        loadService.showCallback(LoadingCallBack::class.java)
    }

    open fun showSuccess() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    open fun showError(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            Log.e("TEST",msg)
            ToastUtils.show(msg)
        }
        loadService.showCallback(ErrorCallBack::class.java)
    }


    //暂时没有发现什么功能
//    open fun showTip(msg: String) {
//        if (!TextUtils.isEmpty(msg)) {
//            MaterialDialog(this).show {
//                title(R.string.title)
//                message(text = msg)
//                cornerRadius(8.0f)
//                negativeButton(R.string.done)
//            }
//            false
//        }
//        loadService.showCallback(SuccessCallback::class.java)
//    }

    open fun showEmpty() {
        loadService.showCallback(EmptyCallBack::class.java)
    }

    /**
     * 分发应用状态
     */
    private val observer by lazy {
        Observer<State> {
            it?.let {
                when (it.code) {
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showError(it.message)
                    StateType.NETWORK_ERROR -> showError(it.message)
                    StateType.TIP -> showError(it.message)
                    StateType.EMPTY -> showEmpty()
                }
            }
        }
    }

    //子类可以选择重写
    override fun onHandlerReceive(msg: Message) {

    }

}