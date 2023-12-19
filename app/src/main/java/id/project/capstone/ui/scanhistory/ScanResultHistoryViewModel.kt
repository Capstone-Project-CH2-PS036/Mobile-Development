package id.project.capstone.ui.scanhistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.project.capstone.data.repository.ScanResultHistoryRepository
import id.project.capstone.data.source.local.ResultUrineEntity

class ScanResultHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ScanResultHistoryRepository = ScanResultHistoryRepository(application)

    fun getAllResultHistory(): LiveData<List<ResultUrineEntity>> {
        return repository.getAllResult()
    }

}