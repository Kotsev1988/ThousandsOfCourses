package you.today.detail.domain

import kotlinx.coroutines.flow.Flow
import you.today.api.IGetCourses
import you.today.api.Resource
import you.today.api.models.Course

class GetCourseDetailUseCase(
    private val repository: IGetCourses
) {
    operator fun invoke(id: Int): Flow<Resource<Course>> {
        return repository.getCourseById(id)
    }

    suspend fun addToFavorite(course: Course?){
           repository.addToFavorite(course)
    }

    suspend fun startThisCourse(course: Course?){
        repository.starThisCourse(course)
    }
}