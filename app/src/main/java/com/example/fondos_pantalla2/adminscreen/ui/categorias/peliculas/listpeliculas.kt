package com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.fondos_pantalla2.adminscreen.adminvmodel.pelicula.PeliculasViewModel


@Composable
fun ListPeliculasDb() {

    val peliculasViewModel: PeliculasViewModel = PeliculasViewModel();
    val listMoview by peliculasViewModel.peliculas.collectAsState();
    val pelisLoad by peliculasViewModel.pelisLoad.observeAsState(false)

    if (!pelisLoad) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(listMoview) { pelicula ->
                ItemMoviewAdd(pelicula = pelicula);
            }
        }
    }
}