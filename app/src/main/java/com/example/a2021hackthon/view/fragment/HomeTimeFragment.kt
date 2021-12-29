package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentHomeTimeBinding
import com.example.a2021hackthon.viewmodel.TimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTimeFragment : Fragment() {

    private lateinit var binding: FragmentHomeTimeBinding
    private val viewModel: TimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        binding.btnStartHomeTime.setOnClickListener {
            viewModel.getAnalyzeTime()
        }
    }

    private fun observe() {
        viewModel.isSuccess.observe(viewLifecycleOwner, {
          findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToResultFragment2(it.URL, it.food))
        })
    }
}