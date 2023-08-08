package com.sendiko.calcmenus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.R

val myFont = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.lato_black,
            weight = FontWeight.ExtraBold
        ),
        Font(
            resId = R.font.lato_blackitalic,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.lato_bold,
            weight = FontWeight.ExtraBold
        ),
        Font(
            resId = R.font.lato_bolditalic,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.lato_regular,
            weight = FontWeight.ExtraBold,
        ),
        Font(
            resId = R.font.lato_regular,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.lato_light,
            weight = FontWeight.ExtraBold
        ),
        Font(
            resId = R.font.lato_lightitalic,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.lato_thin,
            weight = FontWeight.ExtraBold
        ),
        Font(
            resId = R.font.lato_thinitalic,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)