package id.project.capstone.data.source.remote.response

data class ValidationError(
    val loc: List<Any>,
    val msg: String,
    val type: String
)

data class HTTPValidationError(
    val detail: List<ValidationError>
)