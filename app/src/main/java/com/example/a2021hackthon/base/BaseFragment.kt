package com.example.a2021hackthon.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding = _binding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _binding = null
    }
}