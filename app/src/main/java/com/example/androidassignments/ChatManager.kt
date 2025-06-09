package com.example.androidassignments

class ChatManager {
    private val chatMessages = mutableListOf<String>()

    fun addMessage(message: String?) {
        if (!message.isNullOrBlank()) {
            chatMessages.add(message)
        }
    }

    fun clearMessages() {
        chatMessages.clear()
    }

    fun getChatMessages(): List<String> {
        return chatMessages
    }

    fun getMessageCount(): Int {
        return chatMessages.size
    }

    fun getLastMessage(): String? {
        return chatMessages.lastOrNull()
    }
}
