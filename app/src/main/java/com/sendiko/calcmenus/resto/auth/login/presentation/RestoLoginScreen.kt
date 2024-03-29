package com.sendiko.calcmenus.resto.auth.login.presentation

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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.R
import com.sendiko.calcmenus.core.ui.components.buttons.ButtonSize.BIG
import com.sendiko.calcmenus.core.ui.components.buttons.PrimaryButton
import com.sendiko.calcmenus.core.ui.components.others.ErrorMessageView
import com.sendiko.calcmenus.core.ui.components.others.MessageNotificationView
import com.sendiko.calcmenus.core.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.core.navigation.Graphs
import com.sendiko.calcmenus.resto.auth.login.presentation.RestoLoginScreenEvent.OnEmailInput
import com.sendiko.calcmenus.resto.auth.login.presentation.RestoLoginScreenEvent.OnLoginClick
import com.sendiko.calcmenus.resto.auth.login.presentation.RestoLoginScreenEvent.OnPasswordInput
import com.sendiko.calcmenus.resto.auth.login.presentation.RestoLoginScreenEvent.OnPasswordVisibilityToggle
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.core.theme.myFont

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestoLoginScreen(
    state: RestoLoginScreenState,
    onEvent: (RestoLoginScreenEvent) -> Unit,
    navController: NavController = rememberNavController()
) {
    LaunchedEffect(
        key1 = state.loginSuccessful,
        block = {
            if (state.loginSuccessful) {
                navController.navigate(
                    route = Graphs.RestoMainGraph.graph
                ) {
                    popUpTo(
                        navController.graph.id,
                    ) { inclusive = true }
                }
            }
        }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = NotWhite
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ErrorMessageView(
                errorMessage = state.failedState.failedMessage.toString(),
                isVisible = state.failedState.isFailed
            )
            MessageNotificationView(
                message = "Login success.",
                isVisible = state.loginSuccessful
            )
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
                Column {
                    Text(
                        text = "Log In",
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold,
                            fontFamily = myFont
                        )
                    )
                    Text(
                        text = "as Restaurant",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold,
                            fontFamily = myFont
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
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        hint = "Email",
                        isError = false,
                        textValue = state.email,
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
                            onEvent(OnEmailInput(it))
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        hint = "Password",
                        isError = false,
                        textValue = state.password,
                        isPasswordVisible = state.isPasswordVisible,
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
                                onClick = { onEvent(OnPasswordVisibilityToggle(!state.isPasswordVisible)) },
                                modifier = Modifier.padding(
                                    end = 8.dp
                                )
                            ) {
                                Icon(
                                    imageVector = if (state.isPasswordVisible)
                                        Icons.Default.Visibility
                                    else Icons.Filled.VisibilityOff,
                                    contentDescription = "Password"
                                )
                            }
                        },
                        onNewValue = {
                            onEvent(OnPasswordInput(it))
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (!state.isLoading) "Login" else "Logging you in...",
                        buttonSize = BIG,
                        enabled = !state.isLoading,
                        onClick = {
                            onEvent(OnLoginClick)
                        }
                    )
                }
            }
        }
    }
}