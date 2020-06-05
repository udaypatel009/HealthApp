package com.example.healthapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.healthapplication.databinding.ActivityResultBinding
import com.example.healthapplication.eventhandler.ResultEventHandler
import com.example.healthapplication.model.ResultData
import com.example.healthapplication.model.ResultListData
import com.example.healthapplication.util.JsonUtils
import com.example.healthapplication.viewmodel.ResultViewModel
import com.google.gson.Gson

class ResultActivity : AppCompatActivity(),ResultEventHandler {

    private val TAG = ResultActivity::class.java.simpleName
    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var resultDataList: List<ResultData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        binding.lifecycleOwner = this
        binding.eventHandler = this
        binding.viewModel = viewModel

        binding.txtReult.text = getResult()


        Handler().postDelayed({
            viewModel.startAgainVisibility.value = true
        }, 1500)

    }

    private fun getResult(): String? {
        resultDataList = Gson().fromJson(
            JsonUtils().getJsonDataFromAsset(this, "result.json"),
            ResultListData::class.java
        ).resultData

        return resultDataList.get((0..(resultDataList.size-1)).random()).result

    }

    override fun startAgain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}