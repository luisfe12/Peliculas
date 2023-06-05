package com.example.fondos_pantalla2.clientescreen.ui

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@ExperimentalMaterial3Api
@Composable
fun MyTopBarCllient(onClickOpen: () -> Unit) {
    TopAppBar(
        title = { Text("Pantalla Cliente") },
        navigationIcon = {
            IconButton(onClick = { onClickOpen();
                Log.i("Pantalla", "drawer") })
            {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Drawer Icon")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xFFFF760D))
    )
}