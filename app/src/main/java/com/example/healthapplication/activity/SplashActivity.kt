package com.example.healthapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthapplication.databinding.ActivitySplashBinding
import com.example.healthapplication.eventhandler.SplashEventHandler

class SplashActivity : AppCompatActivity(), SplashEventHandler {

    private val TAG = SplashActivity::class.java.simpleName
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eventHandler = this

    }

    override fun start() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}