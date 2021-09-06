package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.Wallsevice

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
}

class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

 val service = Wallsevice()


    class PostViewHolder(
        private val binding: CardPostBinding,
        private val onInteractionListener: OnInteractionListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.apply {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24

                )
                share.setImageResource(
                    if (post.shareByMe) R.drawable.ic_share_24 else R.drawable.ic_share_24
                )

                            shareCount?.text = service.zeroingOutShare(post.share).toString()


                likeCount?.text = service.zeroingOutLikes(post.likes).toString()


                like.setOnClickListener {

                    onInteractionListener.onLike(post)
                }
                share.setOnClickListener {
                    onInteractionListener.onShare(post)

                }

            }
        }
    }

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
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

