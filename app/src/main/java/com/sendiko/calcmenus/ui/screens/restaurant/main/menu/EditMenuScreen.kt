package com.sendiko.calcmenus.ui.screens.restaurant.main.menu

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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.components.textfields.TextArea
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@Composable
fun EditMenuScreen(
    onNavigateBack: (route: String) -> Unit,
    onMenuUpdated: () -> Unit
) {
    Scaffold(
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "Edit Menu",
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
                    onMenuUpdated()
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
                            IconButton(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10))
                                    .background(Color.Gray)
                                    .aspectRatio(1f)
                                    .weight(2f),
                                onClick = { onMenuUpdated() },
                                content = {
                                    Icon(
                                        modifier = Modifier.padding(48.dp).fillMaxSize(),
                                        imageVector = Icons.Filled.AddAPhoto,
                                        contentDescription = "Add picture"
                                    )
                                }
                            )
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