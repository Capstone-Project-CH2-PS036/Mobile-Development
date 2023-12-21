package id.project.capstone.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.project.capstone.databinding.ActivityMainBinding
import id.project.capstone.ui.about.AboutPage
import id.project.capstone.ui.scanhistory.ScanHistoryActivity
import id.project.capstone.ui.urinform.UrineFormActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.scanNow.setOnClickListener {
            val intent = Intent(this@MainActivity, UrineFormActivity::class.java)
            startActivity(intent)
        }

        binding.buttonHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, ScanHistoryActivity::class.java)
            startActivity(intent)
        }

        binding.buttonAbout.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutPage::class.java)
            startActivity(intent)
        }

    }
}