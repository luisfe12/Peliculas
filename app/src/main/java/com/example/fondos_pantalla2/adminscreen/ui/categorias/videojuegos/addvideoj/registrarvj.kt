package com.example.fondos_pantalla2.adminscreen.ui.categorias.videojuegos.addvideoj

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
import androidx.compose.material.icons.filled.VideogameAsset
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
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddVideojuegoViewModel

@ExperimentalMaterial3Api
@Composable
fun RegistrarVideoGame(navController: NavHostController) {

    val addVideojuegoViewModel: AddVideojuegoViewModel = AddVideojuegoViewModel();
    val nameVg by addVideojuegoViewModel.nameVideoGame.observeAsState("");
    val vgUpload by addVideojuegoViewModel.isVGload.observeAsState(false);
    val enabledBtn by addVideojuegoViewModel.enableBtn.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TitleAddVg();
        Spacer(modifier = Modifier.height(15.dp));
        InsertDataImg(nameVg) { addVideojuegoViewModel.setVideGameName(nameVG = it) }
        Spacer(modifier = Modifier.height(15.dp));
        VistaVG()
        IconImageVG(addVideojuegoViewModel)
        Spacer(modifier = Modifier.height(20.dp));
        ButtomPublisVG(enabledBtn, nameVg, addVideojuegoViewModel = addVideojuegoViewModel);

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), contentAlignment = Alignment.Center
        ) {
            if (!vgUpload) {
                CircularProgressIndicator()
            } else {
                Image(
                    painter = rememberAsyncImagePainter(model = addVideojuegoViewModel.selecImgVg.value),
                    contentDescription = "Imiagen para ser subida"
                )
            }
        }
    }
}

@Composable
fun ButtomPublisVG(
    enabledBtn: Boolean,
    nameVG: String,
    addVideojuegoViewModel: AddVideojuegoViewModel
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            onClick = { addVideojuegoViewModel.publishVideoGame(nameVG = nameVG) },
            enabled = enabledBtn,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFC0BCBC),
                containerColor = Color(0xFF866F29),
            )
        ) {
            Text(text = "Publicar Imagen", fontSize = 25.sp)
        }
    }
}

@Composable
fun IconImageVG(addVideojuegoViewModel: AddVideojuegoViewModel) {
    val galleryLaunch = PickImageVG(addVideojuegoViewModel = addVideojuegoViewModel)
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Icon(
            imageVector = Icons.Default.VideogameAsset,
            contentDescription = "Icono de videojuego",
            modifier = Modifier
                .size(120.dp)
                .clickable {
                    galleryLaunch.launch("image/*")
                },
            tint = Color(0xFF49390A)
        )
    }
}

@Composable
fun VistaVG() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Visibility,
            contentDescription = "Vistas",
            modifier = Modifier.size(40.dp)
        );
        Spacer(modifier = Modifier.width(8.dp));
        Text("0", fontSize = 24.sp);
    }
}

@ExperimentalMaterial3Api
@Composable
fun InsertDataImg(nameVg: String, setNameVG: (String) -> Unit) {
    OutlinedTextField(
        value = nameVg,
        onValueChange = { setNameVG(it) },
        placeholder = { Text(text = "Ingrese el nombre de Videojuego") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFEEE1E1),
            textColor = Color(0xFF12303F),
            focusedBorderColor = Color(0xFF12303F),
        )
    )
}

@Composable
fun TitleAddVg() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Text(text = "Nombre del Videojuego", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
    }
}
