package com.youjiaoyule.mvvmactual.weight

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 *  @author RenGX on 2020/6/11
 *
 */
fun ImageView.loadImg(url: String){
    Glide.with(this.context).load(url).into(this)
}