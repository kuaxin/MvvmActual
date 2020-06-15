package com.youjiaoyule.mvvmactual.activity.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.youjiaoyule.mvvmactual.R
import com.youjiaoyule.mvvmactual.activity.home.adapter.HomeAdapter
import com.youjiaoyule.mvvmactual.activity.home.adapter.HomeMultiItemEntity
import com.youjiaoyule.mvvmactual.base.BaseLifeCycleFragment
import com.youjiaoyule.mvvmactual.module.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  @author RenGX on 2020/6/11
 *
 */
class HomeFragment private constructor(): BaseLifeCycleFragment<HomeViewModel>() {
    private var type: Int? = null
    private var mAdapter:HomeAdapter? = null
    private var list: ArrayList<HomeMultiItemEntity> = ArrayList()

    constructor(type:Int):this(){
        this.type = type
    }

    override fun initDataObserver() {
        mViewModel.homeData.observe(this, Observer {

            when(it[0].adType){
                4 ->{
                    val homeMultiItemEntity = HomeMultiItemEntity(HomeMultiItemEntity.BANNER_TYPE)
                    homeMultiItemEntity.bannerData = it
                    mAdapter?.setData(0,homeMultiItemEntity)
                }

                6 ->{
                    val homeMultiItemEntity = HomeMultiItemEntity(HomeMultiItemEntity.ICON_TYPE)
                    homeMultiItemEntity.iconData = it
                    mAdapter?.setData(1,homeMultiItemEntity)
                }
            }
        })
    }

    override fun initView() {
        super.initView()
        initRv()
    }

    private fun initRv() {
        rv_home.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        mAdapter = HomeAdapter()
        rv_home.adapter = mAdapter

        list.add(HomeMultiItemEntity(HomeMultiItemEntity.BANNER_TYPE))
        list.add(HomeMultiItemEntity(HomeMultiItemEntity.ICON_TYPE))
        list.add(HomeMultiItemEntity(HomeMultiItemEntity.COURSE_TYPE))
        list.add(HomeMultiItemEntity(HomeMultiItemEntity.STORY_TYPE))
        list.add(HomeMultiItemEntity(HomeMultiItemEntity.PAR_COURSE_TYPE))
        list.add(HomeMultiItemEntity(HomeMultiItemEntity.BABY_TYPE))
        mAdapter?.data = list
    }

    override fun initData() {
        mViewModel.loadHomeData(4)

        mViewModel.loadHomeData(6)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
}