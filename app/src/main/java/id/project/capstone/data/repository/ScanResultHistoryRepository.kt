package id.project.capstone.data.repository

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import id.project.capstone.data.source.local.LocalDatabase
import id.project.capstone.data.source.local.ResultDao
import id.project.capstone.data.source.local.ResultUrineEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ScanResultHistoryRepository(application: Application) {


    private val favoriteUserDao: ResultDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = LocalDatabase.getInstance(application)
            favoriteUserDao = db.resultDao()
    }

    fun insertResult(favoriteUser: ResultUrineEntity) {
        executorService.execute {
            try {
                favoriteUserDao.insertResult(favoriteUser)
                Log.d(ContentValues.TAG, "Insert Success")
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Insert Error: ${e.message}")
            }
        }
    }

    fun deleteResult(favorite: ResultUrineEntity) {
        executorService.execute {
            try {
                favoriteUserDao.removeResult(favorite)
                Log.d(ContentValues.TAG, "Delete Success")
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Delete Error: ${e.message}")
            }
        }
    }

    fun getAllResult(): LiveData<List<ResultUrineEntity>> {
        return favoriteUserDao.getAllResult()
    }


}