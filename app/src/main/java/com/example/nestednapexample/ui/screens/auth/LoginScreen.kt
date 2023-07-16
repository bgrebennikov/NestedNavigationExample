package com.example.nestednapexample.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nestednapexample.R
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun LoginScreen(
    navController: NavController = rememberNavController()
) {

    var loginValue by remember {
        mutableStateOf("")
    }

    var passwordValue by remember {
        mutableStateOf("")
    }

    var loginErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var passwordErrorText by rememberSaveable {
        mutableStateOf("")
    }

    var hasErrors by rememberSaveable {
        mutableStateOf(false)
    }

    fun validateInput() {
        if (loginValue.length < 5) {
            loginErrorText = "Incorrect login"
        }
        if (passwordValue.length < 8) {
            passwordErrorText = "Incorrect password"
        }

        hasErrors = loginErrorText.isNotEmpty() || passwordErrorText.isNotEmpty()
    }



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 12.dp)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.login_subtitle),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(36.dp))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .background(Color.White)
                .fillMaxWidth()
                .padding(all = 12.dp)
        ) {
            OutlinedTextField(
                value = loginValue,
                label = {
                    Text(text = "Enter your login")
                },
                isError = loginErrorText.isNotEmpty(),
                supportingText = {
                    if (loginErrorText.isNotEmpty()) {
                        Text(text = loginErrorText)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    loginValue = it
                    loginErrorText = ""
                }
            )

            OutlinedTextField(
                value = passwordValue,
                label = {
                    Text(text = "Enter your password")
                },
                isError = passwordErrorText.isNotEmpty(),
                supportingText = {
                    if (passwordErrorText.isNotEmpty()) {
                        Text(text = passwordErrorText)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    passwordValue = it
                    passwordErrorText = ""
                }
            )

            Button(
                shape = RoundedCornerShape(12.dp),
                colors = buttonColors(Color(0xff0362fc)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                onClick = {
                    validateInput()
                    if (!hasErrors) navController.navigate("home") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                }) {
                Text(text = "Sign In")
            }

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Forgot password", style = MaterialTheme.typography.titleSmall,
                textDecoration = TextDecoration.Underline,
                color = Color(0xff0362fc),
                modifier = Modifier.clickable {
                    navController.navigate("restore_password_screen")
                }
            )
        }
    }
}