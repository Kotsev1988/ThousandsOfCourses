package you.today.thousandsofcourses.enter

sealed class AuthorizationEventView {

    data class EnteredEmail(val email: String?): AuthorizationEventView()
    data class EnteredPassword(val password: String?): AuthorizationEventView()
}