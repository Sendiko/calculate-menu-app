package com.sendiko.calcmenus.ui.components.employee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.sendiko.calcmenus.ui.components.buttons.IconInButtonPosition
import com.sendiko.calcmenus.ui.components.buttons.OutlineButton
import com.sendiko.calcmenus.ui.components.menu.defaultImageUrl
import com.sendiko.calcmenus.ui.theme.CardBackgroundColor
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@Composable
fun EmployeeCard(
    modifier: Modifier = Modifier,
    imageUrl: String = defaultImageUrl,
    contentDescription: String? = "",
    title: String,
    onNextClick: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = CardBackgroundColor
        )
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .weight(2f)
                .padding(8.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(5)),
            model = imageUrl,
            contentDescription = contentDescription,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.padding(32.dp),
                    color = PrimaryRed,
                    strokeCap = StrokeCap.Butt
                )
            },
            error = {
              Icon(
                  modifier = Modifier.padding(16.dp),
                  imageVector = Icons.Filled.SignalWifiConnectedNoInternet4,
                  contentDescription = "No Connection"
              )
            },
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            OutlineButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = "See details",
                onClick = onNextClick,
                icon = Icons.Filled.KeyboardArrowRight,
                iconPosition = IconInButtonPosition.AfterText
            )
        }
    }
}

@Preview
@Composable
fun EmployeeCardPrev() {
    EmployeeCard(
        title = "Nama",
        onNextClick = {

        }
    )
}