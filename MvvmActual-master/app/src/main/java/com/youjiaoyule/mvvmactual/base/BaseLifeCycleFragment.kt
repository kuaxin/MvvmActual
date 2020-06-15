package com.youjiaoyule.mvvmactual.base

import android.os.Message
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hjq.toast.ToastUtils
import com.wjx.android.wanandroidmvvm.common.state.State
import com.youjiaoyule.mvvmactual.common.CommonUtil
import com.youjiaoyule.mvvmactual.common.StateType
import com.youjiaoyule.mvvmactual.common.callback.EmptyCallBack
import com.youjiaoyule.mvvmactual.common.callback.ErrorCallBack
import com.youjiaoyule.mvvmactual.common.callback.LoadingCallBack

/**
 *  @author RenGX on 2020/6/11
 *
 */
abstract class BaseLifeCycleFragment<VM: BaseViewModel<*>>: BaseFragment() {

    protected lateinit var mViewModel: VM

    override fun initView() {
        showLoading()

        mViewModel = ViewModelProvider(this).get(CommonUtil.getClass(this))
        mViewModel.loadState.observe(this,observer)

        initDataObserver()
    }

    abstract fun initDataObserver()

    private fun showLoading() {
        loadService.showCallback(LoadingCallBack::class.java)
    }

    private fun showSuccess(){
        loadService.showSuccess()
    }

    private fun showError(errorMsg: String){
        if(errorMsg.isNotEmpty()){
            ToastUtils.show(errorMsg)
        }
        loadService.showCallback(ErrorCallBack::class.java)
    }

    private fun showEmpty(){
        loadService.showCallback(EmptyCallBack::class.java)
    }

    //消息的统一处理
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

    override fun reLoad() {
        super.reLoad()
        lazyLoad()
    }

    //子类可以选择重写
    override fun onHandlerReceive(msg: Message) {

    }

}