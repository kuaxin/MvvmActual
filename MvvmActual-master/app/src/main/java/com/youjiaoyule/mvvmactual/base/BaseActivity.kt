package com.youjiaoyule.mvvmactual.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 *  @author RenGX on 2020/6/10
 *
 */
abstract class BaseActivity: AppCompatActivity() {


    val loadService: LoadService<*> by lazy {
        LoadSir.getDefault().register(this) {
            reLoad()
        }
    }

    open fun reLoad() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initDate()
    }

    //获取到页面的布局
    abstract fun getLayoutId(): Int

    //初始化控件
    abstract fun initView()

    //加载数据
    abstract fun initDate()

}