package you.today.data.repository

import kotlinx.coroutines.flow.Flow
import you.today.data.model.CourseEntity
import you.today.data.model.MyCoursesEntity

interface ICourses {
    suspend fun insertCoursesItemToDb(coursesItem: List<CourseEntity>)
    suspend fun insertCourse(coursesItem: CourseEntity)
    suspend fun insertMyCourse(coursesItem: MyCoursesEntity)
     fun getCoursesItems(): Flow<List<CourseEntity>>
    fun getFavoritesCoursesItems(): Flow<List<CourseEntity>>
     fun getCoursesItemById(id: Int): Flow<CourseEntity>
    fun getMyCoursesItems(): Flow<List<MyCoursesEntity>>
}