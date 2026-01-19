package you.today.data

import androidx.room.RoomDatabase
import you.today.data.model.CourseEntity
import you.today.data.model.MyCoursesEntity

@androidx.room.Database (entities = [CourseEntity::class, MyCoursesEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract val coursesDao: CoursesDao
    abstract val myCoursesDao: MyCoursesDao

    companion object {
        const val DB_NAME = "database.db"
    }
}