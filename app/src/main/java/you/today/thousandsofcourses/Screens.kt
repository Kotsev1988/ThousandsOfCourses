package you.today.thousandsofcourses
import androidx.annotation.StringRes

sealed class Screens(val route: String, @StringRes resId: Int) {
    object MainScreen: Screens("main_screen", R.string.main_screen)
    object FavoriteScreen: Screens("favorites_screen", R.string.favorite_screen)
    object AccountScreen: Screens("account_screen", R.string.account_screen)
    object DetailScreen: Screens("DetailScreen/{id}", R.string.detail_screen)
}