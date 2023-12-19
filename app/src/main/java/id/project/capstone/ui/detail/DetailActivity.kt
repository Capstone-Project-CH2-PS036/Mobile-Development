package id.project.capstone.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.project.capstone.R
import id.project.capstone.databinding.ActivityDetailBinding
import id.project.capstone.databinding.ActivityScanHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val id = intent.getLongExtra("id",0)
        val color = intent.getStringExtra("color")
        val desc = intent.getStringExtra("description")
        val time = intent.getLongExtra("time",0)


        val formattedTimestamp = formatTimestamp(time)

        supportActionBar?.title = formattedTimestamp

        binding.colorHistory.text = "Your Urine is "+ color
        binding.descriptionHistory.text = desc
        binding.toolbar.title = formattedTimestamp
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }
}