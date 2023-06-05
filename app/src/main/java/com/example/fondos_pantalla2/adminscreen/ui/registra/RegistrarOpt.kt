package com.example.fondos_pantalla2.adminscreen.ui.registra

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fondos_pantalla2.R

@ExperimentalMaterial3Api
@Composable
fun RegistrarOptionScreen(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleRegistrar(title = "Registro de Administradores");
        DateCheck();
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "perfil image admin",
            modifier = Modifier
                .size(160.dp)
                .padding(top = 12.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        FormAdmin();
    }

}


@Composable
fun TitleRegistrar(title: String) {
    Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold);
}

@Composable
fun DateCheck() {
    Row(
        modifier = Modifier.padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = "Fecha de Registro", fontSize = 18.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Default.CalendarMonth,
            contentDescription = "Calendar",
            modifier = Modifier.size(30.dp),
            tint = Color(0xFF4B6BBD)
        )
    }
}