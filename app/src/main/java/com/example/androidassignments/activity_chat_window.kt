package com.example.androidassignments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class activity_chat_window : AppCompatActivity() {

    private lateinit var chatListView: ListView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: Button

    private lateinit var chatMessages: ArrayList<String>
    private lateinit var messageAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize views
        chatListView = findViewById(R.id.chat_list)
        messageBox = findViewById(R.id.message_box)
        sendButton = findViewById(R.id.send_button)

        // Initialize message list and adapter
        chatMessages = ArrayList()
        messageAdapter = ChatAdapter(this)
        chatListView.adapter = messageAdapter

        // Handle Send button click
        sendButton.setOnClickListener {
            val message = messageBox.text.toString().trim()
            if (message.isNotEmpty()) {
                chatMessages.add("You: $message")
                messageAdapter.notifyDataSetChanged() // Update list
                messageBox.setText("") // Clear input
            }
        }
    }

    inner class ChatAdapter(context: Context) : ArrayAdapter<String>(context, 0, chatMessages) {

        override fun getCount(): Int {
            return chatMessages.size
        }

        override fun getItem(position: Int): String {
            return chatMessages[position]
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater: LayoutInflater = this@activity_chat_window.layoutInflater
            val rowView: View = if (position % 2 == 0) {
                inflater.inflate(R.layout.chat_call_incoming, parent, false)
            } else {
                inflater.inflate(R.layout.chat_call_outgoing, parent, false)
            }

            val messageText = rowView.findViewById<TextView>(R.id.message_text)
            messageText.text = getItem(position)

            return rowView
        }
    }
}
