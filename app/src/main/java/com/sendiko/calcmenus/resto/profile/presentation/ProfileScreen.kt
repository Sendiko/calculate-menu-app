package com.sendiko.calcmenus.resto.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.sendiko.calcmenus.core.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.core.ui.components.buttons.IconInButtonPosition
import com.sendiko.calcmenus.core.ui.components.buttons.OutlineButton
import com.sendiko.calcmenus.core.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.core.ui.components.others.ErrorMessageView
import com.sendiko.calcmenus.core.ui.components.others.MessageNotificationView
import com.sendiko.calcmenus.core.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.core.navigation.Graphs
import com.sendiko.calcmenus.core.navigation.Routes
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnAddressEdit
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnEditButtonClick
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnEmailEdit
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnLogoutClick
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnNameEdit
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnPasswordEdit
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnPasswordVisibilityClick
import com.sendiko.calcmenus.resto.profile.presentation.ProfileScreenEvent.OnPhoneEdit
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.core.theme.Yellowyellow
import com.sendiko.calcmenus.core.theme.myFont

@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    onEvent: (ProfileScreenEvent) -> Unit,
    navController: NavController = rememberNavController(),
    onNavigateBack: (route: String) -> Unit
) {
    LaunchedEffect(
        key1 = state.logoutSuccessful,
        block = {
            if (state.logoutSuccessful) {
                navController.navigate(
                    route = Graphs.WholeGraphRoute.graph
                ) {
                    popUpTo(
                        navController.graph.id,
                    ) { inclusive = true }
                }
            }
        }
    )
    Scaffold(
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "Profile",
                headerIcon = {
                    IconButton(
                        onClick = { onNavigateBack(Routes.RestoDashboardScreen.route) },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowLeft,
                                contentDescription = "Back",
                                tint = NotWhite
                            )
                        }
                    )
                },
                trailingIcon = {
                    SmallOutlineButton(
                        text = "Edit Profile",
                        background = Yellowyellow,
                        onClick = {
                            onEvent(OnEditButtonClick(!state.editable))
                        }
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (state.editable) {
                FloatingActionButton(
                    containerColor = PrimaryRed,
                    contentColor = NotWhite,
                    shape = RoundedCornerShape(100),
                    content = {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Update Profile",
                            style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight.Black, fontFamily = myFont
                            )
                        )
                    },
                    onClick = {

                    }
                )
            }
        }
    ) { paddingValues ->
        ErrorMessageView(
            errorMessage = state.failedState.failedMessage.toString(),
            isVisible = state.failedState.isFailed
        )
        MessageNotificationView(
            message = "Logout success.",
            isVisible = state.logoutSuccessful
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clip(
                    RoundedCornerShape(
                        topStartPercent = 10, topEndPercent = 10
                    )
                )
                .background(NotWhite)
        ) {
            LazyColumn(
                content = {
                    item {
                        Row(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f))
                            if (state.imageUri == null) {
                                IconButton(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10))
                                        .background(Color.Gray)
                                        .aspectRatio(1f)
                                        .weight(2f),
                                    onClick = {
//                                        imagePicker.launch(
//                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                                        )
                                    },
                                    content = {
                                        Icon(
                                            modifier = Modifier
                                                .padding(48.dp)
                                                .fillMaxSize(),
                                            imageVector = Icons.Filled.AddAPhoto,
                                            contentDescription = "Add picture"
                                        )
                                    }
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10))
                                        .background(Color.Gray)
                                        .aspectRatio(1f)
                                        .weight(2f),
                                ) {
                                    SubcomposeAsyncImage(
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .clip(RoundedCornerShape(5)),
                                        model = state.imageUri,
                                        contentDescription = null,
                                        loading = {
                                            CircularProgressIndicator(
                                                modifier = Modifier.padding(32.dp),
                                                color = PrimaryRed,
                                                strokeCap = StrokeCap.Butt
                                            )
                                        },
                                        error = {
                                            Icon(
                                                modifier = Modifier.padding(16.dp),
                                                imageVector = Icons.Filled.SignalWifiConnectedNoInternet4,
                                                contentDescription = "No Connection"
                                            )
                                        },
                                        contentScale = ContentScale.Crop
                                    )
                                    if (state.editable) {
                                        IconButton(
                                            modifier = Modifier
                                                .aspectRatio(1f),
                                            onClick = {
//                                                imagePicker.launch(
//                                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                                                )
                                            },
                                            content = {
                                                Icon(
                                                    modifier = Modifier
                                                        .padding(48.dp)
                                                        .fillMaxSize(),
                                                    imageVector = Icons.Filled.AddAPhoto,
                                                    contentDescription = "Add picture",
                                                    tint = Color(0x7F000000)
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "Nama Resto",
                            isError = false,
                            textValue = state.restoName,
                            enabled = state.editable,
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
                                onEvent(OnNameEdit(it))
                            }
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "Address",
                            isError = false,
                            textValue = state.restoAddress,
                            enabled = state.editable,
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
                                onEvent(OnAddressEdit(it))
                            }
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "82241626760",
                            isError = false,
                            textValue = state.restoPhone,
                            enabled = state.editable,
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
                            trailingIcon = {},
                            onNewValue = {
                                onEvent(OnPhoneEdit(it))
                            }
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "Email",
                            isError = false,
                            textValue = "",
                            enabled = state.editable,
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
                                onEvent(OnEmailEdit(it))
                            }
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "*******",
                            isError = false,
                            textValue = "",
                            isPasswordVisible = true,
                            enabled = state.editable,
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
                                    onClick = { onEvent(OnPasswordVisibilityClick(!state.isPasswordVisible)) },
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
                                onEvent(OnPasswordEdit(it))
                            }
                        )
                    }
                    item {
                        OutlineButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = if (!state.isLoading) "Logout" else "Logging out...",
                            icon = Icons.Default.Logout,
                            iconPosition = IconInButtonPosition.AfterText,
                            enabled = !state.isLoading,
                            onClick = {
                                onEvent(OnLogoutClick)
                            }
                        )
                    }
                }
            )
        }
    }
}