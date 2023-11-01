package com.example.tablayoutactivity.activity

import android.content.Context
import android.net.Uri
import com.example.tablayoutactivity.modelClass.Message
import java.text.SimpleDateFormat
import java.util.Locale

class FetchMessages(var context: Context) {

    var dateFormat = SimpleDateFormat("dd-mm-yyyy", Locale.getDefault())

//    var projection = arrayOf("address","body","date")

    suspend fun getMessages() : ArrayList<Message> {
        val messageList = ArrayList<Message>()

        val cursor = context.contentResolver.query(Uri.parse("content://sms/inbox"), arrayOf("address", "body", "date"), null, null, " date DESC")

        if (cursor!!.count > 0){
            cursor.moveToFirst()
            do {
                val msgId = cursor.getString(0)
                val msgContent = cursor.getString(1)
                val msgDate = cursor.getLong(2)

                messageList.add(Message(msgId, msgContent, msgDate))

            } while (cursor.moveToNext())
        }


        return messageList
    }


}