package com.sendiko.calcmenus.core.ui.components.appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.core.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.core.theme.LessGray
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.core.theme.Yellowyellow
import com.sendiko.calcmenus.core.theme.myFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    headerIcon: @Composable (() -> Unit),
    trailingIcon: @Composable (RowScope.() -> Unit),
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryRed,
            scrolledContainerColor = PrimaryRed,
            titleContentColor = NotWhite
        ),
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = myFont,
                    fontSize = 20.sp
                )
            )
        },
        navigationIcon = headerIcon,
        actions = trailingIcon
    )
}

@Preview
@Composable
fun CustomAppBarsPrev() {
    Surface(
        color = PrimaryRed
    ) {
        CustomAppBar(
            title = "Employee",
            headerIcon = {
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(100))
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
}