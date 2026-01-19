package you.today.thousandsofcourses.enter

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import you.today.thousandsofcourses.R

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun EnterScreen(
    navController: NavHostController,
) {
    val viewModel: EnterViewModel = hiltViewModel()
    val emailState = viewModel.emailText.value
    val passwordState = viewModel.passwordText.value
    val context = LocalContext.current

    Scaffold { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.Black
        ) {
            Column(
                modifier = Modifier.padding(top = 140.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Вход",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontSize = 28.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 36.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                )

                Text(
                    text = "Email",
                    modifier = Modifier.padding(top = 28.dp),
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 18.sp,
                    letterSpacing = 0.15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 11.dp),
                    shape = RoundedCornerShape(30.dp),
                    value = emailState.email,
                    onValueChange = { it ->
                        if (it.all {
                                it in 'a'..'z' || it in 'A'..'Z' || it in '0'..'9' ||
                                        it in "@._-+"
                            }) {
                            viewModel.handleViewEvent(AuthorizationEventView.EnteredEmail(it))
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "example@gmail.com",
                            color = Color.White,
                            fontWeight = FontWeight(400)
                        )
                    },
                    textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )

                Text(
                    text = "Пароль",
                    modifier = Modifier.padding(top = 26.dp),
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 18.sp,
                    letterSpacing = 0.15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 11.dp),
                    shape = RoundedCornerShape(26.dp),
                    value = passwordState.password,
                    onValueChange = {
                        viewModel.handleViewEvent(AuthorizationEventView.EnteredPassword(it))
                    },
                    placeholder = {
                        Text(
                            text = "Введите пароль",
                            color = Color.White,
                            fontWeight = FontWeight(400)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Button(
                    onClick = {
                        viewModel.checkEmailAndPassword(
                            onSuccess = {
                                navController.navigate("main_screen") {
                                    popUpTo("enter_screen") {
                                        inclusive = true
                                    }
                                }
                            },
                            onError = {
                                Toast.makeText(
                                    context,
                                    "Введите данные правильно",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF12B956)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(30.dp)
                )
                {
                    Text(
                        text = "Вход",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            letterSpacing = 0.1.sp,
                            textAlign = TextAlign.Center,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = true
                            )
                        )
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = Modifier.padding(0.dp),
                                text = "Нету аккаунта?",
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600),
                                lineHeight = 15.sp,
                                letterSpacing = 0.4.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),

                                )
                            TextButton(
                                onClick = {},
                                modifier = Modifier
                                    .padding(0.dp)
                                    .height(15.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = "Регистрация",
                                    color = Color(0xFF12B956),
                                    textAlign = TextAlign.Start,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(600),
                                    lineHeight = 15.sp,
                                    letterSpacing = 0.4.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular))
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(0.dp)
                                .height(15.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "Забыли пароль",
                                color = Color(0xFF12B956),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600),
                                lineHeight = 15.sp,
                                letterSpacing = 0.4.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular))
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(top = 32.dp),
                            thickness = 1.dp,
                            color = Color.Gray
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth().padding(top = 32.dp)
                        ) {

                            Button(
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        "https://vk.com".toUri()
                                    )
                                    context.startActivity(intent)
                                },
                                modifier = Modifier
                                    .height(40.dp)
                                    .weight(1f),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xFF2683ED)
                                )
                            ) {
                                Icon(
                                    modifier = Modifier.size(40.dp),
                                    painter = painterResource(R.drawable.vk_icon),
                                    contentDescription = "",
                                    tint = Color.Unspecified
                                )
                            }

                            Button(
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        "https://ok.ru".toUri()
                                    )
                                    context.startActivity(intent)
                                },

                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.White
                                ),

                                elevation = ButtonDefaults.elevation(0.dp, 0.dp),

                                shape = RoundedCornerShape(30.dp),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier
                                    .height(40.dp)
                                    .weight(1f)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xFFF98509),
                                                Color(0xFFF95D00)
                                            )
                                        ),
                                        shape = RoundedCornerShape(30.dp)
                                    )
                            ) {
                                Icon(
                                    modifier = Modifier.size(40.dp),
                                    painter = painterResource(R.drawable.ok_icon),
                                    contentDescription = "",
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}