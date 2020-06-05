package com.example.healthapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.healthapplication.adapter.QuestionPagerAdapter
import com.example.healthapplication.databinding.ActivityMainBinding
import com.example.healthapplication.eventhandler.MainEventHandler
import com.example.healthapplication.model.QuestionListData
import com.example.healthapplication.util.JsonUtils
import com.example.healthapplication.viewmodel.MainViewModel
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), MainEventHandler{

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.lifecycleOwner = this
        binding.eventHandler = this
        binding.viewModel = viewModel

        getQuestionData()

        binding.questionPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                viewModel.questionNumber.value = position +1
                if( viewModel.questionNumber.value == viewModel.questionData.value?.size){
                    viewModel.finalActionText.value = "DONE"
                } else{
                    viewModel.finalActionText.value = "SKIP"
                }
            }
        })

    }

    private fun getQuestionData() {

        viewModel.questionData.value = Gson().fromJson(
            JsonUtils().getJsonDataFromAsset(this, "question.json"),
            QuestionListData::class.java
        ).questionData
        binding.questionPager.adapter = QuestionPagerAdapter(viewModel.questionData.value!!)

        viewModel.totalQuestion.value= viewModel.questionData.value!!.size

    }

    override fun previous() {
        binding.questionPager.setCurrentItem(binding.questionPager.currentItem - 1)
    }

    override fun next() {
        binding.questionPager.setCurrentItem(binding.questionPager.currentItem + 1)
    }

    override fun finalAction() {
        startActivity(Intent(this, ResultActivity::class.java))
        finish()
    }

}

