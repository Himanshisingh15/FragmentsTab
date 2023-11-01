package com.example.tablayoutactivity.activity

import android.content.Context
import android.provider.ContactsContract
import com.example.tablayoutactivity.modelClass.Contact

class FetchContacts(var context: Context) {

    val projection = listOf<String>(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER).toTypedArray()
     suspend fun getContacts(): ArrayList<Contact> {
        val contactsList = ArrayList<Contact>()


//        val contentResolver = ContentResolver(context)

        val cursor = context.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null)

            if (cursor!!.count > 0) {
                cursor.moveToFirst()
                do {

                    val contactId = cursor.getInt(0)
                    val contactName = cursor.getString(1)
                    val contactNumber = cursor.getString(2)


                    contactsList.add(Contact(contactId, contactName, contactNumber))
                } while (cursor.moveToNext())
        }
         return contactsList
    }
}