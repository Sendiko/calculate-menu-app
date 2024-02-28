package com.sendiko.calcmenus.resto.main.employee.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed

@Composable
fun ViewEmployeeScreen(
    onNavigateBack: (route: String) -> Unit
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
    imageUri = Uri.parse("http://192.168.1.8:8000/storage/images/menu/MbaC4FwN5iRwoSAVgmk3ORcVcsiumkXw93I9Fd9s.jpg")
    Scaffold(
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "View Account",
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
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(5))
                                    .weight(2f),
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
                            enabled = false,
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
                            enabled = false,
                            leadingIcon = {
                                Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
                            },
                            onNewValue = {}
                        )
                    }
                }
            )
        }
    }
}