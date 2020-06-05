package com.example.healthapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthapplication.model.QuestionData
import com.example.healthapplication.model.QuestionListData

class ResultViewModel : ViewModel() {
    val startAgainVisibility : MutableLiveData<Boolean> = MutableLiveData(false)
}