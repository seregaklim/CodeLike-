package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.Wallsevice


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      val service= Wallsevice()


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            shareByMe = false,
            likes = 0,
            share = 0,
            viewing = 0,
            viewingByMe = false
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            menu.setOnClickListener {
                Log.d("stuff", "menu")
            }

            like?.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount?.text = service.zeroingOutLikes(post.likes).toString()
            }

            share?.setOnClickListener {
                Log.d("stuff", "share")
                post.shareByMe = !post.shareByMe
                share.setImageResource(
                    if (post.shareByMe) R.drawable.ic_share_24 else R.drawable.ic_share_24)

                if (post.shareByMe) post.share++ else post.share++

                shareCount?.text = service.zeroingOutShare(post.share).toString()
            }
        }
    }
}


//D/stuff: stuff
//D/stuff: stuff
//D/stuff: stuff
//D/stuff: avatar
//D/stuff: menu
//D/stuff: stuff
//D/stuff: share
//D/stuff: like
//D/stuff: stuff
