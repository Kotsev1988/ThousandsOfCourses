package you.today.favorites.presentation

import you.today.api.models.Course

sealed class FavoriteCoursesAppState {
    data class OnSuccessContents(
        val content: List<Course>
    ): FavoriteCoursesAppState()

    data class Error(val error: String) : FavoriteCoursesAppState()
    object Loading : FavoriteCoursesAppState()
}
