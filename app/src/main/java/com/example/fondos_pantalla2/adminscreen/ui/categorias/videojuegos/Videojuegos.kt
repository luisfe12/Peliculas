package com.example.fondos_pantalla2.adminscreen.ui.categorias

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.Routes.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun VideojuegosScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            MyTopAppBarVideojuegos(
                onClickBack = { navController.popBackStack()},
                onAddVideoGame = { navController.navigate(AppScreen.AddVideoGameScr.route) })
        }
    ) { innerPadding ->
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Videojuegos")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MyTopAppBarVideojuegos(onClickBack: () -> Unit, onAddVideoGame: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Videojuegos", color = Color.White) },
        navigationIcon = {
            IconButton(
                onClick = { onClickBack() }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver a peliculas"
                );
            }
        },
        actions = {
            IconButton(
                onClick = { onAddVideoGame() }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add videojuegos")
            }
            IconButton(
                onClick = { /*TODO*/ }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Alinear ")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF666262)
        )
    )
}