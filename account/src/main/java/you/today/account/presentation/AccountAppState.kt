package you.today.account.presentation

import you.today.api.models.MyCourse

sealed class AccountAppState {
    data class OnSuccessContents(
        val content: List<MyCourse>
    ): AccountAppState()

    data class Error(val error: String) : AccountAppState()
    object Loading : AccountAppState()
}