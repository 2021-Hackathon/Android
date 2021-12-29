package com.example.a2021hackthon.view.utils

import androidx.fragment.app.FragmentActivity
import com.example.a2021hackthon.view.fragment.DefaultDialogFragment

object MessageUtils {
    fun showDialog(activity: FragmentActivity, content: String) {
        DefaultDialogFragment(content).show(activity.supportFragmentManager, "dialog")
    }
}