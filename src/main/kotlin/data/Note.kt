package data

data class Note(
    val id: Int, // Идентификатор заметки
    val title: String, // Заголовок заметки
    val text: String, // Текст заметки
    //val privacy: Int, // Уровень доступа к заметке(0-3)
    val comments: MutableList<Comment>,
    //val commentPrivacy: Int // Уровень доступа к комментированию заметки(0-3)
    //val privacyView: String,
    //val privacyComment:String
)
