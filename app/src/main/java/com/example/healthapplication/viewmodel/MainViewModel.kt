package com.example.healthapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthapplication.model.QuestionData
import com.example.healthapplication.model.QuestionListData

class MainViewModel : ViewModel() {
    val questionNumber: MutableLiveData<Int> = MutableLiveData(0)
    val totalQuestion: MutableLiveData<Int> = MutableLiveData(0)
    val finalActionText: MutableLiveData<String> = MutableLiveData("SKIP")
    val questionData: MutableLiveData<List<QuestionData>> = MutableLiveData()
}