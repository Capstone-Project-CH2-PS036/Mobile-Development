package id.project.capstone.ui.scanhistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.project.capstone.databinding.ActivityMainBinding
import id.project.capstone.databinding.ActivityScanHistoryBinding

class ScanHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}