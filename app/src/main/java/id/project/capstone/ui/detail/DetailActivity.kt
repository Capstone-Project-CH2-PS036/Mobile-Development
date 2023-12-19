package id.project.capstone.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.project.capstone.R
import id.project.capstone.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }


        val id = intent.getIntExtra("id",0)
        val image = intent.getStringExtra("image")
        val color = intent.getStringExtra("color")
    }
}