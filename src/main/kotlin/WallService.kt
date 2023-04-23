import PostNotFoundException as PostNotFoundException

object WallService {

    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reportComments = emptyArray<Comment>()

    fun clear() {
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        if (posts.isEmpty()) {
            posts += post.copy(id = 1)
            return posts.last()
        }
        else {
            var i = 1
            while (thereIs(posts.last().id + i)) i++
            posts += post.copy(id = posts.last().id + i)
            return posts.last()
        }
    }

    private fun thereIs (test: Int): Boolean {
        for (post in posts) {
            if (post.id == test) return true
        }
        return false
    }

    fun update(postUpdate: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (postUpdate.id == post.id) {
                posts[index] = postUpdate.copy(id = post.id)
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (postId == post.id) {
                comments += comment
                return comments.last()
            }
        }
        throw  PostNotFoundException("Нет такого postId = $postId")
    }

    fun reportComment(postId: Int, reportComment: Comment, reason: Int): Comment {
        if (reason in 0..6 || reason == 8) {
            for (post in posts) {
                if (postId == post.id) {
                    for (comment in comments) {
                        if (reportComment == comment) {
                            reportComments += reportComment
                            return reportComments.last()
                        }
                    }
                    throw  PostNotFoundException("Нет такого comment")
                }
            }
            throw  PostNotFoundException("Нет такого postId = $postId")
        }
        throw  PostNotFoundException("Нет такой жалобы = $reason")
    }
}