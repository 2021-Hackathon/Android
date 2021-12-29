package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2021hackthon.databinding.FragmentHomeFaceSelectPhotoBinding


class HomeFaceSelectPhotoFragment : Fragment() {

    private lateinit var binding: FragmentHomeFaceSelectPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFaceSelectPhotoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}