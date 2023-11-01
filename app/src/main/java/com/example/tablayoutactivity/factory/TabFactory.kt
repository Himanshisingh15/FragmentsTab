package com.example.tablayoutactivity.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tablayoutactivity.activity.FetchContacts
import com.example.tablayoutactivity.repository.Repository
import com.example.tablayoutactivity.viewModel.TabsViewModel


class TabFactory(var context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create (modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(TabsViewModel::class.java)){
            return TabsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}