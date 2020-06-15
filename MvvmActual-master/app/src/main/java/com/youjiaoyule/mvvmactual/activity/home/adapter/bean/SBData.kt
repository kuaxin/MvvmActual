package com.youjiaoyule.mvvmactual.activity.home.adapter.bean

/**
 *  @author RenGX on 2020/6/15
 *
 */
data class SBData(
    val lucidaBabyList: List<LucidaBaby>
)

data class LucidaBaby(
    val babyAge: String,
    val babyName: String,
    val childVideoStatus: String,
    val classFlag: Int,
    val createTime: String,
    val id: String,
    val lucidaBabyId: String,
    val picUrl: String,
    val updateTime: String,
    val userSystem: String,
    val videoUrl: String
)