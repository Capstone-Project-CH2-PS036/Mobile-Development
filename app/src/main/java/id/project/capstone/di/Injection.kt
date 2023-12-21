package id.project.capstone.di

import android.content.Context
import id.project.capstone.data.repository.ScanUrineRepository
import id.project.capstone.data.source.remote.ApiConfig
import kotlinx.coroutines.runBlocking

object Injection {

    fun provideScanRepository(context: Context): ScanUrineRepository {

        val apiService = ApiConfig.getApiService()
        return ScanUrineRepository(apiService)
    }


}