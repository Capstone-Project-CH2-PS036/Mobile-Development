package id.project.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.project.capstone.databinding.ActivityAboutPageBinding

class AboutPage : AppCompatActivity() {

    private lateinit var binding: ActivityAboutPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}