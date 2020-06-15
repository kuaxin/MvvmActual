package com.youjiaoyule.mvvmactual.activity.home.adapter

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.youjiaoyule.mvvmactual.activity.home.adapter.bean.HDData
import com.youjiaoyule.mvvmactual.activity.home.adapter.bean.PCData
import com.youjiaoyule.mvvmactual.activity.home.adapter.bean.SBData
import com.youjiaoyule.mvvmactual.activity.home.adapter.bean.TSData

/**
 *  @author RenGX on 2020/6/15
 *
 */
class HomeMultiItemEntity(override val itemType: Int) : MultiItemEntity {

    companion object{
        val BANNER_TYPE = 100
        val ICON_TYPE = 200
        val COURSE_TYPE = 300
        val STORY_TYPE = 400
        val PAR_COURSE_TYPE = 500
        val BABY_TYPE = 600
    }

    var bannerData: List<HDData>? = null
    var iconData: List<HDData>? = null
    var courseData: String = ""
    var babyData: SBData? = null
    var storyData: TSData? = null
    var parCourseData: PCData? = null
}