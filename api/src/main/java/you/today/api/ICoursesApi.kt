package you.today.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import you.today.api.models.CourseResponse

interface ICoursesApi {
    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&e")
    suspend fun getCourses(): CourseResponse
}