package com.sendiko.calcmenus.ui.screens.restaurant

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.R
import com.sendiko.calcmenus.ui.components.buttons.ButtonSize.BIG
import com.sendiko.calcmenus.ui.components.buttons.OutlinedTextField
import com.sendiko.calcmenus.ui.components.buttons.PrimaryButton
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    onLogin: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = NotWhite
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_ill),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Column{
                    Text(
                        text = "Log In",
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "as Restaurant",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        hint = "Email",
                        isError = false,
                        textValue = "",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email",
                                modifier = Modifier.padding(
                                    start = 8.dp
                                )
                            )
                        },
                        onNewValue = {

                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        hint = "Password",
                        isError = false,
                        textValue = "",
                        passwordVisible = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Password",
                                modifier = Modifier.padding(
                                    start = 8.dp
                                )
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.padding(
                                    end = 8.dp
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "Password"
                                )
                            }
                        },
                        onNewValue = {

                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Login",
                        buttonSize = BIG,
                        onClick = {

                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen (
        onLogin = {

        }
    )
}