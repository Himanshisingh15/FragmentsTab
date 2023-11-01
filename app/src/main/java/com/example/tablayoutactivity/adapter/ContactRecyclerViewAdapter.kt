package com.example.tablayoutactivity.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tablayoutactivity.databinding.ListItemBinding
import com.example.tablayoutactivity.modelClass.Contact
import com.example.tablayoutactivity.onRequestCall

class ContactRecyclerViewAdapter(private val list: ArrayList<Contact>) :Adapter<ContactRecyclerViewAdapter.MyViewHolder>() {

    private var requestCall : onRequestCall? = null
//    var mobileNumber = String

    inner class MyViewHolder(val binding: ListItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ):MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var contact = list[position]
        holder.binding.tvName.text = contact.name
        holder.binding.tvMobile.text = contact.mobileNumber


        holder.binding.tvCall.setOnClickListener {
            requestCall?.onCall(contact.mobileNumber)
        }
    }

    fun setOnCallIconClickListener(listener : onRequestCall){
        requestCall = listener
    }

}