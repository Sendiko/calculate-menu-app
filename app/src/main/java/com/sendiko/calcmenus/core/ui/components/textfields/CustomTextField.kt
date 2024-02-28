@file:OptIn(ExperimentalMaterial3Api::class)

package com.sendiko.calcmenus.core.ui.components.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sendiko.calcmenus.core.theme.NotWhite
import com.sendiko.calcmenus.core.theme.PrimaryRed
import com.sendiko.calcmenus.core.theme.myFont

/**
 * @author Sendiko
 * This is my own Custom Outlined TextField, i made this to differentiate
 * with the material 3's Outlined TextField
 *
 * @param modifier to modify the component
 * @param hint for the gray text that didn't move updwards when focused
 * @param leadingIcon pretty self-explanatory
 * @param trailingIcon pretty self-explanatory
 * @param prefix for additional text beside the leadingIcon
 * @param suffix for additional text beside traliningIcon
 * @param isPasswordVisible for password visibility, false by default
 * @param isError to show if there's any error
 * @param supportingText for extra hint or to show error message
 * @param enabled if the textfield is enabled, true by default
 * @param keyboardActions actions for the keyboard
 * @param keyboardOptions what type of keyboard used
 * @param textValue the value of the text
 * @param onNewValue to update the textValue
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    hint: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    isPasswordVisible: Boolean = true,
    isError: Boolean,
    supportingText: String? = null,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    textValue: String,
    onNewValue: (newValue: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = textValue,
        onValueChange = onNewValue,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(100),
        singleLine = true,
        isError = isError,
        prefix = prefix,
        suffix = suffix,
        enabled = enabled,
        supportingText = {
            supportingText?.let { Text(it) }
        },
        textStyle = TextStyle(
            fontFamily = myFont,
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryRed,
            focusedTrailingIconColor = PrimaryRed,
            focusedLeadingIconColor = PrimaryRed,
            focusedPrefixColor = PrimaryRed,
            focusedSuffixColor = PrimaryRed,
            containerColor = NotWhite
        ),
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontFamily = myFont,
                )
            )
        },
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else PasswordVisualTransformation(),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilledTextField(
    modifier: Modifier = Modifier,
    hint: String,
    passwordField: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    textValue: String,
    onNewValue: (newValue: String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = textValue,
        onValueChange = onNewValue,
        shape = TextFieldDefaults.filledShape,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = myFont,
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedLeadingIconColor = PrimaryRed,
            focusedTrailingIconColor = PrimaryRed,
            containerColor = NotWhite
        ),
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontFamily = myFont,
                )
            )
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTextArea(
    modifier: Modifier = Modifier,
    hint: String,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    textValue: String,
    onNewValue: (newValue: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = textValue,
        onValueChange = onNewValue,
        shape = RoundedCornerShape(15),
        singleLine = false,
        textStyle = TextStyle(
            fontFamily = myFont,
        ),
        minLines = 3,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryRed,
            focusedTrailingIconColor = PrimaryRed,
            focusedLeadingIconColor = PrimaryRed,
            focusedPrefixColor = PrimaryRed,
            focusedSuffixColor = PrimaryRed,
            containerColor = NotWhite
        ),
        placeholder = {
            Text(
                hint,
                style = TextStyle(
                    textAlign = TextAlign.Right,
                    fontFamily = myFont
                )
            )
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextArea(
    modifier: Modifier = Modifier,
    hint: String,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    textValue: String,
    onNewValue: (newValue: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = textValue,
        onValueChange = onNewValue,
        shape = RoundedCornerShape(15),
        singleLine = false,
        textStyle = TextStyle(
            fontFamily = myFont,
        ),
        minLines = 5,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryRed,
            focusedTrailingIconColor = PrimaryRed,
            focusedLeadingIconColor = PrimaryRed,
            focusedPrefixColor = PrimaryRed,
            focusedSuffixColor = PrimaryRed,
            containerColor = NotWhite
        ),
        placeholder = {
            Text(
                hint,
                style = TextStyle(
                    textAlign = TextAlign.Right,
                    fontFamily = myFont
                )
            )
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
    )
}

@Preview
@Composable
fun CustomTextFieldPrev() {
    Surface(
        color = NotWhite
    ) {
        val text = remember {
            mutableStateOf("")
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                textValue = text.value,
                onNewValue = {
                    text.value = it
                },
                hint = "text",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                isError = false
            )
            FilledTextField(
                modifier = Modifier.wrapContentWidth(),
                textValue = text.value,
                onNewValue = {
                    text.value = it
                },
                hint = "1",
            )
        }
    }
}