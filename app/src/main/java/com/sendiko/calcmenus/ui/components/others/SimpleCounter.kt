package com.sendiko.calcmenus.ui.components.others

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.myFont

@Composable
fun SimpleCounter(
    modifier: Modifier = Modifier,
    amount: Int,
    fillContainer: Boolean = false,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = if(fillContainer) Arrangement.SpaceEvenly else Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.size(32.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = NotWhite
            ),
            content = {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Minus",
                )
            },
            onClick = {

            }
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(NotWhite)
                .size(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = amount.toString(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = myFont
                )
            )
        }
        IconButton(
            modifier = Modifier.size(32.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = NotWhite
            ),
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            },
            onClick = {

            }
        )
    }
}