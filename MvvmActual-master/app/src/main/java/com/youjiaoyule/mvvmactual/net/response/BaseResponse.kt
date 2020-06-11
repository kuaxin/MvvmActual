package com.youjiaoyule.mvvmactual.net.response

/**
 *  @author RenGX on 2020/6/10
 *
 */
class BaseResponse<T>(var data:T,var error:Int = -1,var message:String = "")