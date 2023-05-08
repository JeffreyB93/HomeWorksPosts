package data

data class Comment(
    override val id: Int,
    val message: String,
) : Identifiable

interface Identifiable {
    val id: Int
}