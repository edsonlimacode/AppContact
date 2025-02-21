package com.edsonlimadev.appcontact.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.edsonlimadev.appcontact.ui.theme.Gray700


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Text(text = placeholder)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Gray700,
            unfocusedBorderColor = Gray700,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardOptions
        )
    )

}