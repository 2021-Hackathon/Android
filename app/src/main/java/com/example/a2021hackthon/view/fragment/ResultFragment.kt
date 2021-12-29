package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentResultBinding
import com.example.a2021hackthon.model.remote.RetrofitInstance
import com.example.a2021hackthon.view.utils.ImagePicker

class ResultFragment : Fragment() {

    private val navController by lazy { findNavController() }

    private lateinit var binding: FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        Glide.with(binding.resultImg).load(RetrofitInstance.BASE_URL + args.imageUri.substring(1)).into(binding.resultImg)
        binding.resultContent.text = "오늘은 ${args.food}, 어떠신가요?"

        binding.btnBack.setOnClickListener {
            navController.navigate(R.id.action_resultFragment2_to_homeFragment2)
        }

        binding.btnRecommend.setOnClickListener {
            navController.navigate(
                ResultFragmentDirections.actionResultFragment2ToKakaoMapFragment(args.food)
            )
        }
    }
}