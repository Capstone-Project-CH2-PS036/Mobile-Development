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
