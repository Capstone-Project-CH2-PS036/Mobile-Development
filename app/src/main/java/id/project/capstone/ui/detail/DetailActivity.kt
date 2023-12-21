package id.project.capstone.ui.detail

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import id.project.capstone.R
import id.project.capstone.databinding.ActivityDetailBinding
import id.project.capstone.ui.home.MainActivity
import id.project.capstone.ui.result.ResultViewModel
import id.project.capstone.ui.scanhistory.ScanHistoryActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resultViewModel = ViewModelProvider(this)[ResultViewModel::class.java]
        val id = intent.getLongExtra("id",0)
        val color = intent.getStringExtra("color")
        val disease = intent.getStringExtra("disease")
        val time = intent.getLongExtra("time", 0)

        val bgColor = color?.toLowerCase(Locale.ROOT)

        val backgroundColor: Drawable? = when (bgColor) {
            "biru" -> ContextCompat.getDrawable(this, R.color.biru)
            "coklat" -> ContextCompat.getDrawable(this, R.color.coklat)
            "hijau" -> ContextCompat.getDrawable(this, R.color.hijau)
            "hitam" -> ContextCompat.getDrawable(this, R.color.hitam)
            "jingga" -> ContextCompat.getDrawable(this, R.color.jingga)
            "kuning" -> ContextCompat.getDrawable(this, R.color.kuning)
            "merah" -> ContextCompat.getDrawable(this, R.color.merah)
            "putih" -> ContextCompat.getDrawable(this, R.color.putih)
            else -> ContextCompat.getDrawable(this, R.color.kuning)
        }

        binding.toolbar.title = formatTimestamp(time)
        binding.colorTitle.text = color
        binding.colorTitle.background = backgroundColor
        binding.descriptionHistory.text = disease
        binding.colorHistory.text = "Your Urine Color is "+color


        binding.toolbar.setOnClickListener {
            onBackPressed()
        }

        binding.btnDelete.setOnClickListener {
            resultViewModel.deleteResult(id = id)
            val intent = Intent(this, ScanHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }
}