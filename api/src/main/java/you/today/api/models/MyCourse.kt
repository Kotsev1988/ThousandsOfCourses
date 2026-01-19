package you.today.api.models

data class MyCourse(
    val id: Int,
    val title: String,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
    val progress: Int =0,
    val lessons: Int
)
