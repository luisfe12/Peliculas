package com.example.fondos_pantalla2.clientescreen.ui.acercade

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.Routes.AppScreen
import com.example.fondos_pantalla2.clientescreen.clientvmodel.AcercaDeViewModel
import com.example.fondos_pantalla2.clientescreen.ui.clientViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun AdminLogin(navController: NavHostController) {
    val acercaDeViewModel: AcercaDeViewModel = AcercaDeViewModel();
    val email by acercaDeViewModel.email.observeAsState("");
    val password by acercaDeViewModel.password.observeAsState("");
    val loginEnabled by acercaDeViewModel.loginEnable.observeAsState(false);

    EmailClient(email = email) {
        acercaDeViewModel.acercaLoginClient(emailC = it, passwordC = password);
    }
    SpaceMidle(height = 15)
    PasswordClient(password = password) {
        acercaDeViewModel.acercaLoginClient(emailC = email, passwordC = it);
    }
    SpaceMidle(height = 25)
    ButtonLogin(acercaDeViewModel, loginEnabled, email, password, navController);
}

@Composable
fun SpaceMidle(height: Int) {//Espacio entre Capos del form
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun ButtonLogin(
    acercaDeViewModel: AcercaDeViewModel,
    loginEnabled: Boolean,
    email: String,
    password: String,
    navController: NavHostController,
) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            acercaDeViewModel.loginWithEmailAndPassword(
                email, password
            ){
                navController.navigate(AppScreen.AdminScreenPt2.route)
                scope.launch {
                    clientViewModel.currentOption("Inicio");
                }
            }
        },
        enabled = loginEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD67A19),
            contentColor = Color.White
        )
    ) {
        Text(text = "ACCEDER", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@ExperimentalMaterial3Api
@Composable
fun PasswordClient(password: String, ondataChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = { ondataChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contraseña") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFE4DEDE),
            focusedBorderColor = Color(0xFF0436B8),
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun EmailClient(email: String, ondataChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { ondataChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0XFF000000),
            containerColor = Color(0XFFE4DEDE),
            focusedBorderColor = Color(0xFF0436B8),
        )
    )
}
