package com.sendiko.calcmenus.employee.post_screen.presentation

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sendiko.calcmenus.R
import com.sendiko.calcmenus.core.navigation.Routes
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.core.theme.myFont

@Composable
fun PostDeliverScreen(
    navController: NavController = rememberNavController()
) {
    var isVisible by remember {
        mutableStateOf(false)
    }

    Scaffold(
        containerColor = PrimaryRed
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Handler(Looper.myLooper()!!).postDelayed({
                isVisible = true
            }, 400)
            Handler(Looper.myLooper()!!).postDelayed({
                navController.navigate(
                    route = Routes.EmployeeOngoingOrdersScreen.route
                ) {
                    popUpTo(
                        navController.graph.id,
                    ) { inclusive = true }
                }
            }, 1500)

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.checkmark),
                    modifier = Modifier.size(256.dp),
                    contentDescription = "Checkmark",
                    tint = NotWhite,
                )
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(),
            ) {
                Text(
                    text = "Order delivered!",
                    style = TextStyle(
                        fontFamily = myFont,
                        fontSize = 32.sp,
                        color = NotWhite,
                    )
                )
            }
        }
    }
}