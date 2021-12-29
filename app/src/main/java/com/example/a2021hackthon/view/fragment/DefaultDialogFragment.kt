package com.example.a2021hackthon.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.a2021hackthon.R

class DefaultDialogFragment(
    private val content: String
) : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv: TextView = view.findViewById(R.id.textView)
        val btn: Button = view.findViewById(R.id.btn_confirm)

        tv.text = content
        btn.setOnClickListener {
            dismiss()
        }
    }
}