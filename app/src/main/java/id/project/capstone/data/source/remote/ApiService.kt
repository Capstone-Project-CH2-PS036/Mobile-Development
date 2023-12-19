package id.project.capstone.data.source.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("stories")
    suspend fun scanUrine(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): ResultResponse

}