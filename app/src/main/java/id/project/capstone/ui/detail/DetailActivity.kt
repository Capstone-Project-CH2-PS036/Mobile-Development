package id.project.capstone.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.project.capstone.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val id = intent.getIntExtra("id",0)
        val image = intent.getStringExtra("image")
        val color = intent.getStringExtra("color")
    }
}