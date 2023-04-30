package service

import data.Comment
import data.Note
import exception.NoteNotFoundException

object NoteService {

    private var notes = mutableSetOf<Note>()

    fun clear() {
        notes = mutableSetOf()
    }

    fun add(note: Note):Note {
        if (notes.isEmpty()) {
            notes += note.copy(id = 1)
            return notes.last()
        } else {
            var i = 1
            while (thereIs(notes.last().id + i)) i++
            notes += note.copy(id = notes.last().id + i)
            return notes.last()
        }
    }

    private fun thereIs (test: Int): Boolean {
        for (note in notes) {
            if (note.id == test) return true
        }
        return false
    }

    fun createComment(noteId: Int, comment: Comment): Comment {
        for (note in notes) {
            if (noteId == note.id) {
                note.comments += comment
                return note.comments.last()
            }
        }
        throw NoteNotFoundException("Нет такого noteId = $noteId")
    }

    fun delete(noteId: Int): Int {
        for (note in notes) {
            if (note.id == noteId) {
                notes.remove(note)
                return 1
            }
        }
        throw  NoteNotFoundException("Заметка не найдена")
    }

    fun deleteComment(commentId: Int): Int {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.id == commentId) {
                    note.comments.remove(comment)
                    return 1
                }
            }
        }
        throw  NoteNotFoundException("Комментарий не найдена")
    }

    fun edit(noteId: Int, title: String, text: String): Int {
        for (note in notes) {
            if (note.id == noteId) {
                notes.remove(note)
                notes += note.copy(
                    id = noteId,
                    title = title,
                    text = text,
                    comments = note.comments)
                return 1
            }
        }
        throw  NoteNotFoundException("Нет такого note")
    }

    fun editComment(noteId: Int, commentId: Int, message: String): Int {
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId) {
                        note.comments.remove(comment)
                        note.comments += comment.copy(id = noteId, message = message)
                        return 1
                    }
                }
            }
        }
        throw  NoteNotFoundException("Нет такого comment")
    }

    fun <A>get(vararg noteIdArg: Int, list: List<A>): MutableList<A> {
        val getList = mutableListOf<A>()
        for (noteId in noteIdArg) {
            for (a in list) {
                if (a is Note) {
                    if (noteId == a.id) {
                        getList.add(a)
                    }
                }
            }
        }
        if (getList.isNotEmpty()) {
            return getList
        }
        throw  NoteNotFoundException("Нет такого note")
    }

    fun getById(noteId: Int): Note{
        for (note in notes) {
            if (note.id == noteId) {
                return note
            }
        }
        throw  NoteNotFoundException("Нет такого note")
    }

    fun getComments(noteId: Int): MutableList<Comment> {
        for (note in notes) {
            if (note.id == noteId) {
                return note.comments
            }
        }
        throw  NoteNotFoundException("Нет такого note")
    }
}