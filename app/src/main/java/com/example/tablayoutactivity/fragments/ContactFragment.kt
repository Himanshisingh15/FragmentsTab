package com.example.tablayoutactivity.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
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
import com.example.tablayoutactivity.adapter.ContactRecyclerViewAdapter
import com.example.tablayoutactivity.databinding.FragmentContactBinding
import com.example.tablayoutactivity.modelClass.Contact
import com.example.tablayoutactivity.onRequestCall
import com.example.tablayoutactivity.viewModel.TabsViewModel

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    private lateinit var viewModel: TabsViewModel
    private lateinit var contactRecyclerViewAdapter : ContactRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_contact, container, false)
        viewModel = (activity as TabLayoutActivity).viewModel

        checkPermission()
        checkPermissionCalling()

        return binding.root
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            personContact()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), 1001)
        }
    }

    fun personContact(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.fetchAllContacts().observe(requireActivity(), Observer {

            contactRecyclerViewAdapter = ContactRecyclerViewAdapter(it)

            binding.recyclerView.adapter = contactRecyclerViewAdapter
            contactRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            personContact()
//            callContact()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), 1001)
        }
    }

    fun callContact(){
        viewModel.contacts.observe(requireActivity(), Observer {
            contactRecyclerViewAdapter.setOnCallIconClickListener(object : onRequestCall{
                override fun onCall(mobileNumber: String) {
                    var intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel: $mobileNumber")
                    startActivity(intent)
                }

            })
        })
    }

    fun checkPermissionCalling(){
        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callContact()
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE), 101)
        }
    }

//    fun  makeCall(){
//
//        var mobileNumber = mobileNumber
//        var intentCall = Intent(Intent.ACTION_CALL)
//        intentCall.data = Uri.parse("tel:$mobileNumber")
//        startActivity(intentCall)
//
//    }
    }

