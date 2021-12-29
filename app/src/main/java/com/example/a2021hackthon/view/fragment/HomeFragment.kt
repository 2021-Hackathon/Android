package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2021hackthon.R
import com.example.a2021hackthon.base.BaseFragment
import com.example.a2021hackthon.databinding.FragmentHomeBinding
import com.example.a2021hackthon.view.adapter.ViewPagerAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()


    }

    private fun init() {
        adapter = ViewPagerAdapter(listOf(HomeFaceFragment()))
        binding.viewPagerHome.adapter = adapter
    }
}