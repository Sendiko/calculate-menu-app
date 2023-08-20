package com.sendiko.calcmenus.ui.screens.employee.menu_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.buttons.SelectableOutlineButton
import com.sendiko.calcmenus.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.ui.components.menu.MenuCard
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.employee.menu_screen.MenuTypeList.menuTypeList
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
    var menuSelection by rememberSaveable {
        mutableStateOf(0)
    }
    var selectedMenu = mutableListOf<IntArray>()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryRed,
        floatingActionButton = {
            when{
                selectedMenu.isNotEmpty() -> FloatingActionButton(
                    containerColor = PrimaryRed,
                    contentColor = NotWhite,
                    shape = RoundedCornerShape(100),
                    content = {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Place Order",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                        )
                    },
                    onClick = { /*TODO*/ }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        hint = "Search",
                        isError = false,
                        textValue = "",
                        onNewValue = {},
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                        }
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        menuTypeList.forEachIndexed { index, menuType ->
                            SelectableOutlineButton(
                                modifier = Modifier.weight(1f),
                                text = menuType.type,
                                isSelected = menuSelection == index,
                                onClick = {
                                    menuSelection = index
                                }
                            )
                        }
                        IconButton(
                            onClick = { /*TODO*/ },
                            content = {
                                Icon(
                                    imageVector = Icons.Filled.SortByAlpha,
                                    contentDescription = "Sort Content"
                                )
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .clip(
                            RoundedCornerShape(
                                topStartPercent = 10, topEndPercent = 10
                            )
                        )
                        .background(NotWhite),
                ) {
                    this@Column.AnimatedVisibility(
                        visible = menuSelection == 0
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp, top = 16.dp),
                                    text = "Menu",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Black,
                                        color = PrimaryRed,
                                        fontSize = 32.sp
                                    )
                                )
                            }
                            items(count = 30) {
                                MenuCard(
                                    imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.Dd560SUzCN5UNP_E8hkbJQHaE6%26pid%3DApi&f=1&ipt=d2bc437090590052ad0108f8ea6354466a0738f84e9334c5d7e2eb96c7e5eb4f&ipo=images",
                                    title = "French fries $it",
                                    description = "Kentang kualitan tinggi yang di goreng sempurna $it",
                                    price = "Rp. 12.500",
                                    amount = 9
                                )
                            }
                        }
                    }
                    this@Column.AnimatedVisibility(
                        visible = menuSelection == 1
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp, top = 16.dp),
                                    text = "Menu",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Black,
                                        color = PrimaryRed,
                                        fontSize = 32.sp
                                    )
                                )
                            }
                            items(count = 30) {
                                MenuCard(
                                    imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi0.wp.com%2Fi.gojekapi.com%2Fdarkroom%2Fgofood-indonesia%2Fv2%2Fimages%2Fuploads%2F7940780d-1f6e-45a9-afee-5bfc8644c9af.jpg&f=1&nofb=1&ipt=096540f75f2332b259c906bb0a5a2c9905adb3cc07fa2910c9fdf1eeffbe44fc&ipo=images",
                                    title = "Milkmax Rasa $it",
                                    description = "Susu sapi segar dipadukan dengan $it",
                                    price = "Rp. 15.000"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPrev() {
    Surface(
        color = PrimaryRed
    ) {
        MenuScreen(
            onPlaceOrder = {

            }
        )
    }
}