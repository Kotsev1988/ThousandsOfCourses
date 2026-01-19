package you.today.account.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import you.today.account.domain.use_cases.AccountUseCase
import you.today.api.Resource
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase,
) : ViewModel() {

    var blogsState: AccountAppState by mutableStateOf(AccountAppState.Loading)
        private set

    init {
        viewModelScope.launch {
            accountUseCase.getMyCoursesFromDB().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            blogsState = AccountAppState.OnSuccessContents(it)
                        }
                    }

                    is Resource.Loading -> {
                        blogsState = AccountAppState.Loading
                    }

                    is Resource.Error -> {
                        blogsState = AccountAppState.Error(resource.message ?: "Error")
                    }
                }
            }
        }
    }
}