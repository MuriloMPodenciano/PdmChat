package com.example.pdmchat

data class Message(
    val senderId: String = "",
    val senderName: String = "",
    val receiverId: String = "",
    val text: String = "",
    val timestamp: Long = 0
)