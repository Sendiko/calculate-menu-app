package com.sendiko.calcmenus.ui.screens.employee

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.Yellowyellow
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuScreen(
    onPlaceOrder: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "Employee",
                headerIcon = {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(100)
                            )
                            .clip(RoundedCornerShape(100))
                            .background(NotWhite),
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                tint = LessGray,
                                contentDescription = "Profile"
                            )
                        }
                    }
                },
                trailingIcon = {
                    SmallOutlineButton(
                        text = "On Going Orders",
                        background = Yellowyellow,
                        onClick = {

                        }
                    )
                }
            )
        }
    ) {

    }
}

@Preview
@Composable
fun MenuScreenPrev() {
    Surface(
        color = PrimaryRed
    ){
        MenuScreen(
            onPlaceOrder = {

            }
        )
    }
}