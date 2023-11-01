package com.example.tablayoutactivity.activity

import android.content.Context
import android.provider.CallLog
import com.example.tablayoutactivity.modelClass.Call

class FetchCalls(var context: Context) {

    var projection = listOf<String>(CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.DATE, CallLog.Calls.DURATION,CallLog.Calls._ID).toTypedArray()

    suspend fun getCalls(): ArrayList<Call> {
        val callLogs = ArrayList<Call>()
        val sortOrder = "${CallLog.Calls.DATE} DESC"
        val cursor = context.contentResolver.query(CallLog.Calls.CONTENT_URI, projection, null, null, sortOrder)

        if (cursor!!.count > 0){
            cursor.moveToFirst()
            do{
                val callNumberIndex = cursor.getString(0)
                val callTypeIndex = cursor.getInt(1)
                val callDateIndex = cursor.getLong(2)
                val callDurationIndex = cursor.getString(3)


                callLogs.add(Call(callNumberIndex, callTypeIndex, callDateIndex, callDurationIndex))

            } while (cursor.moveToNext())
        }

        return  callLogs
    }

}