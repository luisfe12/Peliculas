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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.Routes.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MusicScreen(navController: NavHostController) {
    Log.i("MusicScreen", "MUSICA ENTRO");
    Scaffold(
        topBar = {
            MyTopBarMusic(
                onClickBack = { navController.popBackStack() },
                onAddMusic = { navController.navigate(AppScreen.AddMusicScr.route) })
        }
    ) { innerPadding ->
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Musica")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MyTopBarMusic(onClickBack: () -> Unit, onAddMusic: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Mmusica", color = Color.White) },
        navigationIcon = {
            IconButton(
                onClick = { onClickBack() }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver a la linea de atras"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onAddMusic() }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add musica")
            }
            IconButton(
                onClick = { /*TODO*/ }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Alinear Datos")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF7205D3)
        )

    )
}