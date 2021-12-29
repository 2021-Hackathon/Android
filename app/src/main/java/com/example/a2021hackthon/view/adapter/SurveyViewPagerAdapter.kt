package com.example.a2021hackthon.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.a2021hackthon.R

class SurveyViewPagerAdapter: RecyclerView.Adapter<SurveyViewPagerAdapter.ViewHolder>() {

    private val dataSet = mutableListOf<Survey>()
    private val resultList = mutableListOf<String>()

    fun setData(data: List<Survey>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

    fun getResult() : List<String> {
        return resultList
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val content: TextView = view.findViewById(R.id.item_survey_content)
        val answer1: AppCompatButton = view.findViewById(R.id.answer1)
        val answer2: AppCompatButton = view.findViewById(R.id.answer2)

        fun bind(data: Survey) {
            content.text = data.content
            answer1.text = data.answer1
            answer2.text = data.answer2
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_survey, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.answer1.setOnClickListener {
            resultList.add(holder.answer1.text.toString())
            resultList.remove(holder.answer2.text.toString())
        }

        holder.answer2.setOnClickListener {
            resultList.add(holder.answer2.text.toString())
            resultList.remove(holder.answer1.text.toString())
        }
    }

    override fun getItemCount() = 5
}