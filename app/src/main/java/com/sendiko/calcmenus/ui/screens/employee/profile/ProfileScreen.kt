package com.sendiko.calcmenus.ui.screens.employee.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.Yellowyellow
import com.sendiko.calcmenus.ui.theme.myFont

@Composable
fun ProfileScreen(
    onNavigateBack: (route: String) -> Unit
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var editable by remember {
        mutableStateOf(false)
    }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri = uri
        }
    )
    imageUri = Uri.parse("http://192.168.1.8:8000/storage/images/menu/MbaC4FwN5iRwoSAVgmk3ORcVcsiumkXw93I9Fd9s.jpg")
    Scaffold(
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "Profile",
                headerIcon = {
                    IconButton(
                        onClick = { onNavigateBack(Routes.EmployeeMenuScreen.route) },
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
                            editable = !editable
                        }
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
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
                            if (imageUri == null) {
                                IconButton(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10))
                                        .background(Color.Gray)
                                        .aspectRatio(1f)
                                        .weight(2f),
                                    onClick = {
                                        imagePicker.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
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
                                ){
                                    SubcomposeAsyncImage(
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .clip(RoundedCornerShape(5)),
                                        model = imageUri,
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
                                    IconButton(
                                        modifier = Modifier
                                            .aspectRatio(1f),
                                        onClick = {
                                            imagePicker.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                            )
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
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "Velarina Nurmalakana",
                            isError = false,
                            textValue = "",
                            enabled = editable,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.RestaurantMenu,
                                    contentDescription = "Menu"
                                )
                            },
                            onNewValue = {}
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "velarina2014@gmail.com",
                            isError = false,
                            textValue = "",
                            enabled = editable,
                            leadingIcon = {
                                Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
                            },
                            onNewValue = {}
                        )
                    }
                    item {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "#######",
                            isError = false,
                            textValue = "",
                            enabled = editable,
                            leadingIcon = {
                                Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password")
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = { /*TODO*/ },
                                    content = {
                                        Icon(
                                            imageVector = Icons.Filled.Visibility,
                                            contentDescription = "Password"
                                        )
                                    }
                                )
                            },
                            onNewValue = {}
                        )
                    }
                }
            )
        }
    }
}