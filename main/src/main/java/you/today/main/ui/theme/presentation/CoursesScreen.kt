package you.today.main.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import you.today.api.models.Course
import you.today.main.R

@Composable
fun CoursesScreen(
    content: Course,
    modifier: Modifier = Modifier,
    navigation: NavController,
    viewModel: MainViewModel,
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
            .requiredHeight(236.dp)
            .clickable {
                navigation.navigate("DetailScreen/${content.id}")
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),

        ) {

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .requiredHeight(114.dp)
            ) {
                Box(modifier = modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.cover),
                        contentDescription = "Картинка"
                    )

                    IconButton(
                        onClick = {
                            viewModel.addToFavorite(content.id)
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 8.dp)
                            .clip(RoundedCornerShape(100))
                            .requiredWidth(30.dp)
                            .requiredHeight(30.dp)
                            .background(Color(0x4D32333A)),
                    ) {
                        Icon(
                            painter = if (content.hasLike) painterResource(R.drawable.bookmark_fill)
                            else painterResource(R.drawable.bookmark_outlined),
                            contentDescription = "bookmark",
                            tint = if (content.hasLike) Color(0xFF12B956) else Color.Unspecified
                        )
                    }
                }
            }

            Text(
                text = content.title,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                modifier = modifier.padding(start = 16.dp, end = 4.dp, top = 16.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = modifier.padding(1.dp))

            Text(
                text = content.text,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                style = TextStyle(
                    color = Color(0xFFF2F2F2),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 16.sp
                ),
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content.price + " ₽",
                    color = Color(0xFFF2F2F2)
                )

                TextButton(
                    onClick = {
                        navigation.navigate("DetailScreen/${content.id}")
                    },
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Подробнее ",
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
                            painter = painterResource(R.drawable.arrow_right_short_fill),
                            contentDescription = "arrow",
                            tint = Color.Green,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}