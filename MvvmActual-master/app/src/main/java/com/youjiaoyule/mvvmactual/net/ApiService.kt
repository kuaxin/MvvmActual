package com.youjiaoyule.mvvmactual.net

import com.youjiaoyule.mvvmactual.module.home.HomeBean
import com.youjiaoyule.mvvmactual.net.response.BaseResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 *  @author RenGX on 2020/6/10
 *
 */
interface ApiService {

    @GET
    suspend fun loadHomeBean(@Url url:String, @QueryMap params:HashMap<String, Any>):BaseResponse<HomeBean>

}