package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author:String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val shareByMe: Boolean = false,
    var likes: Int,
    var share: Int,
    val viewing: Int,
    val viewingByMe: Boolean = false,
)







