package you.today.thousandsofcourses

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import you.today.account.presentation.AccountScreen
import you.today.detail.presentation.DetailScreen
import you.today.favorites.presentation.FavoritesScreen
import you.today.main.ui.theme.presentation.Main

private const val TAB_MAIN = "tab_main"
private const val TAB_FAVORITES = "tab_favorites"
private const val TAB_ACCOUNT = "tab_account"

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomItems = listOf(
        BottomMenuItem(
            label = TAB_MAIN,
            stringId = R.string.main_screen,
            icon = painterResource(id = R.drawable.main_icon)
        ),
        BottomMenuItem(
            label = TAB_FAVORITES,
            stringId = R.string.favorite_screen,
            icon = painterResource(id = R.drawable.icon_favorite)
        ),
        BottomMenuItem(
            label = TAB_ACCOUNT,
            stringId = R.string.account_screen,
            icon = painterResource(id = R.drawable.icon_account)
        )
    )

    Scaffold(
        containerColor=Color(0xFF24252A),
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.navigationBarsPadding().padding(8.dp),
                backgroundColor = Color(0xFF24252A),
                contentColor = MaterialTheme.colorScheme.primary,
                elevation = 0.dp
            ) {
                bottomItems.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.label } == true

                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.label) {

                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        selectedContentColor = Color(0xFF128956),
                        unselectedContentColor = Color(0x4D0D0D0D),
                        label = {
                            Text(
                                text=stringResource(screen.stringId),
                                color = if (selected) Color(0xFF128956) else MaterialTheme.colorScheme.onSecondary,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 16.sp
                            )
                        },
                        icon = {
                            Icon(
                                painter = screen.icon, contentDescription = screen.label,
                                tint = if (selected) Color(0xFF128956) else MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.MainScreen.route,
            Modifier.padding(contentPadding)
        ) {
            navigation(
                startDestination = TAB_MAIN,
                route = Screens.MainScreen.route
            ) {
                composable(TAB_MAIN) { Main(navController) }

                composable(
                    route = Screens.DetailScreen.route,
                    arguments = listOf(navArgument("id") { type = NavType.IntType })

                ) {
                    it.arguments?.getInt("id")?.let { id -> DetailScreen(id, navController) }
                }
            }

            composable(TAB_FAVORITES) { FavoritesScreen(navController) }

            composable(TAB_ACCOUNT) { AccountScreen() }
        }
    }
}