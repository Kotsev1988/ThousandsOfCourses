package you.today.main.ui.theme.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import you.today.main.R

@Composable
fun Main(
    navController: NavController,
) {
    val viewModel: MainViewModel = hiltViewModel()
    val countriesState = viewModel.blogsState
    val listState = rememberLazyListState()
    var text by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        when (countriesState) {
            is CoursesAppState.OnSuccessContents -> {
                Column(
                    modifier = Modifier.padding(top = 56.dp)
                ) {
                    Row(
                        Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .height(56.dp)
                                .weight(1f),
                            value = text,
                            onValueChange = { text = it },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.DarkGray,
                                unfocusedContainerColor = Color.DarkGray,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White
                            ),
                            placeholder = {
                                Text(
                                    text = "Search courses...",
                                    color = Color.White,
                                    fontWeight = FontWeight(400)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.White,
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = "Search"
                                )
                            },
                            shape = RoundedCornerShape(28.dp)
                        )

                        IconButton(
                            onClick = {},
                            modifier = Modifier.size(56.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = Color.Gray
                            )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.funnel),
                                contentDescription = "filter"
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            modifier = Modifier.padding(top = 16.dp),
                            onClick = {
                                viewModel.sortByDate()
                            },
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "По дате добавления ",
                                    color = Color.Green,
                                    textAlign = TextAlign.Start,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(600),
                                    lineHeight = 15.sp,
                                    letterSpacing = 0.4.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.roboto_regular)
                                    )
                                )

                                Icon(
                                    painter = painterResource(R.drawable.arrow_down_up),
                                    contentDescription = "arrow",
                                    tint = Color.Green,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        state = listState,
                    ) {
                        itemsIndexed(items = countriesState.content) { index, item ->
                            CoursesScreen(
                                content = item, modifier = Modifier, navController,
                                viewModel
                            )
                        }
                    }
                }
            }

            is CoursesAppState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is CoursesAppState.Error -> {
                ErrorScreen((countriesState.error))
            }
        }
    }
}