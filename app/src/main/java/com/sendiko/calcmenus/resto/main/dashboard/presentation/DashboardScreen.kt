package com.sendiko.calcmenus.resto.main.dashboard.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.buttons.ButtonSize.*
import com.sendiko.calcmenus.ui.components.buttons.SelectableOutlineButton
import com.sendiko.calcmenus.ui.components.employee.EmployeeCard
import com.sendiko.calcmenus.ui.components.menu.MenuCard
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.employee.menu.presentation.MenuTypeList.menuTypeList
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    onCreateMenu: (route: String) -> Unit,
    onEditMenu: (route: String) -> Unit,
    onEmployeeDetails: (route: String) -> Unit,
    onCreateEmployee: (route: String) -> Unit,
    onNavigate: (route: String) -> Unit
) {
    val tab = listOf("Menu", "Employee")
    var selectedTab by rememberSaveable {
        mutableIntStateOf(0)
    }
    var selectedMenuTab by rememberSaveable {
        mutableIntStateOf(0)
    }
    val lazyGridState = rememberLazyGridState()
    val lazyState = rememberLazyListState()
    Scaffold(
        containerColor = PrimaryRed,
        topBar = {
            CustomAppBar(
                title = "Restaurant",
                headerIcon = {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp, color = Color.Black, shape = RoundedCornerShape(100)
                            )
                            .clip(RoundedCornerShape(100))
                            .background(NotWhite),
                    ) {
                        IconButton(onClick = {
                            onNavigate(Routes.RestoProfileScreen.route)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                tint = LessGray,
                                contentDescription = "Profile"
                            )
                        }
                    }
                },
                trailingIcon = { }
            )
        },
        floatingActionButtonPosition = if (selectedTab == 0) {
            FabPosition.Center
        } else FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = selectedTab == 0, enter = fadeIn(), exit = fadeOut()
            ) {
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
                            onCreateMenu(Routes.RestoCreateMenuScreen.route)
                    }
                )
            }
            AnimatedVisibility(
                visible = selectedTab == 1,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    containerColor = PrimaryRed,
                    contentColor = NotWhite,
                    shape = RoundedCornerShape(100),
                    content = {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Create Account",
                            style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight.Black, fontFamily = myFont
                            )
                        )
                    },
                    onClick = {
                        onCreateEmployee(Routes.RestoCreateEmployeeScreen.route)
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tab.forEachIndexed { index, title ->
                    SelectableOutlineButton(modifier = Modifier.weight(1f),
                        isSelected = index == selectedTab,
                        text = title,
                        buttonSize = BIG,
                        onClick = {
                            selectedTab = index
                        })
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                hint = "Search",
                isError = false,
                textValue = "",
                keyboardOptions = KeyboardOptions().copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {

                }),
                onNewValue = { },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search, contentDescription = "Search"
                    )
                },
            )
            AnimatedVisibility(visible = selectedTab == 0) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    menuTypeList.forEachIndexed { index, menuType ->
                        SelectableOutlineButton(modifier = Modifier.weight(1f),
                            text = menuType.type,
                            isSelected = selectedMenuTab == index,
                            onClick = {
                                selectedMenuTab = index
                            })
                    }
                    IconButton(onClick = { /*TODO*/ }, content = {
                        Icon(
                            imageVector = Icons.Filled.SortByAlpha,
                            contentDescription = "Sort Content"
                        )
                    })
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 24.dp, topEnd = 24.dp
                        )
                    )
                    .background(NotWhite)
            ) {
                Column {
                    AnimatedVisibility(
                        visible = selectedMenuTab == 0 && selectedTab == 0
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            state = lazyState
                        ) {
                            item {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp, top = 16.dp),
                                    text = "Menu",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Black,
                                        color = PrimaryRed,
                                        fontSize = 32.sp,
                                        fontFamily = myFont
                                    )
                                )
                            }
                            items(count = 30) {
                                MenuCard(
                                    imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.Dd560SUzCN5UNP_E8hkbJQHaE6%26pid%3DApi&f=1&ipt=d2bc437090590052ad0108f8ea6354466a0738f84e9334c5d7e2eb96c7e5eb4f&ipo=images",
                                    title = "French fries $it",
                                    description = "Kentang kualitan tinggi yang di goreng sempurna $it",
                                    price = "Rp. 12.500",
                                    amount = 9,
                                    editable = true,
                                    onPlusClick = {

                                    },
                                    onMinusClick = {

                                    },
                                    onButtonClick = {
                                        onEditMenu(Routes.RestoEditMenuScreen.route)
                                    }
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(FloatingActionButtonDefaults.LargeIconSize * 2))
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = selectedMenuTab == 1 && selectedTab == 0
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            state = lazyState
                        ) {
                            item {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp, top = 16.dp),
                                    text = "Menu",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Black,
                                        color = PrimaryRed,
                                        fontSize = 32.sp,
                                        fontFamily = myFont
                                    )
                                )
                            }
                            items(count = 30) {
                                MenuCard(
                                    imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi0.wp.com%2Fi.gojekapi.com%2Fdarkroom%2Fgofood-indonesia%2Fv2%2Fimages%2Fuploads%2F7940780d-1f6e-45a9-afee-5bfc8644c9af.jpg&f=1&nofb=1&ipt=096540f75f2332b259c906bb0a5a2c9905adb3cc07fa2910c9fdf1eeffbe44fc&ipo=images",
                                    title = "Milkmax Rasa $it",
                                    description = "Susu sapi segar dipadukan dengan $it",
                                    price = "Rp. 15.000",
                                    editable = true,
                                    onPlusClick = {

                                    },
                                    onMinusClick = {

                                    },
                                    onButtonClick = {
                                        onEditMenu(Routes.RestoEditMenuScreen.route)
                                    }
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(FloatingActionButtonDefaults.LargeIconSize * 2))
                            }
                        }
                    }
                    AnimatedVisibility(visible = selectedTab == 1) {
                        LazyHorizontalGrid(
                            state = lazyGridState,
                            rows = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(top = 16.dp, start = 16.dp, bottom = paddingValues.calculateBottomPadding(), end = 156.dp),
                            content = {
                                items(16) { index ->
                                    EmployeeCard(
                                        imageUrl = "http://192.168.1.8:8000/storage/images/menu/MbaC4FwN5iRwoSAVgmk3ORcVcsiumkXw93I9Fd9s.jpg",
                                        title = "name $index",
                                        onDetailClick = {
                                            onEmployeeDetails(Routes.RestoViewEmployeeScreen.route)
                                        }
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun DashboardPrev() {
    DashboardScreen(
        onCreateMenu = {

        },
        onEditMenu = {

        },
        onEmployeeDetails = {

        },
        onCreateEmployee = {

        },
        onNavigate = {

        }
    )
}