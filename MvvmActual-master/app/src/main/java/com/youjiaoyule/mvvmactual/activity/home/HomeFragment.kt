package com.youjiaoyule.mvvmactual.activity.home

import androidx.lifecycle.Observer
import com.youjiaoyule.mvvmactual.R
import com.youjiaoyule.mvvmactual.base.BaseLifeCycleFragment
import com.youjiaoyule.mvvmactual.module.home.HomeViewModel
import com.youjiaoyule.mvvmactual.weight.loadImg
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.properties.Delegates

/**
 *  @author RenGX on 2020/6/11
 *
 */
class HomeFragment private constructor(): BaseLifeCycleFragment<HomeViewModel>() {
    private var type: Int? = null

    constructor(type:Int):this(){
        this.type = type
    }

    override fun initDataObserver() {

    }

    override fun initView() {
        super.initView()


        mViewModel.homeData.observe(this, Observer {
            val dailySentenceList = it.dailySentenceList
            img_home.loadImg(
                when(type!!){
                    0 ->{
                        dailySentenceList[0].imgUrl
                    }
                    1 ->{
                        "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2573142242,3008102846&fm=26&gp=0.jpg"
                    }
                    2 ->{
                        "http://img0.imgtn.bdimg.com/it/u=336513716,3836559598&fm=26&gp=0.jpg"
                    }
                    else ->{
                        dailySentenceList[0].imgUrl
                    }
                }

            )
        })
    }

    override fun initData() {
        mViewModel.loadHomeBean()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}