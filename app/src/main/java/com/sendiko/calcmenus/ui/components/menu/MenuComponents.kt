package com.sendiko.calcmenus.ui.components.menu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.sendiko.calcmenus.ui.components.buttons.SmallOutlineButton
import com.sendiko.calcmenus.ui.components.others.SimpleCounter
import com.sendiko.calcmenus.ui.theme.CardBackgroundColor
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

const val defaultImageUrl =
    "http://192.168.100.12:8000/storage/images/menu/MbaC4FwN5iRwoSAVgmk3ORcVcsiumkXw93I9Fd9s.jpg"

@Composable
fun MenuCard(
    imageUrl: String = defaultImageUrl,
    contentDescription: String? = "",
    title: String,
    description: String,
    price: String,
    amount: Int = 0,
    onAddButtonClick: () -> Unit,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = CardBackgroundColor,
            contentColor = LessGray
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(32.dp),
                        color = PrimaryRed,
                        strokeCap = StrokeCap.Butt
                    )
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(4f)
                    .padding(8.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(5))
            )
            Column(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight()
                    .padding(top = 8.dp, end = 8.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            fontFamily = myFont
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(), text = description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start,
                            fontFamily = myFont
                        )
                    )
                }

                AnimatedVisibility(visible = amount > 1) {
                    SimpleCounter(
                        modifier = Modifier.padding(top = 16.dp),
                        amount = amount,
                        onMinusClick = onMinusClick,
                        onPlusClick = onPlusClick
                    )
                }

                AnimatedVisibility(visible = amount == 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                    SmallOutlineButton(
                        text = "Add",
                        onClick = onAddButtonClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MenuComponentPrev() {
    Surface(
        color = NotWhite,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MenuCard(
                title = "Beautiful Boobies",
                description = "My girlfriend's big beautiful boobies",
                price = "priceless",
                amount = 4,
                onPlusClick = {

                },
                onMinusClick = {

                },
                onAddButtonClick = {

                }
            )
        }
    }
}