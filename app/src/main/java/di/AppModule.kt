package di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import you.today.account.domain.use_cases.AccountUseCase
import you.today.api.ICoursesApi
import you.today.data.Database
import you.today.data.data.GetCoursesImpl
import you.today.data.repository.ICourses
import you.today.api.IGetCourses
import you.today.data.data.CoursesImpl
import you.today.detail.domain.GetCourseDetailUseCase
import you.today.favorites.domain.use_cases.FavoritesUseCase

import you.today.main.ui.theme.domain.use_cases.GetCoursesUseCase
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun providesGetBlogsImpl(api: ICoursesApi) = GetCoursesImpl(api)

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application) =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Provides
    @Singleton
    fun provideCountryItemRepository(db: Database): ICourses =
        CoursesImpl(db)

    @Provides
    @Singleton
    fun providesGetCoursesImpl(api: ICoursesApi, coursesDao: ICourses): IGetCourses =
        GetCoursesImpl(api, coursesDao)


    @Provides
    @Singleton
    fun providesContentsUseCases(repository: IGetCourses): GetCoursesUseCase =
        GetCoursesUseCase(repository)

    @Provides
    @Singleton
    fun providesContentsDetailUseCases(repository: IGetCourses): GetCourseDetailUseCase =
        GetCourseDetailUseCase(repository)

    @Provides
    @Singleton
    fun providesFavoritesContentsUseCases(repository: IGetCourses): FavoritesUseCase =
        FavoritesUseCase(repository)

    @Provides
    @Singleton
    fun providesAccountContentsUseCases(repository: IGetCourses): AccountUseCase =
        AccountUseCase(repository)


    @Named("baseURL")
    @Provides
    fun baseURL(): String = "https://drive.usercontent.google.com/"

    @Singleton
    @Provides
    fun api(@Named("baseURL") baseURL: String, gson: Gson): ICoursesApi =
        Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ICoursesApi::class.java)

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder().setLenient().create()
}