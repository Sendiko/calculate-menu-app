package com.sendiko.calcmenus.employee.menu.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.employee.menu.data.MenusItem
import com.sendiko.calcmenus.core.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.core.ui.components.buttons.SelectableOutlineButton
import com.sendiko.calcmenus.core.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.core.ui.components.menu.MenuCard
import com.sendiko.calcmenus.core.ui.components.others.ErrorMessageView
import com.sendiko.calcmenus.core.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.core.navigation.Routes
import com.sendiko.calcmenus.employee.menu.presentation.MenuTypeList.menuTypeList
import com.sendiko.calcmenus.core.theme.LessGray
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.core.theme.Yellowyellow
import com.sendiko.calcmenus.core.theme.myFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuScreen(
    state: MenuScreenState,
    onEvent: (MenuScreenEvent) -> Unit,
    onNavigate: (
        route: String,
        orderedMenuList: List<MenusItem>
    ) -> Unit
) {
    val enterCardAnimation = expandHorizontally(tween(durationMillis = 700, easing = EaseInOut))
    val exitCardAnimation = shrinkHorizontally(tween(durationMillis = 700, easing = EaseInOut))
    LaunchedEffect(
        key1 = state.menuList,
        block = {
            if (state.menuList == emptyList<MenusItem>()) {
                onEvent(MenuScreenEvent.RequestMenuData(state.token))
            }
        }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryRed,
        floatingActionButton = {
            FloatingActionButton(
                containerColor = PrimaryRed,
                contentColor = NotWhite,
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
                onClick = {
                    onNavigate(Routes.EmployeeOrderResumeScreen.route, state.orderedMenuList)
                    onEvent(MenuScreenEvent.OnPlaceOrder)
                }
            )
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
                            onClick = {
                                onNavigate(
                                    Routes.EmployeeProfileScreen.route,
                                    state.orderedMenuList
                                )
                            }
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
                            onNavigate(
                                Routes.EmployeeOngoingOrdersScreen.route,
                                state.orderedMenuList
                            )
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // SearchBar
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                hint = "Search",
                isError = false,
                keyboardOptions = KeyboardOptions().copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { TODO("apply search") }
                ),
                textValue = state.searchText,
                onNewValue = { value ->
                    onEvent(MenuScreenEvent.OnSearchInput(value))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
            )
            // MenuButton
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                menuTypeList.forEachIndexed { index, menuType ->
                    SelectableOutlineButton(
                        modifier = Modifier.weight(1f),
                        text = menuType.type,
                        isSelected = state.currentPage == index,
                        onClick = {
                            onEvent(MenuScreenEvent.SwitchPages(index))
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

            LazyColumn(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 8,
                            topEndPercent = 8
                        )
                    )
                    .background(NotWhite)
                    .fillMaxSize(),
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    item {
                        ErrorMessageView(
                            errorMessage = state.failedState.failedMessage.toString(),
                            isVisible = state.failedState.isFailed
                        )
                    }
                    item {
                        AnimatedVisibility(visible = state.isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    if (state.menuList != null){
                        val food = state.menuList.filter { it!!.category == "food" }
                        val beverage = state.menuList.filter { it!!.category == "beverage" }
                        items(if (state.currentPage == 0) food else beverage) { menu ->
                            AnimatedVisibility(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                                visible = state.currentPage == 0,
                                enter = enterCardAnimation,
                                exit = exitCardAnimation
                            ) {
                                if (menu != null) {
                                    MenuCard(
                                        title = menu.name!!,
                                        description = menu.description!!,
                                        price = menu.price!!.toString(),
                                        imageUrl = menu.imageUrl!!,
                                        amount = state.orderedMenuList.count { amount ->
                                            amount == menu
                                        },
                                        onButtonClick = {
                                            onEvent(MenuScreenEvent.OnAddMenuToList(menu))
                                        },
                                        onMinusClick = {
                                            onEvent(MenuScreenEvent.OnRemoveMenuFromList(menu))
                                        },
                                        onPlusClick = {
                                            onEvent(MenuScreenEvent.OnAddMenuToList(menu))
                                        }
                                    )
                                }
                            }
                            AnimatedVisibility(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                                visible = state.currentPage == 1,
                                enter = enterCardAnimation,
                                exit = exitCardAnimation
                            ) {
                                MenuCard(
                                    title = menu?.name!!,
                                    description = menu.description!!,
                                    price = menu.price!!.toString(),
                                    imageUrl = menu.imageUrl!!,
                                    amount = state.orderedMenuList.count { amount ->
                                        amount == menu
                                    },
                                    onButtonClick = {
                                        onEvent(MenuScreenEvent.OnAddMenuToList(menu))
                                    },
                                    onMinusClick = {
                                        onEvent(MenuScreenEvent.OnRemoveMenuFromList(menu))
                                    },
                                    onPlusClick = {
                                        onEvent(MenuScreenEvent.OnAddMenuToList(menu))
                                    }
                                )
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(72.dp))
                        }
                    }
                }
            )
        }
    }
}
