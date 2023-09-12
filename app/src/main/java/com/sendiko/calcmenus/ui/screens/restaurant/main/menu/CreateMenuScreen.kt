package com.sendiko.calcmenus.ui.screens.restaurant.main.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.components.textfields.TextArea
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@Composable
fun CreateMenuScreen() {
    Scaffold(
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "Add Menu",
                headerIcon = {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = NotWhite
                    )
                },
                trailingIcon = { }
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
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f))
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .weight(2f)
                        .padding(8.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(5))
                        .align(Alignment.CenterVertically),
                    model = "192.168.1.8:8000/storage/images/menu/MbaC4FwN5iRwoSAVgmk3ORcVcsiumkXw93I9Fd9s.jpg",
                    contentDescription = "null",
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(32.dp),
                            color = PrimaryRed,
                            strokeCap = StrokeCap.Butt
                        )
                    },
                    error = {
                        Icon(
                            imageVector = Icons.Filled.SignalWifiConnectedNoInternet4,
                            contentDescription = "No Connection"
                        )
                    },
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier.weight(1f))
            }
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
}

@Preview
@Composable
fun CreateMenuScreenPrev() {
    CreateMenuScreen()
}