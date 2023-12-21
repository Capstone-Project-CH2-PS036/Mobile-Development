package id.project.capstone.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.google.gson.Gson
import id.project.capstone.data.source.MyResult
import id.project.capstone.data.source.remote.ApiService
import id.project.capstone.data.source.remote.response.ResponseColor
import id.project.capstone.data.source.remote.response.UploadResponse
import id.project.capstone.data.source.remote.response.ValidationError
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class ScanUrineRepository(private val apiService: ApiService) {

    fun scanUrine(imageFile: File) = liveData {
        emit(MyResult.Loading)
        try {
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "file",
                imageFile.name,
                requestImageFile
            )

            Log.d("ApiConfig", "Sending HTTP request...")
            val successResponse = apiService.scanUrine(multipartBody)
            val urineColor = successResponse.body()?.urineColor?.modelPrediction
            val diseaseDescription = successResponse.body()?.diseaseDescription
            Log.d("ApiConfig", "HTTP request successful")
            Log.d("ApiConfig", successResponse.toString())
            Log.d("ApiConfig", urineColor.toString())
            diseaseDescription?.let { Log.d("ApiConfig", it) }
            emit(MyResult.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, UploadResponse::class.java)
            emit(errorResponse?.message.let { it?.let { it1 -> MyResult.Error(it1) } })
        }
    }

    fun getResultColor(score: Int?, classes: Int?) = liveData {
        emit(MyResult.Loading)
        try {
            val successResponse = apiService.getResultColor(score, classes)

            emit(MyResult.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ValidationError::class.java)
            emit(MyResult.Error("Unknown error"))
              emit(errorResponse.msg.let { MyResult.Error(it) })
        } catch (e: Exception) {
            emit(MyResult.Error("Something went wrong"))
        }
    }
}