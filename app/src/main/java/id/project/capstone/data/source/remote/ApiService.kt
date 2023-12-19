package id.project.capstone.data.source.remote

import id.project.capstone.data.source.local.ResponseColor
import id.project.capstone.data.source.local.UploadResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @Multipart
    @POST("upload")
    suspend fun scanUrine(
        @Part file: MultipartBody.Part,
    ): UploadResponse

    @GET("predict")
    suspend fun getResultColor(
        @Query("model-prediction-confidence-score") score: Int? = null,
        @Query("model-prediction") classes: Int? = null,
    ): ResponseColor

}
