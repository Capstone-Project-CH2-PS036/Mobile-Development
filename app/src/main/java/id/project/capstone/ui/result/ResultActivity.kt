package id.project.capstone.ui.result

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import id.project.capstone.R
import id.project.capstone.databinding.ActivityResultBinding
import id.project.capstone.ui.home.MainActivity
import java.util.Locale

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
        val color = intent.getStringExtra("color")
        val disease = intent.getStringExtra("disease")

        val bgColor = color?.toLowerCase(Locale.ROOT)

        val backgroundColor: Drawable? = when (bgColor) {
            "biru" -> ContextCompat.getDrawable(this, R.color.biru)
            "coklat" -> ContextCompat.getDrawable(this, R.color.coklat)
            "hijau" -> ContextCompat.getDrawable(this, R.color.hijau)
            "hitam" -> ContextCompat.getDrawable(this, R.color.black_in)
            "jingga" -> ContextCompat.getDrawable(this, R.color.jingga)
            "kuning" -> ContextCompat.getDrawable(this, R.color.kuning)
            "merah" -> ContextCompat.getDrawable(this, R.color.merah)
            "putih" -> ContextCompat.getDrawable(this, R.color.white_in)
            else -> ContextCompat.getDrawable(this, R.color.kuning)
        }


        binding.colorTitle.text = color
        binding.colorTitle.background = backgroundColor
        binding.tvColor.text = "Your Urine Color is "+color
        binding.tvDisease.text = disease
        binding.description.text = descriptionText

        binding.btnSave.setOnClickListener {
            val image = currentImageUri.toString()
            if (!isFavorite) {
                isFavorite = true
                binding.btnSave.setImageResource(R.drawable.baseline_bookmark_filled_24)
                color?.let { it1 ->
                    disease?.let { it2 ->
                        resultViewModel.insertResult(
                            image, it1,
                            it2
                        )
                    }
                }
            }
        }

    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}