package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author:String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val sharedByMe: Boolean = false,
    var likes: Int,
    var share: Int,
//    val viewing: Int,
    val video: String,

    )



