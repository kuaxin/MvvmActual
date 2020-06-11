package com.youjiaoyule.mvvmactual.module.home

/**
 *  @author RenGX on 2020/6/10
 *
 */
data class HomeBean(
    val dailySentenceList: List<DailySentence>,
    val page: Page
)

data class DailySentence(
    val audioTime: String,
    val audioUrl: String,
    val commentNum: Int,
    val createTime: String,
    val dailySentenceId: String,
    val dailySentenceStatus: Int,
    val distanceTime: String,
    val id: String,
    val imgUrl: String,
    val sentenceChContent: String,
    val sentenceEngContent: String,
    val showData: String,
    val updateTime: String,
    val viewFakeNum: Int,
    val viewNum: Int,
    val viewRealNum: Int
)

data class Page(
    val automaticCount: Boolean,
    val currentPage: Int,
    val first: Boolean,
    val last: Boolean,
    val pageSize: Int,
    val totalPageSize: Int,
    val totalResultSize: Int
)