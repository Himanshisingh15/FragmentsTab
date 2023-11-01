package com.example.tablayoutactivity.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablayoutactivity.R
import com.example.tablayoutactivity.activity.TabLayoutActivity
import com.example.tablayoutactivity.adapter.CallRecyclerViewAdapter
import com.example.tablayoutactivity.databinding.FragmentCallBinding
import com.example.tablayoutactivity.viewModel.TabsViewModel

class CallFragment : Fragment() {
    private lateinit var binding : FragmentCallBinding
    private lateinit var viewModel : TabsViewModel
    lateinit var callRecyclerViewAdapter: CallRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_call, container, false)
        viewModel = (activity as TabLayoutActivity).viewModel
        checkPermission()
        return binding.root
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED){
            callLogs()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_CALL_LOG, Manifest.permission.CALL_PHONE), 101)
        }
    }

    fun callLogs(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.fetchAllCalls().observe(requireActivity(), Observer {
            callRecyclerViewAdapter = CallRecyclerViewAdapter(it)
            binding.recyclerView.adapter = callRecyclerViewAdapter
            callRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            callLogs()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_CALL_LOG, Manifest.permission.CALL_PHONE), 101)
        }
    }

}