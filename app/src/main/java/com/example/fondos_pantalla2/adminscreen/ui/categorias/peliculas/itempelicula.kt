package com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Pelicula

@Composable
fun ItemMoviewAdd(pelicula: Pelicula) {
    //val uriuRL = Uri.parse(pelicula.image)

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(15.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF353638)
        )
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageBoxPelicula(pelicula = pelicula)
            MovieNameAndViews(pelicula)
        }
    }

}

@Composable
fun MovieNameAndViews(pelicula: Pelicula) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF555454)), contentAlignment = Alignment.Center
    ) {
        Text(
            text = pelicula.name,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        );

    }

    Divider(
        Modifier
            .height(8.dp)
            .padding(12.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF555454)), contentAlignment = Alignment.Center
    ) {

        Text(
            text ="Vista " + pelicula.views.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        );
    }
}

@Composable
fun ImageBoxPelicula(pelicula: Pelicula) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = rememberAsyncImagePainter(
            pelicula.image
        ), contentDescription = "Imagen pintado", modifier = Modifier
            .size(200.dp)
            .clickable {
                Log.i("Image", "${pelicula.image}")
            }
        );
    }
}






