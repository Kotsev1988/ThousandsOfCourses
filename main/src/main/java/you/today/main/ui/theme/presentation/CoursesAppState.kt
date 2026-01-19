package you.today.main.ui.theme.presentation

import you.today.api.models.Course

sealed class CoursesAppState {
    data class OnSuccessContents(
        val content: List<Course>
    ): CoursesAppState()

    data class Error(val error: String) : CoursesAppState()
    object Loading : CoursesAppState()
}
