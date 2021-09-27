package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author:String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val shareByMe: Boolean = false,
    var likes: Long,
    var share: Long,
    val viewing: Long,
   val video:String,

    )



