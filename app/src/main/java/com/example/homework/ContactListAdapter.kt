package com.example.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter: RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private val contactList: MutableList<Contact> = mutableListOf()

    fun setContactList(newContacts: List<Contact>) {
        contactList.clear()
        contactList.addAll(newContacts)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder (itemView) {

        val title: TextView = itemView.findViewById(R.id.itemTitle)

        fun bind (item: Contact) {
            title.text = item.name
        }
    }
}