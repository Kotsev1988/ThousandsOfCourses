package you.today.data.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import you.today.api.ICoursesApi
import you.today.api.IGetCourses
import you.today.api.Resource
import you.today.api.models.Course
import you.today.api.models.MyCourse
import you.today.data.model.CourseEntity
import you.today.data.model.MyCoursesEntity
import you.today.data.repository.ICourses

class GetCoursesImpl(
    private val api: ICoursesApi,
    private val countriesDao: ICourses,
) : IGetCourses {

    override suspend fun getCourses() {
        try {
            val courses = api.getCourses()
            val coursesEntity = courses.courses.map {
                CourseEntity(
                    id = it.id,
                    title = it.title,
                    text = it.text,
                    price = it.price,
                    rate = it.rate,
                    startDate = it.startDate,
                    hasLike = it.hasLike,
                    publishDate = it.publishDate
                )
            }
            countriesDao.insertCoursesItemToDb(coursesEntity)
        } catch (e: Exception) {

        }
    }

    override fun getCoursesFromDB(): Flow<Resource<List<Course>>> =
        countriesDao.getCoursesItems()
            .map { entities ->
                Resource.Success(entities.map { entity ->
                    Course(
                        id = entity.id,
                        title = entity.title,
                        text = entity.text,
                        price = entity.price,
                        rate = entity.rate,
                        startDate = entity.startDate,
                        hasLike = entity.hasLike,
                        publishDate = entity.publishDate
                    )
                })
            }.flowOn(Dispatchers.IO)

    override fun getFavoritesCoursesFromDB(): Flow<Resource<List<Course>>> =
        countriesDao.getFavoritesCoursesItems()
            .map { entities ->
                Resource.Success(entities.map { entity ->
                    Course(
                        id = entity.id,
                        title = entity.title,
                        text = entity.text,
                        price = entity.price,
                        rate = entity.rate,
                        startDate = entity.startDate,
                        hasLike = entity.hasLike,
                        publishDate = entity.publishDate
                    )
                })
            }.flowOn(Dispatchers.IO)


    override fun getCourseById(id: Int): Flow<Resource<Course>> {
        return countriesDao.getCoursesItemById(id)
            .map { entity ->
                val course = Course(
                    id = entity.id,
                    title = entity.title,
                    text = entity.text,
                    price = entity.price,
                    rate = entity.rate,
                    startDate = entity.startDate,
                    hasLike = entity.hasLike,
                    publishDate = entity.publishDate
                )
                Resource.Success(course)
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun addToFavorite(course: Course?) = withContext(Dispatchers.IO) {
        if (course != null) {
            val course = CourseEntity(
                id = course.id,
                title = course.title,
                text = course.text,
                price = course.price,
                rate = course.rate,
                startDate = course.startDate,
                hasLike = course.hasLike,
                publishDate = course.publishDate
            )

            val updatedCourse = course.copy(hasLike = !course.hasLike)
            countriesDao.insertCourse(updatedCourse)
        }
    }

    override suspend fun starThisCourse(course: Course?) = withContext(Dispatchers.IO) {
        if (course != null) {
            val course = MyCoursesEntity(
                id = course.id,
                title = course.title,
                rate = course.rate,
                startDate = course.startDate,
                hasLike = course.hasLike,
                publishDate = course.publishDate,
                progress = 65,
                lessons = 44
            )
            countriesDao.insertMyCourse(course)
        }
    }

    override fun getMyCoursesFromDB(): Flow<Resource<List<MyCourse>>> {
        val myCourses = countriesDao.getMyCoursesItems()
        return myCourses.map {
            Resource.Success(
                it.map { myCourse ->
                    MyCourse(
                        id = myCourse.id,
                        title = myCourse.title,
                        rate = myCourse.rate,
                        startDate = myCourse.startDate,
                        hasLike = myCourse.hasLike,
                        publishDate = myCourse.publishDate,
                        progress = 65,
                        lessons = 44
                    )
                })
        }.flowOn(Dispatchers.IO)
    }
}