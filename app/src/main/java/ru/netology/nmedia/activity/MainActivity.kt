
package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))

                viewModel.shareById(post.id)
                startActivity(shareIntent)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }


        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
      viewModel.canselContent(String())
        }

        binding.fab.setOnClickListener {
            newPostLauncher.launch()
        }

        val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
            result ?: return@registerForActivityResult

            viewModel.changeContent(result)
            viewModel.save()


        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            post.content
            editPostLauncher.launch()
        }
    }

}





//
//
//            override fun onRemove(post: Post) {
//                viewModel.removeById(post.id)
//            }
//
//
//        })
//
//        binding.list.adapter = adapter
//        viewModel.data.observe(this) { posts ->
//            adapter.submitList(posts)
//        }
//       //notShowHide(binding.constraintLayoutCansel)
//
//        viewModel.edited.observe(this) { post ->
//            if (post.id == 0L) {
//                return@observe
//            }
//            with (binding.content) {
//                requestFocus()
//                setText(post.content)
//                showHide(binding.constraintLayoutCansel)
//
//                with(binding.editMessage) {
//                    setText(post.content)
//
//                }
//            }
//        }
//
//
//        binding.save.setOnClickListener {
//            with(binding.content) {
//                if (text.isNullOrBlank()) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        context.getString(R.string.error_empty_content),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    return@setOnClickListener
//                }
//
//                viewModel.changeContent(text.toString())
//                viewModel.save()
//                notShowHide(binding.constraintLayoutCansel)
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//            }
//        }
//
//
//        binding.canselButtum.setOnClickListener {
//            with(binding.content) {
//                if (text.isNullOrBlank()) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        context.getString(R.string.error_empty_content),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    return@setOnClickListener
//                }
//                viewModel.canselContentById(id.toLong())
//                notShowHide(binding.constraintLayoutCansel)
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//            }
//        }
//        notShowHide(binding.constraintLayoutCansel)
//    }
//}
//fun notShowHide(view:View) {
//    view.visibility =  View.GONE  //перестает занимать место на экране
//}
//
//fun showHide(view:View) {
//    view.visibility = View.VISIBLE //видимый
//}




//fun showHide(view:View) {
//    view.visibility = if (view.visibility == View.VISIBLE){
//        View.INVISIBLE  //невидимая
//    } else{
//        View.VISIBLE
//
//    }
//}