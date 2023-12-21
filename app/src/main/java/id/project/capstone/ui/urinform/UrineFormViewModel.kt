package id.project.capstone.ui.urinform

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.project.capstone.data.repository.ScanUrineRepository
import id.project.capstone.di.Injection
import java.io.File

class UrineFormViewModel(private val repository: ScanUrineRepository) : ViewModel() {
    fun scanUrine(file: File) = repository.scanUrine(file)
}


class UrineFormViewModelFactory(private val repository: ScanUrineRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UrineFormViewModel::class.java) -> {
                UrineFormViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: UrineFormViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: UrineFormViewModelFactory(Injection.provideScanRepository(context))
            }.also { instance = it }
    }
}