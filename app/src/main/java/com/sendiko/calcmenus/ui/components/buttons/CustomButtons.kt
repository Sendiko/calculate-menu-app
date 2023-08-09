package com.sendiko.calcmenus.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.calcmenus.ui.components.buttons.ButtonSize.*
import com.sendiko.calcmenus.ui.components.buttons.IconInButtonPosition.*
import com.sendiko.calcmenus.ui.theme.LessGray
import com.sendiko.calcmenus.ui.theme.NotWhite
import com.sendiko.calcmenus.ui.theme.PrimaryRed
import com.sendiko.calcmenus.ui.theme.myFont

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = REGULAR,
    icon: ImageVector? = null,
    iconPosition: IconInButtonPosition = BeforeText,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryRed,
            contentColor = NotWhite
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (icon!= null && iconPosition == BeforeText){
                    Icon(imageVector = icon, contentDescription = null)
                }
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = when (buttonSize) {
                            BIG -> 18.sp
                            REGULAR -> 14.sp
                            SMALL -> 12.sp
                        },
                        fontFamily = myFont,
                        fontWeight = FontWeight.Bold
                    )
                )
                if (icon != null && iconPosition == AfterText) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = REGULAR,
    icon: ImageVector? = null,
    iconPosition: IconInButtonPosition = BeforeText,
    text: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(color = PrimaryRed, width = 1.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = NotWhite,
            contentColor = PrimaryRed
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                if (icon!= null && iconPosition == BeforeText){
                    Icon(imageVector = icon, contentDescription = null)
                }
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = when (buttonSize) {
                            BIG -> 18.sp
                            REGULAR -> 14.sp
                            SMALL -> 12.sp
                        },
                        fontFamily = myFont,
                        fontWeight = FontWeight.Bold
                    )
                )
                if (icon != null && iconPosition == AfterText) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallOutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    AssistChip(
        modifier = modifier,
        onClick = onClick,
        border = AssistChipDefaults.assistChipBorder(
            borderColor = LessGray,
            borderWidth = 1.dp,
        ),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = NotWhite,
            labelColor = LessGray,
            leadingIconContentColor = LessGray,
            trailingIconContentColor = LessGray
        ),
        shape = RoundedCornerShape(100),
        trailingIcon = trailingIcon,
        label = {
            Text(
                text = text,
                fontFamily = myFont,
                fontWeight = FontWeight.Bold
            )
        }
    )
}


@Preview
@Composable
fun CustomButtonPrev() {
    Surface(
        color = NotWhite,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 64.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
                text = "Login as Employee",
                modifier = Modifier.fillMaxWidth(),
                buttonSize = BIG,
                onClick = {}
            )
            OutlineButton(
                text = "Resto owner? Click here!",
                modifier = Modifier.fillMaxWidth(),
                buttonSize = BIG,
                onClick = {}
            )
            SmallOutlineButton(
                text = "Add",
                onClick = {}
            )
        }
    }
}