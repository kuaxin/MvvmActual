package com.youjiaoyule.mvvmactual.activity.home.adapter.bean

/**
 *  @author RenGX on 2020/6/15
 *
 */
data class TSData(
    val page: TSPage,
    val ybbTellStories: List<YbbTellStory>
)

data class TSPage(
    val automaticCount: Boolean,
    val currentPage: Int,
    val first: Boolean,
    val last: Boolean,
    val pageSize: Int,
    val totalPageSize: Int,
    val totalResultSize: Int
)

data class YbbTellStory(
    val audioUrl: String,
    val chName: String,
    val coverImageUrl: String,
    val createTime: String,
    val enName: String,
    val id: String,
    val isueNumber: String,
    val part: String,
    val tellStoryId: String,
    val tellStoryStatus: String,
    val updateTime: String
)