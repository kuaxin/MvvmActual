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
            val dailySentenceList = it.dailySentenceList
            val dailySentence = dailySentenceList[0]
            tv_china.text = dailySentence.sentenceChContent
            Glide.with(this).load("http://wdkid-sentence.oss-cn-beijing.aliyuncs.com/20200518-24/20200611.jpg").into(img_word)
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initDate() {

        mViewModel.loadHomeBean()
    }

    override fun initView() {
        super.initView()
    }

}