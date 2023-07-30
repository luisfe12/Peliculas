package com.example.fondos_pantalla2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddPeliculasViewModel

@Composable
fun AlertDialogPublish(mesagge: String, addPeliculasViewModel: AddPeliculasViewModel) {

    val peliculaimgUpload by addPeliculasViewModel.peliculaImgUpload.observeAsState(false);

    Dialog(
        onDismissRequest = { }, properties = DialogProperties(

        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color(0xFFBEBDBD)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(mesagge, fontWeight = FontWeight.ExtraBold, color = Color.White);

            if (!peliculaimgUpload) {

                CircularProgressIndicator(modifier = Modifier.size(70.dp))
            }else{
                Text(text = "Cargo correctamente")
            }
        }

    }
}
