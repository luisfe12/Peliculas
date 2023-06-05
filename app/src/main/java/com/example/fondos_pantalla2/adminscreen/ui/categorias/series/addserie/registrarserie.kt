package com.example.fondos_pantalla2.adminscreen.ui.categorias.series.addserie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddSerieViewModel

//@ExperimentalMaterial3Api
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ShowRegistrarSerie() {
//    RegistrarSerie();
//}

@ExperimentalMaterial3Api
@Composable
fun RegistrarSerie(navController:NavHostController) {

    val addSerieViewModel: AddSerieViewModel = AddSerieViewModel();
    val nameSerie by addSerieViewModel.nameSerie.observeAsState("");
    val loadSerie by addSerieViewModel.serieLoad.observeAsState(false);
    val enabledBtn by addSerieViewModel.enableBtn.observeAsState(false);

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TextAddS();
        Spacer(modifier = Modifier.height(15.dp));
        InsertDataSerie(nameSerie) {
            addSerieViewModel.setNameSerie(name = it)
        };
        Spacer(modifier = Modifier.height(15.dp));
        VistaSerie();
        Spacer(modifier = Modifier.height(20.dp));
        IconImageSerie(addSerieViewModel);
        Spacer(modifier = Modifier.height(20.dp));
        ButtomPublishSerie(enabledBtn, nameSerie, addSerieViewModel);

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp), contentAlignment = Alignment.Center
        ) {
            if (!loadSerie) {
                CircularProgressIndicator()
            } else {
                Image(
                    painter = rememberAsyncImagePainter(model = addSerieViewModel.selecImgSerie.value),
                    contentDescription = "Imagen para subir"
                )
            }
        }

    }
}

@Composable
fun ButtomPublishSerie(
    enabledBtn: Boolean,
    nameSerie: String,
    addSerieViewModel: AddSerieViewModel
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            onClick = { addSerieViewModel.publishSerie(nameSerie) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF114B5C),
                contentColor = Color.White,
            ),
            enabled = enabledBtn,
        ) {
            Text(text = "Publicar", fontSize = 25.sp)
        }
    }
}

@Composable
fun IconImageSerie(addSerieViewModel: AddSerieViewModel) {

    val galleryLaunch = PickImageSerie(addSerieViewModel = addSerieViewModel)
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Icon(
            imageVector = Icons.Default.Tv,
            contentDescription = "Icono de Image",
            modifier = Modifier
                .size(120.dp)
                .clickable { galleryLaunch.launch("image/*") },
            tint = Color(0xFF426648)
        )

    }
}

@Composable
fun VistaSerie() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Visibility,
            contentDescription = "Vistas de serie",
            modifier = Modifier.size(40.dp)
        );
        Spacer(modifier = Modifier.width(8.dp));
        Text(text = "0", fontSize = 24.sp);
    }
}

@ExperimentalMaterial3Api
@Composable
fun InsertDataSerie(nameSerie: String, onActualName: (String) -> Unit) {
    OutlinedTextField(
        value = nameSerie,
        onValueChange = { onActualName(it) },
        placeholder = { Text(text = "Nombre de la Serie", fontSize = 18.sp) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFEEE1E1),
            textColor = Color(0xFF12303F),
            focusedBorderColor = Color(0xFF12303F),
        )
    )
}


@Composable
fun TextAddS() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Text(
            text = "Ingrese el nombre de la serie",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
    }
}
