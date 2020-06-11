package com.youjiaoyule.mvvmactual.net

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonParseException
import com.wjx.android.wanandroidmvvm.common.state.State
import com.youjiaoyule.mvvmactual.common.StateType
import org.apache.http.conn.ConnectTimeoutException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *  @author RenGX on 2020/6/11
 *
 */
object NetExceptionHandle {
    var msg:String = ""
    fun handleException(e: Throwable, loadState: MutableLiveData<State>){
        e.let {
            msg = when(it){
                is HttpException -> {
                    "网络异常"
                }
                is ConnectException -> {
                    "连接失败"
                }
                is ConnectTimeoutException -> {
                    "连接超时"
                }
                is SocketTimeoutException ->{
                    "连接超时"
                }
                //或者服务器异常
                is UnknownHostException -> {
                    "网络异常"
                }
                is JsonParseException -> {
                    "数据解析异常"
                }
                is ResponseException ->{
                    "请求返回结果异常"
                }
                else ->{
                    "未知错误 ---》 $it"
                }
            }
            loadState.postValue(State(StateType.NETWORK_ERROR,msg))
        }

    }

}