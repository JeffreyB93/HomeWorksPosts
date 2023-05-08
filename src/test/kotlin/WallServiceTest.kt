import data.*
import exception.NoteNotFoundException
import exception.PostNotFoundException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import service.ChatService
import service.NoteService
import service.WallService
import java.util.*

class WallServiceTest {

    @BeforeEach
    fun clearBeforeTest() {
        WallService.clear()
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
}