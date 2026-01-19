package you.today.thousandsofcourses

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import you.today.thousandsofcourses.enter.EnterScreen
import you.today.thousandsofcourses.ui.theme.ThousandsOfCoursesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThousandsOfCoursesTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "enter_screen"
                )
                {
                    composable("enter_screen") {
                        EnterScreen(navController)
                    }

                    composable("main_screen") {
                        MainScreen()
                    }
                }
            }
        }
    }
}

