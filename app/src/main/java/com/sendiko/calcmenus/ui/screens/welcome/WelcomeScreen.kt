@file:OptIn(ExperimentalMaterial3Api::class)

package com.sendiko.calcmenus.ui.screens.welcome

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.R
import com.sendiko.calcmenus.ui.components.buttons.ButtonSize.BIG
import com.sendiko.calcmenus.ui.components.buttons.OutlineButton
import com.sendiko.calcmenus.ui.components.buttons.PrimaryButton
import com.sendiko.calcmenus.ui.components.others.OrDividers
import com.sendiko.calcmenus.ui.screens.Graphs
import com.sendiko.calcmenus.ui.screens.Routes
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    onNavigate: (route: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = NotWhite
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_ill),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Column{
                    Text(
                        text = "Welcome Back!",
                        style = TextStyle(
                            fontSize = 32.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Please log in to your account",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = LessGray,
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(36.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Login as Employee",
                        buttonSize = BIG,
                        onClick = {
                            onNavigate(Routes.EmployeeLogin.route)
                        }
                    )
                    OrDividers()
                    OutlineButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Resto owner? Click Here!",
                        buttonSize = BIG,
                        onClick = {
                            onNavigate(Graphs.RestoAuthGraph.graph)
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun WelcomePrev() {
    WelcomeScreen(onNavigate = {})
}