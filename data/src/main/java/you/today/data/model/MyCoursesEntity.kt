package you.today.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyCoursesEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
    val progress: Int =0,
    val lessons: Int
)
