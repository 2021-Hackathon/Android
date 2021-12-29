package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentSurveyFinishBinding

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

        binding.btnSurveyResult.setOnClickListener {
            findNavController().navigate(R.id.action_surveyFinishFragment_to_resultFragment)
        }
    }

}