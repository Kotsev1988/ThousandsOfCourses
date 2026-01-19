package you.today.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import you.today.data.model.MyCoursesEntity

@Dao
interface MyCoursesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coursesInfo: MyCoursesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg coursesInfo: MyCoursesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coursesInfo: List<MyCoursesEntity>)

    @Update
    suspend fun update(coursesInfo: MyCoursesEntity)

    @Update
    fun update(vararg coursesInfo: MyCoursesEntity)

    @Update
    fun update(coursesInfo: List<MyCoursesEntity>)

    @Delete
    suspend fun delete(coursesInfo: MyCoursesEntity)

    @Delete
    suspend fun delete(vararg coursesInfo: MyCoursesEntity)

    @Delete
    suspend fun delete(coursesInfo: List<MyCoursesEntity>)

    @Query("SELECT * FROM MyCoursesEntity ")
    fun getAll(): Flow<List<MyCoursesEntity>>

}