package com.sendiko.calcmenus.employee.on_going_order.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.core.ui.components.buttons.PrimaryButton
import com.sendiko.calcmenus.core.theme.CardBackgroundColor
import com.sendiko.calcmenus.core.theme.LessGray
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.myFont
import com.sendiko.calcmenus.core.utils.formatCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnGoingOrderCard(
    tableNumber: String,
    transactionNumber: String,
    totalPrice: String,
    textButton: String,
    onCardClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = CardBackgroundColor,
            contentColor = LessGray
        ),
        onClick = onCardClick
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$tableNumber - #$transactionNumber",
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = formatCurrency(amount = totalPrice),
                style = TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    fontFamily = myFont
                )
            )
            PrimaryButton(
                text = textButton,
                onClick = onButtonClick
            )
        }
    }
}

@Preview
@Composable
fun OGODPrev() {
    Surface(
        color = NotWhite,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            OnGoingOrderCard(
                tableNumber = "Table A3",
                transactionNumber = "1234567890",
                totalPrice = "125000",
                textButton = "",
                onCardClick = {

                },
                onButtonClick = {

                }
            )
        }
    }
}