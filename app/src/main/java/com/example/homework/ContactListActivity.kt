package com.example.homework

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.WRITE_CONTACTS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {

    private val contactList = mutableListOf<Contact>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

         val adapter = ContactListAdapter()

        reciclerView.adapter = adapter
        reciclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)


        ActivityCompat.requestPermissions(this, arrayOf(READ_CONTACTS), 200)
        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            getContactList()
        }
        adapter.setContactList(contactList)

    }

    fun getContactList() {

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.let {
            while (it.moveToNext()){
                contactList.add(
                    Contact(id = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)),
                        name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        phone = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    )
                )
            }
        }
        cursor?.close()
    }
}