package id.project.capstone.ui.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.project.capstone.R
import id.project.capstone.databinding.ActivityResultBinding
import id.project.capstone.ui.home.MainActivity

class ResultActivity : AppCompatActivity() {


    private lateinit var binding: ActivityResultBinding
    private lateinit var resultViewModel: ResultViewModel
    private var currentImageUri: Uri? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultViewModel = ViewModelProvider(this)[ResultViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            navigateToMain()
        }

        val descriptionText = intent.getStringExtra("description")
        currentImageUri = intent.getStringExtra("image")?.let { Uri.parse(it) }
        binding.description.text = descriptionText

        binding.btnSave.setOnClickListener {
            val id: Long = 2
            val image = currentImageUri.toString()
            val color = "Yellow"
            val dataUrine ="The yellow color of urine is usually normal and is caused by a urine pigment called urobilin. However, a very dark yellow or brownish yellow color can indicate dehydration. Make sure to drink enough water."
            if (!isFavorite) {
                isFavorite = true
                binding.btnSave.setImageResource(R.drawable.baseline_bookmark_filled_24)
                resultViewModel.insertResult(id,image,color,dataUrine)
            } else {
                isFavorite = false
                binding.btnSave.setImageResource(R.drawable.baseline_bookmark_border_24)

            }
        }

    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}