package id.project.capstone.ui.scanhistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.project.capstone.databinding.ActivityMainBinding
import id.project.capstone.databinding.ActivityScanHistoryBinding
import id.project.capstone.ui.adapter.ScanHistoryAdapter
import id.project.capstone.ui.detail.DetailActivity
import id.project.capstone.ui.result.ResultActivity

class ScanHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanHistoryBinding
    private lateinit var historyAdapter: ScanHistoryAdapter
    private lateinit var historyViewModel: ScanResultHistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setOnClickListener {
            onBackPressed()
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        historyAdapter = ScanHistoryAdapter()
        binding.rvHistory.adapter = historyAdapter

        historyViewModel = ViewModelProvider(this)[ScanResultHistoryViewModel::class.java]
        historyViewModel.getAllResultHistory().observe(this) { history ->
            historyAdapter.submitList(history)
            binding.progressbarHistory.visibility = View.GONE
        }

        historyAdapter.setOnItemClickListener { history ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", history.id)
            intent.putExtra("image", history.image)
            intent.putExtra("color", history.color)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}