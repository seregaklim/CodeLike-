
package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter {
            viewModel.likeById(it.id)
            viewModel.shareById(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts
        }
    }
}

//        val service = Wallsevice()
//
//        val viewModel: PostViewModel by viewModels()
//
//        viewModel.data.observe(this) { post ->
//            with(binding) {
//                author.text = post.author
//                published.text = post.published
//                content.text = post.content
//                like.setImageResource(
//                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
//                )
//                likeCount?.text = service.zeroingOutLikes(post.likes).toString()
//
//                share.setImageResource(
//                    if (post.shareByMe) R.drawable.ic_share_24 else R.drawable.ic_share_24
//                )
//                shareCount?.text = service.zeroingOutShare(post.share).toString()
//            }
//        }
//        binding.like.setOnClickListener {
//
//            viewModel.like()
//        }
//        binding.share.setOnClickListener {
//            viewModel.share()
//        }
//
//    }
//}
//



