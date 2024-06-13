package com.example.pdmchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pdmchat.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        amb.mainTb.apply {
            title = getString(R.string.app_name)
            subtitle = this@MainActivity.javaClass.simpleName
            setSupportActionBar(this)
        }

        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        amb.sendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val messageText = amb.messageInput.text.toString().trim()
        if (messageText.isEmpty()) return

        val senderId = auth.currentUser?.uid ?: return
        val senderName = auth.currentUser?.displayName ?: "Anônimo"
        val receiverId = ""
        val message = Message(senderId, senderName, receiverId, messageText, System.currentTimeMillis())

        val messagesRef = database.child("messages")
        messagesRef.push().setValue(message)
            .addOnSuccessListener {
                amb.messageInput.setText("")
            }
    }
}