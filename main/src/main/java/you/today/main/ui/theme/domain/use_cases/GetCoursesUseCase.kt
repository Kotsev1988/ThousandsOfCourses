package you.today.main.ui.theme.domain.use_cases

import kotlinx.coroutines.flow.Flow
import you.today.api.IGetCourses
import you.today.api.Resource
import you.today.api.models.Course

class GetCoursesUseCase (
    private val repository: IGetCourses
){
    suspend operator fun invoke()= repository.getCourses()

    fun getFromDB(): Flow<Resource<List<Course>>> = repository.getCoursesFromDB()

    suspend fun addToFavorite(course: Course?){
        repository.addToFavorite(course)
    }
}