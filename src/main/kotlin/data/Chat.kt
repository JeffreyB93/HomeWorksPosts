package data

data class Chat(
    val id: Int,
    val messages: MutableList<Message>,
)

data class Message(
    val id: Int,
    val text: String,
<<<<<<< HEAD
    val outgoing: Boolean,
=======
>>>>>>> origin/main
    var read: Boolean
)