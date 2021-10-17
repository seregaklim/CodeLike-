package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.EditPostFragment.Companion.textArg
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel


class FeedFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = PostsAdapter(object : OnInteractionListener {


            override fun onEdit(post: Post) {
                viewModel.edit(post)
                findNavController().navigate(R.id.actionFeedFragmentToEditPostFragment, Bundle().apply { textArg = post.content })
            }
            override fun onAddVideo(post: Post)  {
               viewModel.addVideo(post)
                findNavController().navigate(R.id.actionFeedFragmentToNewVideoFragment,Bundle().apply {textArg=post.video })
            }

            override fun onPlayVideo(post: Post) {
                val intent = Intent().apply {

                    action = Intent.ACTION_VIEW
                    setPackage("com.google.android.youtube")
                    data = Uri.parse("${post.video}")
                }
                startActivity(intent)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
               viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        //Подписка на отдельный пост на id=1
        viewModel.getPost(id=1).observe(viewLifecycleOwner){
            Snackbar.make(binding.root,it.toString(),Snackbar.LENGTH_LONG).show()
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.actionFeedFragmentToNewPostFragment)
        }

        return binding.root
    }
}

