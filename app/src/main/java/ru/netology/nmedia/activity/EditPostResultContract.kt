package ru.netology.nmedia.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.adapter.PostViewHolder
import ru.netology.nmedia.dto.Post


class EditPostResultContract : ActivityResultContract< String, String?>(){


    override fun createIntent(context: Context, input: String): Intent {
    return    Intent(context, EditPostActivity::class.java).apply {
            putExtra("post.content", input)
        }
    }

        override fun parseResult(resultCode: Int, intent: Intent?): String? {
            TODO("Not yet implemented")

        }
    }

