package com.example.fondos_pantalla2.adminscreen.ui.inicio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.R
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.InicioViewModel




@Composable
fun InicioOptionScreen(modifier: Modifier, navController: NavHostController) {

    val inicioViewModel: InicioViewModel = InicioViewModel();
    val peliculas by inicioViewModel.peliculas.observeAsState(false);
    val series by inicioViewModel.series.observeAsState(false);
    val musica by inicioViewModel.musica.observeAsState(false);
    val videojuegos by inicioViewModel.videojuegos.observeAsState(false);


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        RowOptions(modifier = Modifier, navController, inicioViewModel)


    }
}


@Composable
fun RowOptions(
    modifier: Modifier,
    navController: NavHostController,
    inicioViewModel: InicioViewModel
) {
    Row(modifier = modifier) {
        CategoryOption(
            modifier = Modifier.weight(1f),
            R.drawable.peliculas,
            "Peliculas",
            navController,
            inicioViewModel
        );
        CategoryOption(
            modifier = Modifier.weight(1f),
            R.drawable.series,
            "Series",
            navController,
            inicioViewModel
        );
    }
    Row(modifier = modifier) {
        CategoryOption(
            modifier = Modifier.weight(1f),
            R.drawable.musica,
            "Musica",
            navController,
            inicioViewModel
        );
        CategoryOption(
            modifier = Modifier.weight(1f),
            R.drawable.videojuegos,
            "Videojuegos",
            navController,
            inicioViewModel
        );
    }

}
