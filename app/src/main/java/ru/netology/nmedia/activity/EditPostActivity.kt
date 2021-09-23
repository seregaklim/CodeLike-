package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding
import ru.netology.nmedia.util.AndroidUtils

class EditPostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edit.requestFocus()

        binding.canselButtum.setOnClickListener {

            with(binding.edit) {
                if (text.isNullOrBlank()) {
                    canselContentById(id.toLong())

                }
                finish()
            }
            binding.ok.setOnClickListener {
                val intent = Intent()
                if (binding.edit.text.isNullOrBlank()) {
                    setResult(Activity.RESULT_CANCELED, intent)
                } else {
                    val content = binding.edit.text.toString()
                    intent.putExtra(Intent.EXTRA_TEXT, content)
                    setResult(Activity.RESULT_OK, intent)

                }
                finish()
            }
        }}

    private fun canselContentById(toLong: Long) {

    }
}






