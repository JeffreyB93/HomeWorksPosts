import data.Comment
import data.Note
import exception.NoteNotFoundException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.NoteService

class NoteServiceTest {

    @BeforeEach
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addNoteService() {
        val noteService =  NoteService
        val note = Note(1, "Заголовок", "Текст", mutableListOf())
        Assertions.assertEquals(note, noteService.add(note))
    }

    @Test
    fun createCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        Assertions.assertEquals(comment, noteService.createComment(1, comment))
    }

    @Test
    fun exceptionCreateCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.createComment(3, comment)
        }
    }

    @Test
    fun deleteNoteService() {
        val noteService =  NoteService
        val note1 = Note(1, "Заголовок", "Текст", mutableListOf())
        val note2 = Note(2, "Заголовок", "Текст", mutableListOf())
        noteService.add(note1)
        noteService.add(note2)
        Assertions.assertEquals(1, noteService.delete(1))
    }

    @Test
    fun exceptionDeleteNoteService() {
        val noteService =  NoteService
        val note1 = Note(1, "Заголовок", "Текст", mutableListOf())
        val note2 = Note(2, "Заголовок", "Текст", mutableListOf())
        noteService.add(note1)
        noteService.add(note2)
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.delete(3)
        }
    }

    @Test
    fun deleteCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        Assertions.assertEquals(1, noteService.deleteComment(1))
    }

    @Test
    fun exceptionDeleteCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.deleteComment(2)
        }
    }

    @Test
    fun editNoteService() {
        val noteService =  NoteService
        val note = Note(1, "Заголовок", "Текст", mutableListOf())
        noteService.add(note)
        Assertions.assertEquals(1, noteService.edit(1, "Заголовок_2", "Текст_2"))
    }

    @Test
    fun exceptionEditNoteService() {
        val noteService =  NoteService
        val note = Note(1, "Заголовок", "Текст", mutableListOf())
        noteService.add(note)
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.edit(3, "Заголовок_2", "Текст_2")
        }
    }

    @Test
    fun editCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        Assertions.assertEquals(1, noteService.editComment(1, 1, "Текст_qwe"))
    }

    @Test
    fun exceptionEditCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        Assertions.assertThrows(NoteNotFoundException::class.java) {
            noteService.editComment(2, 1, "Текст_qwe")
        }
    }

    @Test
    fun getNoteService() {
        val noteService =  NoteService
        val comment1 = Comment(1, "qwe1")
        val comment2 = Comment(2, "qwe2")
        val note1 = Note(1, "Заголовок_1", "Текст_1", mutableListOf(comment1))
        val note2 = Note(2, "Заголовок_2", "Текст_2", mutableListOf(comment2))
        noteService.add(note1)
        noteService.add(note2)
        val noteList = listOf(note1, note2)
        Assertions.assertEquals(mutableListOf(note1, note2), noteService.get(1, 2, list = noteList))
    }

    @Test
    fun getByIdNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        Assertions.assertEquals(note, noteService.getById(1))
    }

    @Test
    fun getCommentsNoteService() {
        val noteService =  NoteService
        val comment1 = Comment(1, "qwe")
        val comment2 = Comment(2, "asd")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment1, comment2))

        noteService.add(note)
        Assertions.assertEquals(mutableListOf(comment1, comment2), noteService.getComments(1))
    }
}