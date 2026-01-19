package you.today.data.data

import kotlinx.coroutines.flow.Flow
import you.today.data.Database
import you.today.data.model.CourseEntity
import you.today.data.model.MyCoursesEntity
import you.today.data.repository.ICourses

class CoursesImpl(
    private val db: Database,
) : ICourses {
    override suspend fun insertCoursesItemToDb(coursesItem: List<CourseEntity>) {
        db.coursesDao.insert(coursesItem)
    }

    override suspend fun insertCourse(coursesItem: CourseEntity) {
        db.coursesDao.update(coursesItem)
    }

    override suspend fun insertMyCourse(coursesItem: MyCoursesEntity) {
        db.myCoursesDao.insert(coursesItem)
    }

    override fun getCoursesItems(): Flow<List<CourseEntity>> = db.coursesDao.getAll()

    override fun getFavoritesCoursesItems(): Flow<List<CourseEntity>> =
        db.coursesDao.getFavoritesCoursesItems()

    override fun getCoursesItemById(id: Int): Flow<CourseEntity> = db.coursesDao.getCountryById(id)

    override fun getMyCoursesItems(): Flow<List<MyCoursesEntity>> = db.myCoursesDao.getAll()
}