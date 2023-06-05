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
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.Routes.AppScreen

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeriesScreen(navController: NavHostController) {
    Log.i("SeriesScreen", "Series ENTRO");
    Scaffold(
        topBar = {
            MyTopBarSeries(
                onCLickBack = { navController.popBackStack() },
                onAddSerie = { navController.navigate(AppScreen.AddSerieScr.route) })
        }
    ) { innerPadding ->
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Series")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MyTopBarSeries(onCLickBack: () -> Unit, onAddSerie: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Series", color = Color(0xED181501), fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(
                onClick = { onCLickBack() }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color(0xED181501),
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver atras");
            }
        },
        actions = {
            IconButton(
                onClick = { onAddSerie() }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color(0xED181501),
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "nueva series");
            }
            IconButton(
                onClick = { }, colors = IconButtonDefaults.filledIconButtonColors(
                    contentColor = Color(0xED181501),
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Listar Series")
            }
        },

        colors = TopAppBarDefaults.smallTopAppBarColors(
            Color(0xFFA79248)
        )
    )
}