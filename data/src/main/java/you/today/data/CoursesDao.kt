package you.today.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import you.today.data.model.CourseEntity

@Dao
interface CoursesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coursesInfo: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg coursesInfo: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coursesInfo: List<CourseEntity>)

    @Update
    suspend fun update(coursesInfo: CourseEntity)

    @Update
    fun update(vararg coursesInfo: CourseEntity)

    @Update
    fun update(coursesInfo: List<CourseEntity>)

    @Delete
    suspend fun delete(coursesInfo: CourseEntity)

    @Delete
    suspend fun delete(vararg coursesInfo: CourseEntity)

    @Delete
    suspend fun delete(coursesInfo: List<CourseEntity>)

    @Query("SELECT * FROM CourseEntity ")
    fun getAll(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM CourseEntity WHERE hasLike = 1")
    fun getFavoritesCoursesItems(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM CourseEntity WHERE id = :id")
     fun getCountryById(id: Int): Flow<CourseEntity>
}