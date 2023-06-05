package com.example.fondos_pantalla2.clientescreen.ui.acercade

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@ExperimentalMaterial3Api
@Composable
fun AcercaDeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleLogin();
        Spacer(modifier = Modifier.height(20.dp))
        IconLoginClient()
        AdminLogin(navController);

    }
}


@Composable
fun IconLoginClient(){
    Icon(imageVector = Icons.Default.Person4,
        contentDescription = "Usuario login",
        modifier = Modifier.size(200.dp),
        tint = Color(0x973A29D1)
    )
}

@Composable
fun TitleLogin() {
    Text(text = "Login de Administradores", fontSize = 24.sp, fontWeight = FontWeight.Bold)
}