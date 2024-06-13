package com.example.pdmchat

import android.icu.text.SimpleDateFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Date
import java.util.Locale

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(message: Message) {
        itemView.findViewById<TextView>(R.id.senderName).text = message.senderName
        itemView.findViewById<TextView>(R.id.messageText).text = message.text

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(message.timestamp))
        itemView.findViewById<TextView>(R.id.messageTimestamp).text = formattedDate
    }
}