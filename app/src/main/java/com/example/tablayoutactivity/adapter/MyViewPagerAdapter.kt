package com.example.tablayoutactivity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayoutactivity.fragments.CallFragment
import com.example.tablayoutactivity.fragments.ContactFragment
import com.example.tablayoutactivity.fragments.MessageFragment

class MyViewPagerAdapter(var fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ContactFragment()
            1 -> CallFragment()
            else -> MessageFragment()
        }
    }
}