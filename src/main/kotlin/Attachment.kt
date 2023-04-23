sealed class Attachment (val type: String)

class Photo
class Posted
class Video
class Audio
class Doc

data class PhotoAttachment(val photo: Photo) : Attachment("photo")
data class PostedAttachment(val posted: Posted) : Attachment("posted")
data class VideoAttachment(val video: Video) : Attachment("video")
data class AudioAttachment(val audio: Audio) : Attachment("audio")
data class DocAttachment(val doc: Doc) : Attachment("doc")