package com.sendiko.calcmenus.ui.screens.restaurant

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Store
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
import com.sendiko.calcmenus.ui.components.buttons.ButtonSize
import com.sendiko.calcmenus.ui.components.buttons.IconInButtonPosition
import com.sendiko.calcmenus.ui.components.buttons.OutlineButton
import com.sendiko.calcmenus.ui.components.buttons.OutlinedTextField
import com.sendiko.calcmenus.ui.components.buttons.PrimaryButton
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegister: () -> Unit,
    onNavigatePart: (Int) -> Unit,
    registerPart: Int,
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
                Column {
                    Text(
                        text = "Register",
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "a Restaurant",
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
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                val slideInAnimation = slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(durationMillis = 300)
                )
                val slideOutAnimation = slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(durationMillis = 300)
                )
                this@Column.AnimatedVisibility(
                    visible = registerPart == 1,
                ) {
                    RegisterPartOne(
                        onButtonClick = {
                            onNavigatePart(2)
                        }
                    )
                }
                this@Column.AnimatedVisibility(
                    visible = registerPart == 2,
                ) {
                    RegisterPartTwo(
                        onPrevButtonClick = { part ->
                            onNavigatePart(part)
                        },
                        onRegister = onRegister
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterPartOne(
    onButtonClick: () -> Unit
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
            textValue = "",
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

            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Address",
            isError = false,
            textValue = "",
            passwordVisible = true,
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

            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = "Phone Contact",
            isError = false,
            textValue = "",
            passwordVisible = true,
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
                onButtonClick()
            }
        )
    }
}

@Composable
fun RegisterPartTwo(
    onPrevButtonClick: (Int) -> Unit,
    onRegister: () -> Unit
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
            buttonSize = ButtonSize.BIG,
            text = "Register",
            onClick = {

            }
        )
        OutlineButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.BIG,
            text = "Previous",
            icon = Icons.Filled.KeyboardArrowLeft,
            iconPosition = IconInButtonPosition.BeforeText,
            onClick = {
                onPrevButtonClick(1)
            }
        )
    }
}

@Preview
@Composable
fun RegisterScreenPrev() {
    RegisterScreen(
        registerPart = 1,
        onNavigatePart = {

        },
        onRegister = {

        }
    )
}