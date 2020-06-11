package com.youjiaoyule.mvvmactual.base

import com.youjiaoyule.mvvmactual.net.ApiService
import com.youjiaoyule.mvvmactual.net.RetrofitFactory

/**
 *  @author RenGX on 2020/6/10
 *
 */
abstract class ApiRepository : BaseRepository() {
    protected val apiService: ApiService by lazy {
        RetrofitFactory.instance.create(ApiService::class.java)
    }
}