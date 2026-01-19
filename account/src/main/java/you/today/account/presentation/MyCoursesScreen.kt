package you.today.account.presentation

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import you.today.account.R
import you.today.api.models.MyCourse

@Composable
fun MyCoursesScreen(
    content: MyCourse,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(236.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
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

            val progress = content.progress.toFloat() / 100

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = content.progress.toString() + "%",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        color = Color(0xFF12B956),
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.4.sp,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = true
                        )
                    )
                )

                Row {
                    Text(
                        text = content.lessons.toString(),
                        style = TextStyle(
                            color = Color(0xFF12B956),
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.4.sp,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = true
                            )
                        )
                    )
                    Text(
                        text = "/68 уроков",
                        style = TextStyle(
                            color = Color(0xFFF2F2F3),
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.4.sp,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = true
                            )
                        )
                    )

                }

            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFF12B956),
                trackColor = Color(0x4D32333A),
                gapSize = 0.dp,
                drawStopIndicator = {}
            )
        }
    }
}