package com.example.fondos_pantalla2.adminscreen.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fondos_pantalla2.adminscreen.DrawerMenuItem
import com.example.fondos_pantalla2.adminscreen.adminModelView
import kotlinx.coroutines.launch

//adminDrawer: (DrawerMenuItem) -> Unit
@Composable
fun AdminDrawer(closeDrawer: () -> Unit) {

    val listDrawerOptions: List<DrawerMenuItem> = listOf<DrawerMenuItem>(
        DrawerMenuItem(Icons.Default.CheckCircle, "Inicio"),
        DrawerMenuItem(Icons.Default.CheckCircle, "Perfil"),
        DrawerMenuItem(Icons.Default.CheckCircle, "Registrar"),
        DrawerMenuItem(Icons.Default.CheckCircle, "Listar Administradores"),
        DrawerMenuItem(Icons.Default.CheckCircle, "Cerrar Sesion"),
    )

    Column(
        modifier = Modifier
    ) {

        PresentationPart(name = "Luis");
        LazyColumn() {
            items(listDrawerOptions) { item ->
                ContentLazy(item, closeDrawer);
            }
        }
    }
}

@Composable
fun PresentationPart(name: String) {
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Icono personal",
        modifier = Modifier.size(65.dp)
    )
    Text(text = "Fondo de Pantalla", fontSize = 28.sp)
    Text(text = "Creado por $name", fontSize = 24.sp)
}

@Composable
fun ContentLazy(itemDrawer: DrawerMenuItem, closeDrawer: () -> Unit) {

    val scope = rememberCoroutineScope();

    Box(modifier = Modifier
        .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        .clickable {

        }) {
        Card(
            elevation = CardDefaults.cardElevation(28.dp),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .clickable {
                    scope.launch { clickedOptionDwr(optionName = itemDrawer.texto); closeDrawer(); }

                }
                .padding(30.dp)) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = itemDrawer.icon,
                    contentDescription = "Icono ${itemDrawer.texto}"
                );
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = itemDrawer.texto,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                );
            }
        }
    }
}

fun clickedOptionDwr(optionName: String) {
    when (optionName) {
        "Inicio" -> adminModelView.changeOption(inicio = true);
        "Perfil" -> adminModelView.changeOption(perfil = true);
        "Registrar" -> adminModelView.changeOption(registrar = true);
        "Listar Administradores" -> adminModelView.changeOption(listar = true);
        "Cerrar Sesion" -> adminModelView.changeOption(cerrar = true);
    }

    Log.i("pulsate", "$optionName")
}