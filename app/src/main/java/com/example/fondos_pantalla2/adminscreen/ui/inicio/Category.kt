package com.example.fondos_pantalla2.adminscreen.ui.inicio

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.InicioViewModel

@Composable
fun CategoryOption(modifier: Modifier,
                   @DrawableRes image: Int,
                   nameBtn: String,
                   navController: NavHostController,
                   inicioViewModel:InicioViewModel
) {
    Column(modifier = modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Imagen ${image.toString()}",
            modifier = Modifier.size(270.dp)
        )
        Button(
            onClick = {
                inicioViewModel.categorySelected(nameBtn, navController);
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF065269),

                )
        ) {
            Text(text = nameBtn)
        }
    }
}