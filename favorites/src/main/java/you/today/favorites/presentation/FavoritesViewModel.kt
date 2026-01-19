package you.today.favorites.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import you.today.api.Resource
import you.today.api.models.Course
import you.today.favorites.domain.use_cases.FavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel@Inject constructor(
    private val favoritesUseCase: FavoritesUseCase,
) : ViewModel() {

    var blogsState: FavoriteCoursesAppState by mutableStateOf(FavoriteCoursesAppState.Loading)
        private set

    private val _items = MutableStateFlow<List<Course>>(emptyList())

    init {
        viewModelScope.launch {

            favoritesUseCase.getFavoritesFromDB().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            blogsState = FavoriteCoursesAppState.OnSuccessContents(it)
                            _items.value = it
                        }
                    }

                    is Resource.Loading -> {
                        blogsState = FavoriteCoursesAppState.Loading
                    }

                    is Resource.Error -> {
                        blogsState = FavoriteCoursesAppState.Error(resource.message ?: "Error")
                    }
                }
            }
        }
    }

    fun addToFavorite(id: Int){
        viewModelScope.launch {
            _items.value.find { it.id == id }?.let {
                favoritesUseCase.addToFavorite(it)
            }
        }
    }
}