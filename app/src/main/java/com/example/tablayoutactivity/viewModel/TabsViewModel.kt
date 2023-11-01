package com.example.tablayoutactivity.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tablayoutactivity.activity.FetchContacts
import com.example.tablayoutactivity.modelClass.Call
import com.example.tablayoutactivity.modelClass.Contact
import com.example.tablayoutactivity.modelClass.Message
import com.example.tablayoutactivity.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TabsViewModel(var context: Context) : ViewModel() {

    private var repository: Repository = Repository(context)
    var contacts : MutableLiveData<ArrayList<Contact>> = MutableLiveData()
    var calls : MutableLiveData<ArrayList<Call>> = MutableLiveData()
    var messages : MutableLiveData<ArrayList<Message>> = MutableLiveData()

//    var contactId : MutableLiveData<Int> = MutableLiveData()
//    var contactName : MutableLiveData<String> = MutableLiveData()
//    var contactNumber : MutableLiveData<String> = MutableLiveData()

    fun fetchAllContacts() : MutableLiveData<ArrayList<Contact>> {
        viewModelScope.launch(Dispatchers.IO){

                contacts.postValue(repository.getContacts())
            }

        return contacts
        }

    fun fetchAllCalls() : MutableLiveData<ArrayList<Call>>{

        viewModelScope.launch(Dispatchers.IO){

            calls.postValue(repository.getCall())

//            val callList = repository.getCall()
//            calls.postValue(callList)
        }

        return calls

    }

    fun fetchAllMessages() : MutableLiveData<ArrayList<Message>>{

        viewModelScope.launch(Dispatchers.IO){

            messages.postValue(repository.getMessage())

//         var msgList = repository.getMessage()
//            messages.postValue(msgList)
        }
        return messages
    }
    }

