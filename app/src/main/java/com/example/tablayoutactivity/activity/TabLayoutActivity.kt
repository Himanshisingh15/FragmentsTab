package com.example.tablayoutactivity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.tablayoutactivity.R
import com.example.tablayoutactivity.adapter.MyViewPagerAdapter
import com.example.tablayoutactivity.databinding.ActivityTabLayoutBinding
import com.example.tablayoutactivity.factory.TabFactory
import com.example.tablayoutactivity.repository.Repository
import com.example.tablayoutactivity.viewModel.TabsViewModel
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTabLayoutBinding
    lateinit var viewModel: TabsViewModel
    private var tabName = arrayOf("Contact", "Call", "Message")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tab_layout)

//        var fetchContacts = FetchContacts(this)
//        var repository = Repository(this, fetchContacts)
        viewModel = ViewModelProvider(this, TabFactory(this))[TabsViewModel::class.java]

//        binding.lifecycleOwner = this

        var myViewPagerAdapter = MyViewPagerAdapter(this)
        binding.viewPager.adapter = myViewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = tabName[position]
        }.attach()
    }
}