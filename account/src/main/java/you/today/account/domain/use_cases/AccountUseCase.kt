package you.today.account.domain.use_cases

import kotlinx.coroutines.flow.Flow
import you.today.api.IGetCourses
import you.today.api.Resource
import you.today.api.models.MyCourse

class AccountUseCase(private val repository: IGetCourses) {
    fun getMyCoursesFromDB(): Flow<Resource<List<MyCourse>>> = repository.getMyCoursesFromDB()
}