import data.*
import exception.NoteNotFoundException
import exception.PostNotFoundException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import service.NoteService
import service.WallService
import java.util.*

class WallServiceTest {

    @BeforeEach
    fun clearBeforeTest() {
        WallService.clear()
        NoteService.clear()
    }

    @Test
    fun add() {
        val service = WallService
        val comments = Comments(1, canPost = true, groupsCanPost = false, canClose = true, canOpen = true)
        val repost = Repost(12, userReposted = true)
        val likes = Likes(12, userLikes = true, canLike = false, canPublish = true)

        val photo = Photo()
        val video = Video()
        val photoAttachment: Attachment = PhotoAttachment(photo)
        val videoAttachment: Attachment = VideoAttachment(video)

        val attachments: ArrayList<Attachment> = ArrayList()
        attachments.addAll(listOf(photoAttachment, videoAttachment))

        val post = Post(1, 2, 3, 4, null, "Первый пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        assertEquals(post, service.add(post))
    }

    @Test
    fun updateTrue() {
        val service = WallService
        val comments = Comments(1, canPost = true, groupsCanPost = false, canClose = true, canOpen = true)
        val repost = Repost(12, userReposted = true)
        val likes = Likes(12, userLikes = true, canLike = false, canPublish = true)

        val photo = Photo()
        val posted = Posted()
        val video = Video()
        val audio = Audio()
        val doc = Doc()

        val photoAttachment: Attachment = PhotoAttachment(photo)
        val postedAttachment: Attachment = PostedAttachment(posted)
        val videoAttachment: Attachment = VideoAttachment(video)
        val audioAttachment: Attachment = AudioAttachment(audio)
        val docAttachment: Attachment = DocAttachment(doc)

        val attachments1: ArrayList<Attachment> = ArrayList()
        attachments1.addAll(listOf(photoAttachment, postedAttachment))
        val attachments2: ArrayList<Attachment> = ArrayList()
        attachments2.addAll(listOf(postedAttachment, videoAttachment))
        val attachments3: ArrayList<Attachment> = ArrayList()
        attachments3.addAll(listOf(audioAttachment, docAttachment))


        val postOne = Post(1, 2, 3, 4, null, "Первый пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments1,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        val postTwo = Post(1, 2, 3, 4, null, "Второй пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments2,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        val postThree = Post(1, 2, 3, 4, null, "Третий пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments3,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        val postUpdate = Post(1, 2, 3, 4, null, "Update пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments3,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        service.add(postOne)
        service.add(postTwo)
        service.add(postThree)

        assertTrue(service.update(postUpdate))
    }

    @Test
    fun updateFalse() {
        val service = WallService
        val comments = Comments(1, canPost = true, groupsCanPost = false, canClose = true, canOpen = true)
        val repost = Repost(12, userReposted = true)
        val likes = Likes(12, userLikes = true, canLike = false, canPublish = true)

        val photo = Photo()
        val posted = Posted()
        val video = Video()
        val audio = Audio()
        val doc = Doc()

        val photoAttachment: Attachment = PhotoAttachment(photo)
        val postedAttachment: Attachment = PostedAttachment(posted)
        val videoAttachment: Attachment = VideoAttachment(video)
        val audioAttachment: Attachment = AudioAttachment(audio)
        val docAttachment: Attachment = DocAttachment(doc)

        val attachments1: ArrayList<Attachment> = ArrayList()
        attachments1.addAll(listOf(photoAttachment, postedAttachment))
        val attachments2: ArrayList<Attachment> = ArrayList()
        attachments2.addAll(listOf(postedAttachment, videoAttachment))
        val attachments3: ArrayList<Attachment> = ArrayList()
        attachments3.addAll(listOf(audioAttachment, docAttachment))


        val postOne = Post(1, 2, 3, 4, null, "Первый пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments1,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        val postTwo = Post(1, 2, 3, 4, null, "Второй пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments2,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        val postThree = Post(1, 2, 3, 4, null, "Третий пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments3,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        val postUpdate = Post(5, 2, 3, 4, null, "Update пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments3,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)

        service.add(postOne)
        service.add(postTwo)
        service.add(postThree)

        assertFalse(service.update(postUpdate))
    }

    @Test
    fun createComment() {
        val service = WallService
        val comments = Comments(1, canPost = true, groupsCanPost = false, canClose = true, canOpen = true)
        val repost = Repost(12, userReposted = true)
        val likes = Likes(12, userLikes = true, canLike = false, canPublish = true)
        val comment1 = Comment(1, "qwe")
        val photo = Photo()
        val posted = Posted()
        val photoAttachment: Attachment = PhotoAttachment(photo)
        val postedAttachment: Attachment = PostedAttachment(posted)
        val attachments1: ArrayList<Attachment> = ArrayList()
        attachments1.addAll(listOf(photoAttachment, postedAttachment))

        val postOne = Post(1, 2, 3, 4, null, "Первый пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments1,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)
        service.add(postOne)

        assertEquals(comment1, service.createComment(1, comment1))
    }


    @Test
    fun shouldThrow() {
        val service = WallService
        val comment1 = Comment(1, "qwe")
        assertThrows(PostNotFoundException::class.java) {
            service.createComment(2, comment1)
        }
    }

    @Test
    fun reportComment() {
        val service = WallService
        val comments = Comments(1, canPost = true, groupsCanPost = false, canClose = true, canOpen = true)
        val repost = Repost(12, userReposted = true)
        val likes = Likes(12, userLikes = true, canLike = false, canPublish = true)
        val comment1 = Comment(1, "qwe")
        val reportComment1 = ReportComment(1, 1, 1)
        val photo = Photo()
        val posted = Posted()
        val photoAttachment: Attachment = PhotoAttachment(photo)
        val postedAttachment: Attachment = PostedAttachment(posted)
        val attachments1: ArrayList<Attachment> = ArrayList()
        attachments1.addAll(listOf(photoAttachment, postedAttachment))

        val postOne = Post(1, 2, 3, 4, null, "Первый пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments1,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)
        service.add(postOne)

        service.createComment(1, comment1)
        assertEquals(reportComment1, service.reportComment(1, reportComment1, 1))
    }


    @Test
    fun shouldThrowReportComment() {
        val service = WallService
        val comments = Comments(1, canPost = true, groupsCanPost = false, canClose = true, canOpen = true)
        val repost = Repost(12, userReposted = true)
        val likes = Likes(12, userLikes = true, canLike = false, canPublish = true)
        val comment1 = Comment(1, "qwe")
        val reportComment1 = ReportComment(1, 1, 1)
        val photo = Photo()
        val posted = Posted()
        val photoAttachment: Attachment = PhotoAttachment(photo)
        val postedAttachment: Attachment = PostedAttachment(posted)
        val attachments1: ArrayList<Attachment> = ArrayList()
        attachments1.addAll(listOf(photoAttachment, postedAttachment))
        val postOne = Post(1, 2, 3, 4, null, "Первый пост",
            2, 1,  true, comments, "qwe", likes, repost, 12,
            "qwe", "asd","geo", attachments1,15, "tyu", canPin = true,
            canDelete = true, canEdit = false, isPinned = false, markedAsAds = false, isFavorite = false,
            12)
        service.add(postOne)
        service.createComment(1, comment1)

        assertThrows(PostNotFoundException::class.java) {
            service.reportComment(2, reportComment1, 1)
        }
    }

    @Test
    fun addNoteService() {
        val noteService =  NoteService
        val note = Note(1, "Заголовок", "Текст", mutableListOf())
        assertEquals(note, noteService.add(note))
    }

    @Test
    fun createCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        assertEquals(comment, noteService.createComment(1, comment))
    }

    @Test
    fun exceptionCreateCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        assertThrows(NoteNotFoundException::class.java) {
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
        assertEquals(1, noteService.delete(1))
    }

    @Test
    fun exceptionDeleteNoteService() {
        val noteService =  NoteService
        val note1 = Note(1, "Заголовок", "Текст", mutableListOf())
        val note2 = Note(2, "Заголовок", "Текст", mutableListOf())
        noteService.add(note1)
        noteService.add(note2)
        assertThrows(NoteNotFoundException::class.java) {
            noteService.delete(3)
        }
    }

    @Test
    fun deleteCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        assertEquals(1, noteService.deleteComment(1))
    }

    @Test
    fun exceptionDeleteCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        assertThrows(NoteNotFoundException::class.java) {
            noteService.deleteComment(2)
        }
    }

    @Test
    fun editNoteService() {
        val noteService =  NoteService
        val note = Note(1, "Заголовок", "Текст", mutableListOf())
        noteService.add(note)
        assertEquals(1, noteService.edit(1, "Заголовок_2", "Текст_2"))
    }

    @Test
    fun exceptionEditNoteService() {
        val noteService =  NoteService
        val note = Note(1, "Заголовок", "Текст", mutableListOf())
        noteService.add(note)
        assertThrows(NoteNotFoundException::class.java) {
            noteService.edit(3, "Заголовок_2", "Текст_2")
        }
    }

    @Test
    fun editCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        assertEquals(1, noteService.editComment(1, 1, "Текст_qwe"))
    }

    @Test
    fun exceptionEditCommentNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        assertThrows(NoteNotFoundException::class.java) {
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
        assertEquals(mutableListOf(note1, note2), noteService.get(1, 2))
    }

    @Test
    fun getByIdNoteService() {
        val noteService =  NoteService
        val comment = Comment(1, "qwe")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment))
        noteService.add(note)
        assertEquals(note, noteService.getById(1))
    }

    @Test
    fun getCommentsNoteService() {
        val noteService =  NoteService
        val comment1 = Comment(1, "qwe")
        val comment2 = Comment(2, "asd")
        val note = Note(1, "Заголовок", "Текст", mutableListOf(comment1, comment2))

        noteService.add(note)
        assertEquals(mutableListOf(comment1, comment2), noteService.getComments(1))
    }
}