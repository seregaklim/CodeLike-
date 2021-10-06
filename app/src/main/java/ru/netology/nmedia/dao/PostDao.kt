package ru.netology.nmedia.dao
import java.io.Closeable
import ru.netology.nmedia.dto.Post

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
    fun shareById(id: Long)
    fun edit(post: Post)
    fun addVideo(post:Post)
}