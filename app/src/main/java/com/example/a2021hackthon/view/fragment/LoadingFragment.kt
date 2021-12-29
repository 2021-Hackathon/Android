package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentLoadingBinding
import com.example.a2021hackthon.databinding.FragmentSurveyFinishBinding
import com.example.a2021hackthon.model.remote.dto.Survey
import com.example.a2021hackthon.viewmodel.SurveyViewModel
import com.example.a2021hackthon.viewmodel.TimeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoadingFragment : Fragment() {

    private lateinit var binding: FragmentLoadingBinding

    private val args: LoadingFragmentArgs by navArgs()
    private val sViewModel: SurveyViewModel by viewModels()
    private val tViewModel: TimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.isSurvey) {
            surveyObserve()
            val answerList = arguments?.getBooleanArray("answers")!!
            Thread.sleep(1000L)
            sViewModel.postAnalyzeSurvey(Survey(answerList))
        } else {
            timeObserve()
            Thread.sleep(1000L)
            tViewModel.getAnalyzeTime()
        }
    }

    private fun surveyObserve() = with (sViewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToResultFragment2(it.URL, it.food))
        }

        isFailure.observe(viewLifecycleOwner) {
            com.example.a2021hackthon.view.utils.MessageUtils.showDialog(requireActivity(), it)
        }
    }

    private fun timeObserve() = with (tViewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToResultFragment2(it.URL, it.food))
        }

        isFailure.observe(viewLifecycleOwner) {
            com.example.a2021hackthon.view.utils.MessageUtils.showDialog(requireActivity(), it)
        }
    }
}