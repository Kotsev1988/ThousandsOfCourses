package you.today.favorites.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import you.today.favorites.R

@Composable
fun FavoritesScreen(navController: NavHostController) {

    val viewModel: FavoritesViewModel = hiltViewModel()
    val coursesState = viewModel.blogsState
    val listState = rememberLazyListState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        when (coursesState) {
            is FavoriteCoursesAppState.OnSuccessContents -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Избранное",
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier.padding(16.dp),
                        color = Color(0xFFF2F2F3),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            letterSpacing = 0.sp,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = true
                            )
                        )
                    )

                    LazyColumn(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        state = listState,
                    ) {
                        itemsIndexed(items = coursesState.content) { index, item ->
                            FavoritesCoursesScreen(
                                content = item, modifier = Modifier, viewModel,
                                navController
                            )
                        }
                    }
                }
            }

            is FavoriteCoursesAppState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is FavoriteCoursesAppState.Error -> {
                ErrorScreen(coursesState.error)
            }
        }
    }
}