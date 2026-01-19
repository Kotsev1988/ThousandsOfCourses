package you.today.account.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import you.today.account.R

@Composable
fun AccountScreen() {
    Text(text = "Account")

    val viewModel: AccountViewModel = hiltViewModel()
    val coursesState = viewModel.blogsState
    val listState = rememberLazyListState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        when (coursesState) {
            is AccountAppState.OnSuccessContents -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Профиль",
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

                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onBackground
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Написать в поддержку",
                                style = TextStyle(
                                    color = Color(0xFFF2F2F2),
                                    fontFamily = FontFamily(
                                        Font(
                                            R.font.roboto_regular,
                                            FontWeight.Medium
                                        )
                                    ),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp, // Стандарт для Label Large
                                    lineHeight = 20.sp, // Стандарт для Label Large
                                    letterSpacing = 0.1.sp, // Стандарт для Label Large
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = true // Аналог leading-trim: NONE
                                    )
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(top = 8.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_arrow),
                                contentDescription = "support",
                                tint = Color(0xFFF2F2F2)
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
                            thickness = 1.dp,
                            color = Color.Gray
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Настройки",
                                style = TextStyle(
                                    color = Color(0xFFF2F2F2),
                                    fontFamily = FontFamily(
                                        Font(
                                            R.font.roboto_regular,
                                            FontWeight.Medium
                                        )
                                    ),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp, // Стандарт для Label Large
                                    lineHeight = 20.sp, // Стандарт для Label Large
                                    letterSpacing = 0.1.sp, // Стандарт для Label Large
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = true // Аналог leading-trim: NONE
                                    )
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(top = 8.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_arrow),
                                contentDescription = "support",
                                tint = Color(0xFFF2F2F2)
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
                            thickness = 1.dp,
                            color = Color.Gray
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Выйти из аккаунта",
                                style = TextStyle(
                                    color = Color(0xFFF2F2F2),
                                    fontFamily = FontFamily(
                                        Font(
                                            R.font.roboto_regular,
                                            FontWeight.Medium
                                        )
                                    ),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp, // Стандарт для Label Large
                                    lineHeight = 20.sp, // Стандарт для Label Large
                                    letterSpacing = 0.1.sp, // Стандарт для Label Large
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = true // Аналог leading-trim: NONE
                                    )
                                ),
                                modifier = Modifier
                                    .wrapContentHeight(Alignment.CenterVertically)
                                    .padding(top = 8.dp, bottom = 8.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_arrow),
                                contentDescription = "support",
                                tint = Color(0xFFF2F2F2)
                            )
                        }
                    }

                    Text(
                        text = "Ваши курсы",
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
                            MyCoursesScreen(content = item, modifier = Modifier)
                        }
                    }
                }
            }

            is AccountAppState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is AccountAppState.Error -> {
                ErrorScreen(coursesState.error)
            }
        }
    }
}
