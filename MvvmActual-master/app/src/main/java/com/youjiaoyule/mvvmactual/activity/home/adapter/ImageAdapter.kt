package com.youjiaoyule.mvvmactual.activity.home.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.youjiaoyule.mvvmactual.weight.loadImg
import com.youth.banner.adapter.BannerAdapter


class ImageAdapter(datas:ArrayList<String>) : BannerAdapter<String,ImageAdapter.BannerViewHolder>(datas) {

    var context:Context? = null


    class BannerViewHolder(itemView: ImageView) : RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)
        context = parent?.context
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: String?, position: Int, size: Int) {
        holder?.imageView?.loadImg(data!!)
    }
}