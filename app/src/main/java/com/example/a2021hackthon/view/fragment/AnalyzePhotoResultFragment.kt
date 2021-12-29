package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentAnalyzePhotoResultBinding
import com.example.a2021hackthon.model.remote.RetrofitInstance

class AnalyzePhotoResultFragment : Fragment() {

    private lateinit var binding: FragmentAnalyzePhotoResultBinding

    private val navArgs: AnalyzePhotoResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnalyzePhotoResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEmotion.text = navArgs.emotion
        binding.tvFood.text = "이런 기분에 ${navArgs.food}은 어때요?"
        Glide.with(this)
            .load(RetrofitInstance.BASE_URL + navArgs.imageUri.substring(1))
            .into(binding.ivFood)
    }
}