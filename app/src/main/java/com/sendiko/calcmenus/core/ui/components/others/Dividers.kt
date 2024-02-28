package com.sendiko.calcmenus.core.ui.components.others

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.core.theme.LessGray
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.myFont

@Composable
fun OrDividers(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Divider(
            modifier = Modifier
                .weight(3f)
                .padding(vertical = 10.dp),
            color = LessGray
        )
        Text(
            text = "or", style = TextStyle(
                color = LessGray,
                fontSize = 14.sp,
                fontFamily = myFont
            )
        )
        Divider(
            modifier = Modifier
                .weight(3f)
                .padding(vertical = 10.dp),
            color = LessGray
        )
    }
}

@Preview
@Composable
fun OtherCompPrev() {
    Surface(
        color = NotWhite
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 64.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OrDividers()
        }
    }
}