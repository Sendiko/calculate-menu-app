package com.sendiko.calcmenus.resto.main.menu.presentation

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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.sendiko.calcmenus.core.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.core.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.core.ui.components.textfields.TextArea
import com.sendiko.calcmenus.core.navigation.Routes
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.core.theme.myFont

@Composable
fun CreateMenuScreen(
    onNavigateBack: (route: String) -> Unit,
    onMenuCreated: () -> Unit
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri = uri
        }
    )
    Scaffold(
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "Add Menu",
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
                trailingIcon = { }
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
                        text = "Add Menu",
                        style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.Black, fontFamily = myFont
                        )
                    )
                },
                onClick = {
                    onMenuCreated()
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
                            hint = "Menu title",
                            isError = false,
                            textValue = "",
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
                            hint = "Menu price",
                            isError = false,
                            textValue = "",
                            leadingIcon = {
                                Text(
                                    text = "Rp.",
                                    style = TextStyle(
                                        fontFamily = myFont,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                            },
                            onNewValue = {}
                        )
                    }
                    item {
                        TextArea(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            hint = "Menu description",
                            textValue = "",
                            onNewValue = {}
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun CreateMenuScreenPrev() {
    CreateMenuScreen(
        onNavigateBack = {

        },
        onMenuCreated = {

        }
    )
}