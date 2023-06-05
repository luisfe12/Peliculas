@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fondos_pantalla2.adminscreen.ui

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MyTopBarAdmin(onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text("Admin Screen", fontWeight = FontWeight.Bold, fontSize = 24.sp) },
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Image(imageVector = Icons.Filled.Menu, contentDescription = "Menu toppappbar");
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xFFFF760D))

    )

}