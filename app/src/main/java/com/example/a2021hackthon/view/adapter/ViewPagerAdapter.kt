package com.example.a2021hackthon.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a2021hackthon.view.fragment.HomeFaceFragment
import com.example.a2021hackthon.view.fragment.HomeSurveyFragment
import com.example.a2021hackthon.view.fragment.HomeTimeFragment
import com.example.a2021hackthon.view.fragment.SurveyFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> HomeFaceFragment()
            1 -> HomeTimeFragment()
            2 -> HomeSurveyFragment()
            else -> HomeFaceFragment()
        }

}