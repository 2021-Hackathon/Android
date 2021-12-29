package com.example.a2021hackthon.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a2021hackthon.view.fragment.HomeFaceFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> HomeFaceFragment()
            else -> HomeFaceFragment()
        }

}