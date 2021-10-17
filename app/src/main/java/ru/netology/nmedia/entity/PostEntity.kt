package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author:String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    var likes: Int,
    var share: Int ,
    val viewing: Int,
    val video: String

    ) {
    fun toDto() = Post(id, author, content, published, likedByMe, likes, share, viewing,
        video)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(dto.id, dto.author, dto.content, dto.published, dto.likedByMe, dto.likes,
            dto.share, dto.viewing, dto.video)

    }
}
