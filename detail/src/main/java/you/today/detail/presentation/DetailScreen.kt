package you.today.detail.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import you.today.detail.R

@Composable
fun DetailScreen(
    id: Int,
    navController: NavController,
) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val contentState = viewModel.detailState

    Surface(color = Color.Black) {
        when (contentState) {
            is DetailAppState.OnSuccessContents -> {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Box() {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(R.drawable.cover),
                            contentDescription = "Картинка"
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .fillMaxWidth()
                                .padding(top = 56.dp, start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                                    .requiredWidth(44.dp)
                                    .requiredHeight(44.dp)
                                    .background(Color(0xFFF2F2F3)),
                                onClick = {
                                    navController.navigateUp()
                                },
                            )
                            {
                                Icon(
                                    Icons.Filled.ArrowBack,
                                    "backIcon"
                                )
                            }

                            IconButton(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                                    .requiredWidth(44.dp)
                                    .requiredHeight(44.dp)
                                    .background(Color(0xFFF2F2F3)),
                                onClick = {
                                    viewModel.addToFavorite()
                                },
                            )
                            {
                                Icon(
                                    imageVector = if (contentState.hasLike) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                    contentDescription = "backIcon",
                                    tint = if (contentState.hasLike) Color(0xFF12B956) else Color.Unspecified
                                )
                            }
                        }
                    }

                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Text(
                            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                            text = contentState.title,
                            color = Color(0xFFF2F2F2),
                            textAlign = TextAlign.Start,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 28.sp,
                            letterSpacing = 0.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Crop,
                                painter = painterResource(R.drawable.image_detail_screen),
                                contentDescription = "some icon"
                            )

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Автор",
                                    color = Color(0xFFF2F2F2),
                                    textAlign = TextAlign.Start,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    lineHeight = 14.sp,
                                    letterSpacing = 0.14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                                )
                                Text(
                                    text = "Merion Academy",
                                    color = Color(0xFFF2F2F2),
                                    textAlign = TextAlign.Start,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    lineHeight = 18.sp,
                                    letterSpacing = 0.15.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                                )
                            }
                        }

                        Button(
                            onClick = {
                                viewModel.startThisCourse()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Green
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(100.dp)
                        )
                        {
                            Text(
                                text = "Начать курс",
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = {
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.DarkGray
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(100.dp)
                        )
                        {
                            Text(
                                text = "Перейти на платформу",
                                color = Color.White
                            )
                        }

                        Text(
                            text = "О курсе",
                            modifier = Modifier.padding(top = 28.dp, bottom = 28.dp),
                            color = Color(0xFFF2F2F2),
                            textAlign = TextAlign.Start,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 28.sp,
                            letterSpacing = 0.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )

                        Text(
                            text = contentState.content,
                            color = Color(0xFFF2F2F2),
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.W400, // Regular
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            letterSpacing = 0.25.sp
                        )
                    }
                }
            }

            is DetailAppState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is DetailAppState.Error -> {
                ErrorScreen(contentState.error)
            }
        }
    }
}