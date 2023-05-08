import data.Chat
import data.Message
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.ChatService

class ChatServiceTest {

    @BeforeEach
    fun clearBeforeTest() {
        ChatService.clear()
    }

    @Test
    fun createMessage() {
        val chatService =  ChatService
        val message = chatService.createMessage("Первое сообщение.", true)
        Assertions.assertEquals("Первое сообщение.", message.text);
    }

    @Test
    fun addMessageInChat() {
        val chatService =  ChatService
        val message0 = chatService.createMessage("Первое сообщение.", true)
        val chat = chatService.createChat(0, message0)
        val message1 = chatService.createMessage("Первое сообщение.", true)
        val addMessageInChat = chatService.addMessageInChat(0,  message1)

        Assertions.assertEquals(true, addMessageInChat);
    }

    @Test
    fun createChat() {
        val chatService =  ChatService
        val message = chatService.createMessage( "Первое сообщение.", true)
        val chat = chatService.createChat(0, message)
        Assertions.assertEquals(Chat(0, mutableListOf(message)), chat)
    }

    @Test
    fun getLastMessagesFromChat() {
        val chatService =  ChatService
        val message = chatService.createMessage("Первое сообщение.", true)
        chatService.createChat(0, message)
        val lastMessages = chatService.getLastMessagesFromChat()
        Assertions.assertEquals(mutableListOf(message.toString()), lastMessages)
    }

    @Test
    fun getByChatId() {
        val chatService =  ChatService
        val message = chatService.createMessage("Первое сообщение.", true)
        chatService.createChat(0, message)
        val messages = chatService.getByChatId(0, 0, 1)
        Assertions.assertEquals(mutableListOf(message), messages)
    }

    @Test
    fun get() {
        val chatService =  ChatService
        val message = chatService.createMessage("Первое сообщение.", true)
        val chat = chatService.createChat(0, message)
        Assertions.assertEquals(chat, chatService.get(0))
    }

    @Test
    fun deleteChat() {
        val chatService =  ChatService
        val message = chatService.createMessage("Первое сообщение.", true)
        val chat = chatService.createChat(0, message)
        Assertions.assertEquals(chat, chatService.deleteChat(0))
    }

    @Test
    fun deleteMessage() {
        val chatService =  ChatService
        val message = chatService.createMessage("Первое сообщение.", true)
        chatService.createChat(0, message)
        Assertions.assertEquals(message, chatService.deleteMessage(0, 0))
    }

    @Test
    fun getAllChats() {
        val chatService =  ChatService
        val message = chatService.createMessage("Первое сообщение.", true)
        val chat = chatService.createChat(0, message)
        Assertions.assertEquals(mutableSetOf(chat), chatService.getAllChats())
    }
}