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
    private val scanUrineRepository: ScanUrineRepository = ScanUrineRepository(ApiConfig.getApiService())
    fun getResultHistory(score: Int?, classes: Int?) = scanUrineRepository.getResultColor(score, classes)

    fun insertResult(id: Long, image: String, color: String, dataUrine: String) {
        viewModelScope.launch {
            val history = ResultUrineEntity(id, image, color, dataUrine)
            repository.insertResult(history)
        }
    }

    fun deleteResult(id: Long, image: String, color: String,dataUrine: String) {
        viewModelScope.launch {
            val history = ResultUrineEntity(id, image, color,dataUrine)
            repository.deleteResult(history)
        }
    }


}