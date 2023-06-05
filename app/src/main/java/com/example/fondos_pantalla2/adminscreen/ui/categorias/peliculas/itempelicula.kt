package com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Pelicula

@Composable
fun ItemMoviewAdd(pelicula:Pelicula){
    val uriuRL = Uri.parse(pelicula.image)
    Column(modifier = Modifier.padding(12.dp)) {
        Text(pelicula.name);
        Text(text = pelicula.image)
        Image(painter = rememberAsyncImagePainter(
            uriuRL
        ), contentDescription = "Imagen pintado", modifier = Modifier.size(220.dp));
        Text(text = pelicula.views.toString())
    }
}
    
