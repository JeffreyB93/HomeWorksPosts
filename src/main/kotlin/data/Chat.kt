package data

data class Chat(
    val id: Int,
    val messages: MutableList<Message>,
)

data class Message(
    val id: Int,
    val text: String,
    var read: Boolean
)