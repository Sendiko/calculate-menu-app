package com.sendiko.calcmenus.ui.screens.employee.menu_screen

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.sendiko.calcmenus.remote.responses.MenusItem
import com.sendiko.calcmenus.ui.components.appbars.CustomAppBar
import com.sendiko.calcmenus.ui.components.buttons.SelectableOutlineButton
import com.sendiko.calcmenus.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.ui.components.menu.MenuCard
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.screens.employee.menu_screen.MenuTypeList.menuTypeList
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.Yellowyellow
import com.sendiko.calcmenus.ui.theme.myFont

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuScreen(
    state: MenuScreenState,
    onEvent: (MenuScreenEvent) -> Unit,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(
        key1 = state.menuList,
        block = {
            when (state.menuList) {
                emptyList<MenusItem>() -> {
                    onEvent(MenuScreenEvent.RequestMenuData(state.token))
                }
            }
        }
    )
    var foods = state.menuList?.filter { it?.category == "food" }
    var beverages = state.menuList?.filter { it?.category == "beverage" }
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
                    onNavigate(Routes.EmployeeOrderResumeScreen.route)
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
                                onNavigate(Routes.EmployeeProfileScreen.route)
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
                            onNavigate(Routes.EmployeeOngoingOrdersScreen.route)
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
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
                    onSearch = {
                        when (state.currentPage) {
                            0 -> {
                                foods = state.menuList?.filter { menus ->
                                    menus?.name == state.searchText
                                }
                            }

                            1 -> {
                                beverages = state.menuList?.filter { menus ->
                                    menus?.name == state.searchText
                                }
                            }
                        }
                    }
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
                content = {
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
                    item {
                        AnimatedVisibility(visible = !state.isLoading) {
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
                    }
                    items(foods!!) { food ->
                        Spacer(modifier = Modifier.height(8.dp))
                        AnimatedVisibility(
                            visible = state.currentPage == 0 && !state.isLoading,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            if (food != null) {
                                MenuCard(
                                    title = food.name.toString(),
                                    description = food.description.toString(),
                                    imageUrl = food.imageUrl.toString(),
                                    price = food.price.toString(),
                                    amount = state.orderedMenuList.count { amount ->
                                        amount == food.name!!
                                    },
                                    onButtonClick = {
                                        onEvent(MenuScreenEvent.OnAddMenuToList(food.name!!))
                                    },
                                    onMinusClick = {
                                        onEvent(MenuScreenEvent.OnRemoveMenuFromList(food.name!!))
                                    },
                                    onPlusClick = {
                                        Log.i("MENU_SCREEN_EVENT", "MenuScreen: onPlusClick")
                                        onEvent(MenuScreenEvent.OnAddMenuToList(food.name!!))
                                    }
                                )
                            }
                        }
                    }

                    items(beverages!!) { beverage ->
                        Spacer(modifier = Modifier.height(8.dp))
                        AnimatedVisibility(
                            visible = state.currentPage == 1 && !state.isLoading,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            if (beverage != null) {
                                MenuCard(
                                    title = beverage.name.toString(),
                                    description = beverage.description.toString(),
                                    price = beverage.price.toString(),
                                    imageUrl = beverage.imageUrl.toString(),
                                    amount = state.orderedMenuList.count { amount ->
                                        amount == beverage.name!!
                                    },
                                    onButtonClick = {
                                        onEvent(MenuScreenEvent.OnAddMenuToList(beverage.name!!))
                                    },
                                    onMinusClick = {
                                        onEvent(MenuScreenEvent.OnRemoveMenuFromList(beverage.name!!))
                                    },
                                    onPlusClick = {
                                        Log.i("MENU_SCREEN_EVENT", "MenuScreen: onPlusClick")
                                        onEvent(MenuScreenEvent.OnAddMenuToList(beverage.name!!))
                                    }
                                )
                            }
                        }

                    }
                }
            )

        }
    }
}
