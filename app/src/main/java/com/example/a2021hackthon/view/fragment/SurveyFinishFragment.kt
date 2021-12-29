package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentSurveyFinishBinding
import com.example.a2021hackthon.model.remote.dto.Survey
import com.example.a2021hackthon.viewmodel.SurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

class SurveyFinishFragment : Fragment() {

    private lateinit var binding: FragmentSurveyFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurveyFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answerList = arguments?.getBooleanArray("answers")!!

        binding.btnSurveyResult.setOnClickListener {
            val bundle = Bundle()
            bundle.putBooleanArray("answers", answerList)
            bundle.putBoolean("isSurvey", true)
            findNavController().navigate(SurveyFinishFragmentDirections.actionSurveyFinishFragmentToLoadingFragment())
        }
    }
}