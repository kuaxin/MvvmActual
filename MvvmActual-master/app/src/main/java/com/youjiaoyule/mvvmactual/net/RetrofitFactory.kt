package com.youjiaoyule.mvvmactual.net

import com.youjiaoyule.mvvmactual.common.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.StringBuilder

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @date: 2020/02/24
 * Time: 16:56
 */

class RetrofitFactory private constructor() {
    private val retrofit : Retrofit

    fun <T> create(clazz: Class<T>) : T {
        return retrofit.create(clazz)
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(initOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        val instance by lazy {
            RetrofitFactory()
        }
    }

    //初始化Okhttp
    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpManager.getInstance().providerAutoCacheOkHttpClient()
    }

//    private fun initCookieIntercept(): Interceptor {
//        return Interceptor { chain ->
//            val request = chain.request()
//            val response = chain.proceed(request)
//            val requestUrl = request.url().toString()
//            val domain = request.url().host()
//            response
//        }
//    }

//    private fun initLoginIntercept(): Interceptor {
//        return Interceptor { chain ->
//            val request = chain.request()
//            val builder = request.newBuilder()
//            val domain = request.url().host()
//            val response = chain.proceed(builder.build())
//            response
//        }
//    }

    private fun initCommonInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "UTF-8")
                .build()
            chain.proceed(request)
        }
    }

//    private fun parseCookie(it: List<String>): String {
//        if(it.isEmpty()){
//            return ""
//        }
//
//        val stringBuilder = StringBuilder()
//
//        it.forEach { cookie ->
//            stringBuilder.append(cookie).append(";")
//        }
//
//        if(stringBuilder.isEmpty()){
//            return ""
//        }
//        //末尾的";"去掉
//        return stringBuilder.deleteCharAt(stringBuilder.length - 1).toString()
//    }
//
//    private fun saveCookie(domain: String?, parseCookie: String) {
//        domain?.let {
//            var resutl :String by SPreference("cookie",parseCookie)
//            resutl = parseCookie
//        }
//    }
}