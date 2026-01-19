package you.today.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import you.today.api.Resource
import you.today.api.models.Course
import you.today.detail.domain.GetCourseDetailUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailCoursesUseCase: GetCourseDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var detailState: DetailAppState by mutableStateOf(DetailAppState.Loading)
        private set
    private var currentCourse: Course? = null

    init {
        savedStateHandle.get<Int>("id")?.let {
            detailState = DetailAppState.Loading
            try{
                viewModelScope.launch {
                  val  course = detailCoursesUseCase(it)

                    course.collect {
                            resource ->
                        when (resource) {
                            is Resource.Success -> {
                                currentCourse = resource.data
                                resource.data?.let { course ->
                                    detailState = DetailAppState.OnSuccessContents(
                                        title = course.title,
                                        publishedDate = course.publishDate,
                                        content = course.text,
                                        hasLike = course.hasLike
                                    )
                                }
                            }
                            is Resource.Error -> {
                                DetailAppState.Error(resource.message ?: "Error")
                            }
                            is Resource.Loading -> {
                                DetailAppState.Loading
                            }
                        }
                    }
                }
            }catch (e: Exception) {
            detailState = DetailAppState.Error(e.message.toString())
        }
        }
    }

    fun addToFavorite(){
        viewModelScope.launch {
            detailCoursesUseCase.addToFavorite(currentCourse)
        }
    }

    fun startThisCourse(){
        viewModelScope.launch {
            detailCoursesUseCase.startThisCourse(currentCourse)
        }
    }
}