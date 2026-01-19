package you.today.thousandsofcourses.enter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class EnterViewModel @Inject constructor(

): ViewModel() {

    private val _emailText = mutableStateOf(
        EmailTextFieldState(
            ""
        )
    )
    val emailText: State<EmailTextFieldState> = _emailText

    private val _passwordText = mutableStateOf(
        PasswordTextFieldState(
            ""
        )
    )
    val passwordText: State<PasswordTextFieldState> = _passwordText

    fun handleViewEvent(viewEvent: AuthorizationEventView) {
        when (viewEvent) {
            is AuthorizationEventView.EnteredEmail -> {
                viewEvent.email?.let {
                    _emailText.value = emailText.value.copy(email = it)
                }
            }

            is AuthorizationEventView.EnteredPassword -> {
                viewEvent.password?.let {
                    _passwordText.value = passwordText.value.copy(password = it)
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkEmailAndPassword(onSuccess: ()->Unit, onError: ()->Unit){
          if (emailText.value.email.isBlank() || passwordText.value.password.isBlank()){
              onError()
          }
       else if (!isEmailValid(emailText.value.email)){
            onError()
        }
       else if (passwordText.value.password.length < 6){
            onError()
        } else {
            onSuccess()
          }
    }
}