package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentHomeFaceBinding

class HomeFaceFragment : Fragment() {

    private lateinit var binding: FragmentHomeFaceBinding

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFaceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartHomeFace.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_homeFaceSelectPhotoFragment)
        }
    }
}