package id.project.capstone.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UploadResponse(

    @field:SerializedName("message")
    val message: String,
    @SerializedName("filename") val filename: String,
    @SerializedName("prediction") val urineColor: Prediction,
    @SerializedName("response_message") val diseaseDescription: String

)
data class Prediction(
    @SerializedName("model-prediction")
    val modelPrediction: String,
    @SerializedName("model-prediction-confidence-score")
    val modelPredictionConfidenceScore: Double,
    @SerializedName("filename")
    val predictionFilename: String
)
data class ResponseColor(

    @field:SerializedName("filename")
    val filename: String? = null,

    @field:SerializedName("model-prediction-confidence-score")
    val modelPredictionConfidenceScore: Int? = null,

    @field:SerializedName("model-prediction")
    val modelPrediction: String? = null
)
