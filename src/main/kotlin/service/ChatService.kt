package service

import data.Chat
import data.Message
import exception.NoteNotFoundException

object ChatService {

    private var chats =  mutableSetOf<Chat>()
    private var amountIdMessage = 0

    fun clear() {
        chats = mutableSetOf<Chat>()
        amountIdMessage = 0

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

    fun getLastMessagesFromChat(): MutableList<String> {
        val lastMessages = mutableListOf<String>()
        if (chats.isNotEmpty()) chats.forEach {
                chat -> lastMessages.add(chat.messages.last().toString()) }
        if (lastMessages.isNotEmpty()) return lastMessages
        throw  NoteNotFoundException("Чаты не найдены.")
    }

    /*fun getByChatId(chatId: Int, messagesId: Int, amount: Int): MutableList<Message> {
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
        if (getMessages.isNotEmpty()) {
            if (getMessages.size > amount) {
                val getAmountMessages = mutableListOf<Message>()
                for (i in getMessages.size..amount) {
                    getAmountMessages.add(getMessages[i])
                }
            }
            return getMessages
        }
        throw  NoteNotFoundException("Нет сообщений.")
    }*/

    fun getByChatId(chatId: Int, messagesId: Int, count: Int) =
        chats //Возьми чаты
            .find { chat -> chat.id == chatId } //Найди чат с нужным id (может не найтись и вернуть null, поэтому дальше через safe calls и в конце элвис оператор (?:) с указанием что делать при null)
            ?.messages //Возьми сообщения из этого чата
            ?.filter { message -> message.id >= messagesId } //Отфильтруй и оставь те, чей id больше или равен тому что пришёл в параметры
            ?.let { list -> if (list.size > count) list.take(count) else list } //Взять только столько сообщений сколько просят, либо если у нас меньше, то все что есть. В вашем коде нет, но по заданию требуется.
            ?.onEach { message -> message.read = true } //Для каждого полученного таким образом сообщения ставим флаг прочитано
            //?.ifEmpty { throw Exception("Подходящие сообщения не найдены") } //Если список получился пустой - выбрасывай ошибку
            ?.toMutableList() //Эта строка не обязательна, можно вернуть просто лист, но в вашем коде был MutableList
            ?:throw Exception("Чат не найден") //Действие если safe calls не отработали и у нас сейчас null

    fun get(messagesLastId: Int) =
        chats
            .find { chat -> chat.messages.last().id == messagesLastId }
            ?:throw NoteNotFoundException("Нет сообщений.")

    fun createMessage(text: String, outgoing: Boolean): Message {
        if (amountIdMessage == 0 ) {
            amountIdMessage++
            return Message(0, text, outgoing, false)
        }
        return Message(amountIdMessage + 1, text, outgoing,  false)
    }

    fun addMessageInChat(idChat: Int, message: Message) =
        chats
            .find { chat -> chat.id == idChat }
            ?.messages
            ?.add(message)
            ?:throw NoteNotFoundException("Такого чата нет!")

    fun createChat(idChat: Int, newMessage: Message): Chat {
        chats.forEach {chat: Chat ->
            if (chat.id == idChat) {
                throw NoteNotFoundException("Чат уже существует!")
            }
        }
        chats.add(Chat(chats.size, mutableListOf(newMessage)))
        return chats.last()
    }

    fun deleteMessage(idChat: Int, idMessage: Int): Message {
        chats.forEach {chat: Chat ->
            if (chat.id == idChat) {
                chat.messages.forEach { message: Message ->
                    if (message.id == idMessage) {
                        chat.messages.remove(message)
                        return message
                    }
                }
            }
            throw NoteNotFoundException("Такого сообщения нет!")
        }
        throw NoteNotFoundException("Такого чата нет!")
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