package com.example.fondos_pantalla2.clientescreen.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.outlined.CircleNotifications
import androidx.compose.material.icons.outlined.Share
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
import com.example.fondos_pantalla2.clientescreen.DrawerClientItem
import kotlinx.coroutines.launch

@Composable
fun ClientDrawer(onClose: () -> Unit) {

    val listClient: List<DrawerClientItem> = listOf<DrawerClientItem>(
        DrawerClientItem(Icons.Filled.Start, "Inicio"),
        DrawerClientItem(Icons.Outlined.CircleNotifications, "Acerca de"),
        DrawerClientItem(Icons.Outlined.Share, "Compartir"),
    )
    Column(modifier = Modifier.padding(8.dp)) {
        FirstParClt("Luis");
        Spacer(modifier = Modifier.height(20.dp))
        OtherPartClt(listClient.subList(0, 1), "Opciones") { onClose() };
        OtherPartClt(listClient.subList(1,listClient.size), "Mas Opciones") { onClose() };
    }
}


@Composable
fun OtherPartClt(listDrawer: List<DrawerClientItem>, namePart: String, onClose: () -> Unit) {
    val scope = rememberCoroutineScope();
    Text(namePart, fontWeight = FontWeight.Light, fontSize = 24.sp);
    listDrawer.forEach { item ->
        Card(
            elevation = CardDefaults.cardElevation(28.dp),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(30.dp)
                .clickable {
                    scope.launch { onClose() }
                    clientViewModel.currentOption(item.nameOpt);
                    Log.i("presiono", "${item.nameOpt}")
                }) {

            RowItemDrawer(item = item);
        }
    }
}

@Composable
fun RowItemDrawer(item: DrawerClientItem) {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = item.icon, contentDescription = "icono de ${item.nameOpt}");
        Spacer(modifier = Modifier.width(18.dp));
        Text(
            text = item.nameOpt,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        );
    }
}

@Composable
fun FirstParClt(nameCreator: String) {
    Icon(
        imageVector = Icons.Filled.Person3,
        contentDescription = "Icono de cliente",
        modifier = Modifier.size(65.dp)
    )
    Text(text = "Fondode pantalla", fontSize = 24.sp);
    Text(text = "Creado por $nameCreator", fontSize = 24.sp);
}
