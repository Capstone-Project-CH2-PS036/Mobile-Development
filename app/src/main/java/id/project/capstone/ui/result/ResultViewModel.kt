package id.project.capstone.ui.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import id.project.capstone.data.repository.ScanResultHistoryRepository
import id.project.capstone.data.repository.ScanUrineRepository
import id.project.capstone.data.source.local.ResultUrineEntity
import id.project.capstone.data.source.remote.ApiConfig
import kotlinx.coroutines.launch

class ResultViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ScanResultHistoryRepository = ScanResultHistoryRepository(application)


    fun insertResult( image: String, color: String, dataUrine: String) {
        viewModelScope.launch {
            val history = ResultUrineEntity(image = image, color = color, dataUrine = dataUrine)
            repository.insertResult(history)
        }
    }

    fun deleteResult(id: Long) {
        viewModelScope.launch {
            repository.deleteResult(id)
        }
    }
}