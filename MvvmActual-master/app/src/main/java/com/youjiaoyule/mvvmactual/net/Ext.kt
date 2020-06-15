package com.youjiaoyule.mvvmactual.net

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wjx.android.wanandroidmvvm.common.state.State
import com.youjiaoyule.mvvmactual.common.StateType
import com.youjiaoyule.mvvmactual.base.BaseRepository
import com.youjiaoyule.mvvmactual.base.BaseViewModel
import com.youjiaoyule.mvvmactual.common.Constant
import com.youjiaoyule.mvvmactual.net.response.BaseResponse
import kotlinx.coroutines.launch

/**
 * Created with Android Studio.
 * Description:数据解析扩展函数
 * @author: Wangjianxian
 * @CreateDate: 2020/4/19 17:35
 */

fun <T> BaseResponse<T>.dataConvert(
    loadState: MutableLiveData<State>
): T {
    when (code) {
        Constant.SUCCESS -> {
            if (data is List<*>) {
                if ((data as List<*>).isEmpty()) {
                    loadState.postValue(State(StateType.EMPTY))
                }
            }
            loadState.postValue(State(StateType.SUCCESS))
            return data
        }
        Constant.NOT_LOGIN -> {
            return data
        }
        else -> {
            loadState.postValue(State(StateType.ERROR, message = message))
            return data
        }
    }
}


fun <T : BaseRepository> BaseViewModel<T>.initiateRequest(
    block: suspend () -> Unit,
    loadState: MutableLiveData<State>
) {
    viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
        }.onFailure {
            NetExceptionHandle.handleException(it, loadState)
        }
    }
}
