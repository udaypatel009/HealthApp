package com.example.healthapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.healthapplication.databinding.FragmentQuestionPageBinding

class QuestionPageFragment : Fragment() {

    lateinit var binding: FragmentQuestionPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentQuestionPageBinding.inflate(inflater, container, false)
        return binding.root
    }


}