package com.sendiko.calcmenus.ui.screens.employee.login

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
import com.sendiko.calcmenus.ui.components.buttons.ButtonSize
import com.sendiko.calcmenus.ui.components.buttons.PrimaryButton
import com.sendiko.calcmenus.ui.components.others.ErrorMessageView
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EmployeeLoginScreen(
    state: EmployeeLoginScreenState,
    onEvents: (EmployeeLoginEvents) -> Unit,
    navController: NavController = rememberNavController()
) {
    LaunchedEffect(
        key1 = state.loginSuccessful,
        block = {
            when(state.loginSuccessful){
                true -> navController.navigate(
                    route = Graphs.EmpMainGraph.graph
                ) {
                    popUpTo(
                        navController.graph.id,
                    ) { inclusive = true }
                }
                else -> null
            }
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = NotWhite,
    ) {
        LaunchedEffect(
            key1 = state.failedState.isFailed,
            block = {
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        ErrorMessageView(
            errorMessage = state.failedState.failedMessage.toString(),
            isVisible = state.failedState.isFailed
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                            text = "as Employee",
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
                                onEvents(EmployeeLoginEvents.OnEmailInput(it))
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
                                    onClick = {
                                        onEvents(EmployeeLoginEvents.PasswordVisibilityToggle(!state.isPasswordVisible))
                                    },
                                    modifier = Modifier.padding(
                                        end = 8.dp
                                    )
                                ) {
                                    Icon(
                                        imageVector = if(state.isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = "Password"
                                    )
                                }
                            },
                            onNewValue = {
                                onEvents(EmployeeLoginEvents.OnPasswordInput(it))
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = if (state.isLoading) "Logging In..." else "Login",
                            buttonSize = ButtonSize.BIG,
                            enabled = !state.isLoading,
                            onClick = {
                                onEvents(EmployeeLoginEvents.OnClickLogin)
                            },
                        )
                    }
                }
            }
        }
    }
}