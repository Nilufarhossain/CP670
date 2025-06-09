package com.example.androidassignments

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChatManagerTest {

    private lateinit var chatManager: ChatManager

    @Before
    fun setUp() {
        chatManager = ChatManager()
    }

    @Test
    fun testAddMessage() {
        chatManager.addMessage("Hello, World!")
        assertEquals(1, chatManager.getMessageCount())
        assertEquals("Hello, World!", chatManager.getChatMessages()[0])
    }

    @Test
    fun testAddEmptyMessage() {
        chatManager.addMessage("")
        assertEquals(0, chatManager.getMessageCount())
    }

    @Test
    fun testAddNullMessage() {
        chatManager.addMessage(null)
        assertEquals(0, chatManager.getMessageCount())
    }

    @Test
    fun testAddMultipleMessages() {
        chatManager.addMessage("Hi")
        chatManager.addMessage("How are you?")
        assertEquals(2, chatManager.getMessageCount())
        assertEquals("Hi", chatManager.getChatMessages()[0])
        assertEquals("How are you?", chatManager.getChatMessages()[1])
    }

    @Test
    fun testClearMessages() {
        chatManager.addMessage("Test1")
        chatManager.addMessage("Test2")
        chatManager.clearMessages()
        assertEquals(0, chatManager.getMessageCount())
    }

    @Test
    fun testGetLastMessage() {
        chatManager.addMessage("First")
        chatManager.addMessage("Last")
        assertEquals("Last", chatManager.getLastMessage())
    }

    @Test
    fun testGetLastMessageWhenEmpty() {
        assertNull(chatManager.getLastMessage())
    }
}
