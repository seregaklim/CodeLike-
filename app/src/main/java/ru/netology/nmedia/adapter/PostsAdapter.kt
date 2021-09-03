package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.Wallsevice

typealias OnLikeListener = (post: Post) -> Unit

class PostsAdapter(private val onLikeListener: OnLikeListener) :
    RecyclerView.Adapter<PostViewHolder>() {
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}

val service = Wallsevice()

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_liked_24)
            }
            like.setOnClickListener {
                onLikeListener(post)
                likeCount?.text = service.zeroingOutLikes(post.likes).toString()
            }

            if (post.shareByMe) {
                share.setImageResource(R.drawable.ic_share_24)
            }
            share.setOnClickListener {
                onLikeListener(post)
                shareCount?.text = service.zeroingOutShare(post.share).toString()
            }

        }
    }
}



//
//if (post.shareByMe) {
//    share.setImageResource(R.drawable.ic_share_24)
//    if (post.shareByMe) post.share++ else post.share--
//    shareCount?.text = service.zeroingOutShare(post.share).toString()
//
//}
//share.setOnClickListener {
//    onLikeListener(post)
//}









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

