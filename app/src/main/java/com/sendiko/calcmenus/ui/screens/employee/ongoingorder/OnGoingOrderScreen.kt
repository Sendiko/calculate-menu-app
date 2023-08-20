package com.sendiko.calcmenus.ui.screens.employee.ongoingorder

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import com.sendiko.calcmenus.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.ui.components.ongoingorder.OnGoingOrderCard
import com.sendiko.calcmenus.ui.components.textfields.OutlinedTextField
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.Yellowyellow

@Composable
fun OnGoingOrderScreen(
    onNavigate: (route: String) -> Unit
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
                        text = "Menu List",
                        background = Yellowyellow,
                        onClick = {
                            onNavigate(Routes.EmployeeMenuScreen.route)
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
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    hint = "Search",
                    isError = false,
                    textValue = "",
                    onNewValue = {},
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }
                )
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
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Text(
                                modifier = Modifier.padding(start = 10.dp, top = 16.dp),
                                text = "On going orders",
                                style = TextStyle(
                                    fontWeight = FontWeight.Black,
                                    color = PrimaryRed,
                                    fontSize = 32.sp
                                )
                            )
                        }
                        items(count = 30) { number ->
                            OnGoingOrderCard(
                                tableNumber = "Table A$number",
                                transactionNumber = "$number$number$number$number$number$number$number",
                                totalPrice = "$number$number$number$number$number$number$number",
                                onCardClick = {

                                },
                                onMarkAsPayed = {

                                }
                            )
                        }
                    }
                }
            }
        }
    }
}