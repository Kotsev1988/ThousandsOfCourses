package you.today.favorites.domain.use_cases

import kotlinx.coroutines.flow.Flow
import you.today.api.IGetCourses
import you.today.api.Resource
import you.today.api.models.Course

class FavoritesUseCase(private val repository: IGetCourses) {
    fun getFavoritesFromDB(): Flow<Resource<List<Course>>> = repository.getFavoritesCoursesFromDB()

    suspend fun addToFavorite(course: Course?){
        repository.addToFavorite(course)
    }
}