package com.sendiko.calcmenus.ui.screens.employee.order_resume

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.ui.components.others.SimpleCounter
import com.sendiko.calcmenus.ui.components.textfields.SmallTextArea
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderResumeScreen(
    onPlaceOrder: () -> Unit,
    onAddMoreMenu: (route: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryRed,
        floatingActionButton = {
            FloatingActionButton(
                containerColor = NotWhite,
                contentColor = PrimaryRed,
                shape = RoundedCornerShape(100),
                content = {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Place Order",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = myFont
                        )
                    )
                },
                onClick = { /*TODO*/ }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        topBar = {
            CustomAppBar(
                title = "Order Resume",
                headerIcon = {
                    IconButton(
                        onClick = {
                            onAddMoreMenu(Routes.EmployeeMenuScreen.route)
                        },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowLeft,
                                tint = NotWhite,
                                contentDescription = "Back"
                            )
                        }
                    )
                },
                trailingIcon = {

                }
            )
        }
    ) { paddingValues ->
        var notes by rememberSaveable {
            mutableStateOf(false)
        }
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 108.dp
                )
                .clip(RoundedCornerShape(4))
                .background(NotWhite),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(16.dp),
            content = {
                items(count = 5) {
                    Column {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Title",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = myFont
                                    )
                                )
                                Text(
                                    text = "Lorem ipsum dolor sit amet, Aenean commodo ligula eget dolor. Aenean massa.",
                                    style = TextStyle(
                                        fontFamily = myFont,
                                        fontWeight = FontWeight.Thin
                                    )
                                )
                                if (notes) {
                                    SmallTextArea(
                                        modifier = Modifier.padding(top = 4.dp, end = 4.dp),
                                        hint = "separate notes using '|'",
                                        textValue = "",
                                        onNewValue = {

                                        }
                                    )
                                } else {
                                    SmallOutlineButton(
                                        text = "Add",
                                        onClick = {
                                            notes = true
                                        },
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Filled.NoteAdd,
                                                contentDescription = "Add Note"
                                            )
                                        }
                                    )
                                }
                            }
                            SimpleCounter(
                                amount = it,
                                fillContainer = false,
                                onMinusClick = { /*TODO*/ },
                                onPlusClick = { /*TODO*/ }
                            )
                        }
                        Divider(modifier = Modifier.padding(8.dp), thickness = 2.dp)
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            onClick = {
                                onAddMoreMenu(Routes.EmployeeMenuScreen.route)
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add Menu",
                                    tint = LessGray
                                )
                                Text(
                                    text = "Add more menu",
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        color = LessGray,
                                        fontFamily = myFont
                                    ),
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        )
                    }
                    Divider(modifier = Modifier.padding(8.dp), thickness = 2.dp)
                }
            }
        )
    }

}

@Preview
@Composable
fun OrderResumePrev() {
    OrderResumeScreen(
        onAddMoreMenu = {

        },
        onPlaceOrder = {

        }
    )
}