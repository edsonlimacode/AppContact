package com.edsonlimadev.appcontact.ui.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.ui.components.CustomTextField
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray500
import com.edsonlimadev.appcontact.ui.theme.Gray700
import com.edsonlimadev.appcontact.ui.theme.Violet500

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onClickLogin: () -> Unit,
    navigateToRegister: () -> Unit = {},
    navigateToHome: () -> Unit = {},
    toastMessage: LoginErrorUiEvent
) {

    val context = LocalContext.current

    LaunchedEffect(toastMessage) {
        when (toastMessage) {
            is LoginErrorUiEvent.Success -> {
                navigateToHome()
            }

            is LoginErrorUiEvent.Error -> {
                Toast.makeText(context, toastMessage.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark900),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            AsyncImage(
                modifier = Modifier
                    .width(36.dp)
                    .height(40.dp),
                model = R.drawable.ic_login_logo,
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(bottom = 50.dp),
                text = "Login",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Email",
                value = uiState.email,
                onValueChange = {
                    uiState.onEmailChanged(it)
                },
                keyboardOptions = KeyboardType.Email
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                value = uiState.password,
                onValueChange = {
                    uiState.onPasswordChanged(it)
                },
                maxLines = 1,
                singleLine = true,
                placeholder = {
                    Text(text = "Senha")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Gray700,
                    unfocusedBorderColor = Gray700,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (passwordVisible) R.drawable.ic_visibility_24 else R.drawable.ic_visibility_off_24
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = painterResource(image), contentDescription = null)
                    }
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .heightIn(min = 48.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = onClickLogin,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Violet500,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Entrar")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Não tem uma conta?",
                    fontSize = 16.sp,
                    color = Gray500
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            navigateToRegister()
                        },
                    text = "Cadastre-se",
                    fontSize = 16.sp,
                    color = Gray500
                )
            }

        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        uiState = LoginUiState(),
        toastMessage = LoginErrorUiEvent(),
        onClickLogin = {},
        navigateToRegister = {},
        navigateToHome = {}
    )
}