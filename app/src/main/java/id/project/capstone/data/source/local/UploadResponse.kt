package id.project.capstone.data.source.local

import com.google.gson.annotations.SerializedName

data class UploadResponse(

    @field:SerializedName("filename")
    val message: String

)

data class ResponseColor(

    @field:SerializedName("filename")
    val filename: String? = null,

    @field:SerializedName("model-prediction-confidence-score")
    val modelPredictionConfidenceScore: Int? = null,

    @field:SerializedName("model-prediction")
    val modelPrediction: String? = null
)
