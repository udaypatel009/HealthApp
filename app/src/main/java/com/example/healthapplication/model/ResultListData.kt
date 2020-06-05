package com.example.healthapplication.model

data class ResultListData(
    val resultData: List<ResultData>
)

data class ResultData(
    val result: String,
    val resultId: Int
)