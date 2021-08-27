package ru.netology.nmedia.dto

data class Post(
    var id: Int,
    var author:String,
    var content: String,
    var published: String,
    var likedByMe: Boolean = false,
    var shareByMe: Boolean = false,
    var likes: Int,
    var share: Int,
    var viewing: Int,
    var viewingByMe: Boolean = false,
)







