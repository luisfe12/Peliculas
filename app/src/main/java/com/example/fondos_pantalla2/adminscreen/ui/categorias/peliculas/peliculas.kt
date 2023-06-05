package com.example.fondos_pantalla2.adminscreen.ui.categorias

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.Routes.AppScreen
import com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas.ListPeliculasDb

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MoviesScreen(navController: NavHostController) {
    Log.i("PeliculasScreen", "PELICULAS ENTRO");
    MoviesScreen2(navController);
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MoviesScreen2(navController: NavHostController) {
    Scaffold(
        topBar = {
            MyTopBarMovies(
                onBackScreen = {navController.popBackStack()},
                onAddMoview = {navController.navigate(AppScreen.AddMoviewScr.route)}
            )
        },

        ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            ListPeliculasDb();
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun MyTopBarMovies(onBackScreen: () -> Unit, onAddMoview: () -> Unit) {
    //val navController:NavHostController = rememberNavController();
    TopAppBar(
        title = { Text(text = "Peliculas", color = Color.White) },
        navigationIcon = {
            IconButton(
                onClick = { onBackScreen() },
                colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Icono de retroceder"
                );
            }
        },
        actions = {
            IconButton(
                onClick = { onAddMoview()},
                colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Aumentar epliculas");

            }
            IconButton(
                onClick = {},
                colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Listar imagenes"
                );
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(Color.Black)
    )
}