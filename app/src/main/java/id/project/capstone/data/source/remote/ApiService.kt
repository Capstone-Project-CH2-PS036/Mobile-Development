package id.project.capstone.data.source.remote

import id.project.capstone.data.source.remote.response.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("upload")
    suspend fun scanUrine(
        @Part file: MultipartBody.Part,
    ): Response<UploadResponse>
}
