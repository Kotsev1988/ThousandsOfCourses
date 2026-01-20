package you.today.detail.presentation

sealed class DetailAppState {
    data class OnSuccessContents(
        val title: String,
        val content: String,
        val publishedDate: String,
        val hasLike: Boolean,
        val rate: String
    ): DetailAppState()

    data class Error(val error: String) : DetailAppState()
    object Loading : DetailAppState()
}