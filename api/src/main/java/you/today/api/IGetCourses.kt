package you.today.api

import kotlinx.coroutines.flow.Flow
import you.today.api.models.Course
import you.today.api.models.CourseResponse
import you.today.api.models.MyCourse

interface IGetCourses {
    suspend fun getCourses()
    fun getCoursesFromDB(): Flow<Resource<List<Course>>>
    fun getFavoritesCoursesFromDB(): Flow<Resource<List<Course>>>
    fun getCourseById(id: Int): Flow<Resource<Course>>
    suspend fun addToFavorite(course: Course?)
    suspend fun starThisCourse(course: Course?)
    fun getMyCoursesFromDB(): Flow<Resource<List<MyCourse>>>
}