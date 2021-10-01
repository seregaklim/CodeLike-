package ru.netology.nmedia.activity

import NewVideoResultContract
import android.content.Intent
import android.net.Uri
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        run {
//            val preferences = getPreferences(Context.MODE_PRIVATE)
//            preferences.edit().apply {
//                putString("key", "value") // putX
//                commit() // commit - синхронно, apply - асинхронно
//            }
//        }
//
//        run {
//            getPreferences(Context.MODE_PRIVATE)
//                .getString("key", "no value")?.let {
//                    Snackbar.make(binding.root, it, BaseTransientBottomBar.LENGTH_INDEFINITE)
//                        .show()
//                }
//        }

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter(object : OnInteractionListener {


            val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
                result ?: return@registerForActivityResult
                viewModel.changeContent(result)
                viewModel.edit()
            }

            override fun onEdit (post: Post) {
                editPostLauncher.launch(post.content)

            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
            override fun onPlayVideo(post: Post) {

                val intent = Intent().apply {

                    action = Intent.ACTION_VIEW
                    intent.setPackage("com.google.android.youtube")
                    intent.data = Uri.parse("${post.video}")
                }
                startActivity(intent)
            }

            val newVideoLauncher = registerForActivityResult(NewVideoResultContract()) { result ->
                result ?: return@registerForActivityResult
                viewModel.addVideo()
                viewModel.changeContent(result)
            }

            override fun onAddVideo(post: Post){
                newVideoLauncher.launch()
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
        }

        binding.fab.setOnClickListener {
            newPostLauncher.launch()
        }


    }
}



//
//        val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
//            result ?: return@registerForActivityResult
//            viewModel.save()
//            viewModel.changeContent(result)
//
//        }
//
//        viewModel.edited.observe(this) { post ->
//            if (post.id == 0L) {
//                return@observe
//            }
//            editPostLauncher.launch(post.content)
//        }


//        val intent = Intent(Intent.ACTION_VIEW)
//
//        intent.setPackage("com.google.android.youtube")
//        intent.data = Uri.parse("https://www.youtube.com/watch?v=WhWc3b3KhnY")
//
//        startActivity(intent)





//video.setVideoURI(Uri.parse("url"))
//videoView.requestFocus()
//videoView.start()

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


