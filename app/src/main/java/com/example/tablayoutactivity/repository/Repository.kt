package com.example.tablayoutactivity.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.tablayoutactivity.activity.FetchCalls
import com.example.tablayoutactivity.activity.FetchContacts
import com.example.tablayoutactivity.activity.FetchMessages
import com.example.tablayoutactivity.modelClass.Call
import com.example.tablayoutactivity.modelClass.Contact
import com.example.tablayoutactivity.modelClass.Message

class Repository(var context: Context) {
    private var fetchContacts: FetchContacts = FetchContacts(context)
    private var fetchCalls : FetchCalls =  FetchCalls(context)
    private var fetchMessages : FetchMessages = FetchMessages(context)
//    var contactData : MutableLiveData<Contact> = fetchContacts.getContacts()

   suspend fun getContacts(): ArrayList<Contact> {
        return fetchContacts.getContacts()
    }

    suspend fun getCall(): ArrayList<Call> {
        return fetchCalls.getCalls()
    }

    suspend fun getMessage() : ArrayList<Message>{
        return fetchMessages.getMessages()
    }
}