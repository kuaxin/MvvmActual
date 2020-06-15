package com.youjiaoyule.mvvmactual.activity.home.adapter

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.youjiaoyule.mvvmactual.R
import com.youjiaoyule.mvvmactual.weight.loadImg
import com.youth.banner.Banner

/**
 *  @author RenGX on 2020/6/15
 *
 */
class HomeAdapter :
    BaseMultiItemQuickAdapter<HomeMultiItemEntity, BaseViewHolder>() {

    init {
        addItemType(HomeMultiItemEntity.BANNER_TYPE, R.layout.layout_home_banner)
        addItemType(HomeMultiItemEntity.ICON_TYPE, R.layout.layout_home_icon)
        addItemType(HomeMultiItemEntity.COURSE_TYPE, R.layout.layout_home_course)
        addItemType(HomeMultiItemEntity.STORY_TYPE, R.layout.layout_talk_story)
        addItemType(HomeMultiItemEntity.PAR_COURSE_TYPE, R.layout.layout_parent_child_course)
        addItemType(HomeMultiItemEntity.BABY_TYPE, R.layout.layout_star_baby)
    }

    override fun convert(holder: BaseViewHolder, item: HomeMultiItemEntity) {
        when(holder.itemViewType){
            HomeMultiItemEntity.BANNER_TYPE ->{
                initBanner(holder, item)
            }
            HomeMultiItemEntity.ICON_TYPE ->{
                initIcon(holder,item)
            }
        }
    }

    private fun initIcon(holder: BaseViewHolder, item: HomeMultiItemEntity) {
        val imgOne = holder.getView<ImageView>(R.id.img_icon_one)
        val imgTwo = holder.getView<ImageView>(R.id.img_icon_two)
        val imgThree = holder.getView<ImageView>(R.id.img_icon_three)
        item.iconData?.let {
            imgOne.loadImg(it[0].imgUrl)
            imgTwo.loadImg(it[1].imgUrl)
            imgThree.loadImg(it[2].imgUrl)
        }



    }

    private fun initBanner(holder: BaseViewHolder, item: HomeMultiItemEntity) {
        val banner = holder.getView<Banner<*, *>>(R.id.banner_title)
        banner.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, 30f)
            }
        }
        banner.clipToOutline = true
        val imgList = ArrayList<String>()
        val bannerData = item.bannerData
        bannerData?.forEachIndexed { index, hdData ->
            imgList.add(hdData.imgUrl)
        }
        banner.adapter = ImageAdapter(imgList)
        banner.start()
    }
}