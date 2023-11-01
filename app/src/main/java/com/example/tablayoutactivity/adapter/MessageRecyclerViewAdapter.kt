package com.example.tablayoutactivity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutactivity.databinding.MessageListItemBinding
import com.example.tablayoutactivity.modelClass.Message
import java.text.SimpleDateFormat
import java.util.Locale

class MessageRecyclerViewAdapter(private val list: ArrayList<Message>) : RecyclerView.Adapter<MessageRecyclerViewAdapter.MyViewHolder>() {


    inner class MyViewHolder(var binding: MessageListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageRecyclerViewAdapter.MyViewHolder {
        val binding = MessageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var message = list[position]
        holder.binding.tvMessageId.text = message.messageId
//        holder.binding.tvMessageContent.text = message.messageContent
        var date = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())

        holder.binding.tvDate.text = date.format(message.messageDate)

        var msgLength = message.messageContent.length
        var holdContent = message.messageContent.substring(0, minOf(50,msgLength))

        holder.binding.tvMessageContent.text = holdContent


    }
}