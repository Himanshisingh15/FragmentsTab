package com.example.tablayoutactivity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tablayoutactivity.R
import com.example.tablayoutactivity.databinding.CallListItemBinding
import com.example.tablayoutactivity.modelClass.Call

class CallRecyclerViewAdapter(private val list :ArrayList<Call>) : Adapter<CallRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: CallListItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CallListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() : Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var callLogs = list[position]
        holder.binding.tvCallNumber.text = callLogs.callNumber
        holder.binding.tvCallNo.text = callLogs.callNumber

        var time = callLogs.callDuration.toInt()
        var timeSec = 0
        var timeMin = 0
        if (time < 59){
           timeSec =  callLogs.callDuration.toInt()
        }else{
          timeMin =   time/60
            timeSec = time%60
        }
        holder.binding.tvDuration.text = "$timeMin:$timeSec"

        when(callLogs.callType){
            1 -> holder.binding.tvCallIncoming.setImageResource(R.drawable.baseline_call_received_24)
            2 -> holder.binding.tvCallIncoming.setImageResource(R.drawable.baseline_call_made_24)
            3 -> holder.binding.tvCallIncoming.setImageResource(R.drawable.baseline_call_missed_24)
        }
    }
}