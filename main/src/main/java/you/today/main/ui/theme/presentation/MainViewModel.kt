package you.today.main.ui.theme.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import you.today.api.Resource
import you.today.api.models.Course
import you.today.main.ui.theme.domain.use_cases.GetCoursesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coursesUseCase: GetCoursesUseCase,
) : ViewModel() {

    var blogsState: CoursesAppState by mutableStateOf(CoursesAppState.Loading)
        private set

    private val _items = MutableStateFlow<List<Course>>(emptyList())

    private val _isAscending = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            combine(coursesUseCase.getFromDB(), _isAscending) { resource, ascending ->
                when (resource) {
                    is Resource.Success -> {
                        val data = resource.data ?: emptyList()
                        val sorted = if (ascending) {
                            data.sortedBy { it.publishDate }
                        } else {
                            data.sortedByDescending { it.publishDate }
                        }
                        Resource.Success(sorted)
                    }
                    else -> resource
                }
            }.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            blogsState = CoursesAppState.OnSuccessContents(it)
                            _items.value = it
                        }
                    }

                    is Resource.Loading -> {
                        blogsState = CoursesAppState.Loading
                    }

                    is Resource.Error -> {
                        blogsState = CoursesAppState.Error(resource.message ?: "Error")
                    }
                }
            }
        }

        viewModelScope.launch {
            coursesUseCase()
        }
    }

    fun sortByDate() {
        _isAscending.value = !_isAscending.value
    }

    fun addToFavorite(id: Int){
        viewModelScope.launch {
            _items.value.find { it.id == id }?.let {
                coursesUseCase.addToFavorite(it)
            }
        }
    }
}