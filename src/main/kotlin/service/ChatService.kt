package service

import data.Chat
import data.Message
import exception.NoteNotFoundException

object ChatService {

    private var chats =  mutableSetOf<Chat>()
    private var amountIdMessage = 0

    fun clear() {
        chats = mutableSetOf<Chat>()
    }

    fun getUnreadChatsCount(): Int {
        var unreadChatsCount = 0
        chats.forEach { chat ->
            for (message in chat.messages) {
                if (!message.read) {
                    unreadChatsCount++
                    break
                }
            }
        }
        return unreadChatsCount
    }

    fun getChats(): MutableSet<Chat>{
        return chats
    }

    fun getLastMessagesFromChat(): MutableList<Message> {
        val lastMessages = mutableListOf<Message>()
        chats.forEach { chat -> lastMessages.add(chat.messages.last()) }
        if (lastMessages.isNotEmpty()) return lastMessages
        throw  NoteNotFoundException("Нет сообщений.")
    }

    fun getByChatId(chatId: Int, messagesId: Int): MutableList<Message> {
        val getMessages = mutableListOf<Message>()
        chats.forEach { chat: Chat ->
            if (chat.id == chatId) {
                chat.messages.forEach { message: Message ->
                    if (message.id >= messagesId) {
                        message.read = true
                        getMessages.add(message)
                    }
                }
            }
        }
        if (getMessages.isNotEmpty()) return getMessages
        throw  NoteNotFoundException("Нет сообщений.")
    }

    fun get(messagesLastId: Int): Chat {
        chats.forEach { chat ->
            if (chat.messages.last().id == messagesLastId) return chat
        }
        throw NoteNotFoundException("Нет сообщений.")
    }

    fun createMessage(id: Int, text: String): Message {
        return Message(amountIdMessage + 1, text, false)

    }

    fun createChat(id: Int, newMessage: Message): Chat {
        chats.forEach {chat: Chat ->
            if (chat.id == id) {
                chat.messages.add(newMessage)
                return chat
            }
        }
        chats.add(Chat(chats.size, mutableListOf(newMessage)))
        return chats.last()
    }


    fun deleteChat(id: Int): Chat {
        chats.forEach { chat: Chat ->
            if (id == chat.id) {
                chats.remove(chat)
                return chat
            }
        }
        throw NoteNotFoundException("Нет чата")
    }

    fun getAllChats(): MutableSet<Chat> {
        return this.chats
    }
}