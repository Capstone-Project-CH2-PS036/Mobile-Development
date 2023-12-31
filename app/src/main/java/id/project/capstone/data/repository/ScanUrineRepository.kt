package id.project.capstone.data.repository

import androidx.lifecycle.liveData
import com.google.gson.Gson
import id.project.capstone.data.source.MyResult
import id.project.capstone.data.source.remote.ApiService
import id.project.capstone.data.source.remote.response.UploadResponse
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
            val successResponse = apiService.scanUrine(multipartBody)
            emit(MyResult.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, UploadResponse::class.java)
            emit(errorResponse?.message.let { it?.let { it1 -> MyResult.Error(it1) } })
        }
    }
}