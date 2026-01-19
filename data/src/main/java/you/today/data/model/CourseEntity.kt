package you.today.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
