package com.youjiaoyule.mvvmactual.activity.home

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.youjiaoyule.mvvmactual.R
import com.youjiaoyule.mvvmactual.base.BaseLifeCycleActivitty
import com.youjiaoyule.mvvmactual.module.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

/**
 *  @author RenGX on 2020/6/10
 *
 */
class HomeActivity: BaseLifeCycleActivitty<HomeViewModel>() {
    override fun initDataObserver() {
        mViewModel.homeData.observe(this, Observer {

        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initDate() {

    }

    override fun initView() {
        super.initView()
    }

}