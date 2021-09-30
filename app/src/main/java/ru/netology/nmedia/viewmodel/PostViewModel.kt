package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.*
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryFileImpl

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    shareByMe = false,
    likes = 0,
    share = 0,
    viewing = 0,
    video = "https://www.youtube.com/watch?v=WhWc3b3KhnY",
)


class PostViewModel(application: Application) : AndroidViewModel(application) {
    // упрощённый вариант
    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(text: String) {
        edited.value = edited.value?.copy(content = text)

    }
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun likeById(id: Long) = repository.likeById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun shareById(id: Long) = repository.shareById(id)

    fun addVideo(video: String) {
        val text = video.trim()
        if (edited.value?.video == text) {
            return
        }
        edited.value?.copy(video = text)
    }
}





//
//import android.app.Application
//import androidx.lifecycle.*
//import ru.netology.nmedia.dto.Post
//import ru.netology.nmedia.repository.PostRepository
//import ru.netology.nmedia.repository.PostRepositoryFileImpl
//import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl
//
//private val empty = Post(
//    id = 0,
//    content = "",
//    author = "",
//    likedByMe = false,
//    published = "",
//    shareByMe = false,
//    likes = 0,
//    share = 0,
//    viewing = 0,
//    video = "https://www.youtube.com/watch?v=WhWc3b3KhnY",
//    )
//
//class PostViewModel : ViewModel() {
//
//    // упрощённый вариант
//    private val repository: PostRepository = PostRepositoryInMemoryImpl()
//    val data = repository.getAll()
//    val edited = MutableLiveData(empty)
//    class PostViewModel(application: Application) : AndroidViewModel(application) {
//        // упрощённый вариант
//        private val repository: PostRepository = PostRepositoryFileImpl(application)
//        val data = repository.getAll()
//        val edited = MutableLiveData(empty)
//
//
//        fun save() {
//            edited.value?.let {
//                repository.save(it)
//            }
//            edited.value = empty
//        }
//
//        fun edit(result: String) {
//            edited.value = empty
//        }
//
//
//        fun changeContent(content: String) {
//            val text = content.trim()
//            if (edited.value?.content == text) {
//                return
//            }
//            edited.value = edited.value?.copy(content = text)
//        }
//
//        fun likeById(id: Long) = repository.likeById(id)
//        fun removeById(id: Long) = repository.removeById(id)
//        fun shareById(id: Long) = repository.shareById(id)
//
//        fun addVideo(video: String) {
//            val text = video.trim()
//            if (edited.value?.video == text) {
//                return
//            }
//            edited.value = edited.value?.copy(video = text)
//        }
//    }
//}

//
//fun edit(post: Post) {
//    edited.value?.copy(content = )
//    edited.value = post
//    edited.value?.let {
//        repository.save(it.content)
//    }
//}