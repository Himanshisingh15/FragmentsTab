package com.example.tablayoutactivity.fragments

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
import com.example.tablayoutactivity.MessageItemClickListener
import com.example.tablayoutactivity.R
import com.example.tablayoutactivity.activity.TabLayoutActivity
import com.example.tablayoutactivity.adapter.MessageRecyclerViewAdapter
import com.example.tablayoutactivity.databinding.FragmentMessageBinding
import com.example.tablayoutactivity.modelClass.Message
import com.example.tablayoutactivity.viewModel.TabsViewModel

class MessageFragment : Fragment() {
    private lateinit var binding: FragmentMessageBinding
    private lateinit var viewModel: TabsViewModel
    lateinit var messageRecyclerViewAdapter: MessageRecyclerViewAdapter
    lateinit var messageItemClickListener : MessageItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_message,
            container,
            false
        )
        viewModel = (activity as TabLayoutActivity).viewModel

        checkPermission()

//        viewModel.messages.observe(requireActivity(), Observer {
//            messageRecyclerViewAdapter = MessageRecyclerViewAdapter(it, object : MessageItemClickListener{
//
//                override fun onMessageClick(position: Int, list: ArrayList<  Message>) {
//                    var message = list[position]
//
//                }
//
//            })
//        })
        return binding.root
    }

//    fun showMessage(messageId : String, messageContent : String, messageDate: Long){
//        var intent = Intent(context, ShowMessageActivity::class.java)
//        intent.putExtra("messageId", messageId)
//        intent.putExtra("messageContent", messageContent)
//        intent.putExtra("messageDate", messageDate)
//    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            sms()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_SMS),
                101
            )
        }
    }

    fun sms() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.fetchAllMessages().observe(requireActivity(), Observer {
            messageRecyclerViewAdapter = MessageRecyclerViewAdapter(it)
            binding.recyclerView.adapter = messageRecyclerViewAdapter
            messageRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sms()
    }else {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_SMS), 101)
    }
}
}