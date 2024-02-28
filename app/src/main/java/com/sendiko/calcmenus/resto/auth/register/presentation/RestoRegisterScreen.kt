package com.sendiko.calcmenus.resto.auth.register.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Store
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.R
import com.sendiko.calcmenus.ui.components.buttons.ButtonSize
import com.sendiko.calcmenus.ui.components.buttons.IconInButtonPosition
import com.sendiko.calcmenus.ui.components.buttons.OutlineButton
import com.sendiko.calcmenus.ui.components.buttons.PrimaryButton
import com.sendiko.calcmenus.ui.components.others.ErrorMessageView
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.resto.auth.register.presentation.RestoRegisterEvent.*
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestoRegisterScreen(
    state: RestoRegisterState,
    onEvent: (RestoRegisterEvent) -> Unit,
    navController: NavController = rememberNavController()
) {
    LaunchedEffect(
        key1 = state.loginSuccessful,
        block = {
            when (state.loginSuccessful) {
                true -> navController.navigate(
                    route = Routes.RestoLogin.route
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
        containerColor = NotWhite
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ErrorMessageView(
                errorMessage = state.failedState.failedMessage.toString(),
                isVisible = state.failedState.isFailed
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
                        text = "Register",
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold,
                            fontFamily = myFont
                        )
                    )
                    Text(
                        text = "a Restaurant",
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
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = state.registerPart == 1,
                ) {
                    RegisterPartOne(
                        state, onEvent
                    )
                }
                this@Column.AnimatedVisibility(
                    visible = state.registerPart == 2,
                ) {
                    RegisterPartTwo(
                        state, onEvent
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterPartOne(
    state: RestoRegisterState,
    onEvent: (RestoRegisterEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Restaurant Name",
            isError = false,
            textValue = state.restoName,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Store,
                    contentDescription = "Restaurant",
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            },
            onNewValue = {
                onEvent(OnRestoNameInput(it))
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Address",
            isError = false,
            textValue = state.restoAddress,
            isPasswordVisible = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Address",
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            },
            onNewValue = {
                onEvent(OnRestoAddressInput(it))
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Phone Contact",
            isError = false,
            textValue = state.restoPhone,
            isPasswordVisible = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            prefix = {
                Text(
                    "+62",
                    style = TextStyle(
                        fontFamily = myFont,
                        fontWeight = FontWeight.Bold,
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.PhoneAndroid,
                    contentDescription = "Phone",
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            },
            onNewValue = {
                onEvent(OnRestoPhoneInput(it))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Next",
            buttonSize = ButtonSize.BIG,
            icon = Icons.Filled.KeyboardArrowRight,
            iconPosition = IconInButtonPosition.AfterText,
            onClick = {
                onEvent(OnNavigatePart(2))
            }
        )
    }
}

@Composable
fun RegisterPartTwo(
    state: RestoRegisterState,
    onEvent: (RestoRegisterEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Email",
            isError = false,
            textValue = state.email,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            supportingText = "Password has to be 8 character or more",
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
            buttonSize = ButtonSize.BIG,
            enabled = !state.isLoading,
            text = if (!state.isLoading) "Register" else "Registering you in...",
            onClick = {
                onEvent(OnClickRegister)
            }
        )
        OutlineButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.BIG,
            text = if (!state.isLoading) "Previous" else "Wait for a sec",
            enabled = !state.isLoading,
            icon = Icons.Filled.KeyboardArrowLeft,
            iconPosition = IconInButtonPosition.BeforeText,
            onClick = {
                onEvent(OnNavigatePart(1))
            }
        )
    }
}