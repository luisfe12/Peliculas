package com.example.fondos_pantalla2.adminscreen.ui.categorias.musica.addmusic

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
import androidx.compose.material.icons.filled.MusicNote
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
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddMusicViewModel

//@ExperimentalMaterial3Api
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun ShowReMus() {
//    RegistrarMusica()
//}

@ExperimentalMaterial3Api
@Composable
fun RegistrarMusica(navController:NavHostController) {

    val addMusicViewModel: AddMusicViewModel = AddMusicViewModel();
    val nameMusic by addMusicViewModel.nameMusic.observeAsState("");
    val isLoading by addMusicViewModel.isLoadingIM.observeAsState(false);
    val loadImgMsc by addMusicViewModel.loadImgMsc.observeAsState(false);

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TitleAddM();
        Spacer(modifier = Modifier.height(15.dp))
        InsertDataMusic(nameMusic) {
            addMusicViewModel.setNameMusic(nameMsc = it);
        };
        Spacer(modifier = Modifier.height(15.dp));
        VistaMusic();
        Spacer(modifier = Modifier.height(20.dp));
        IconImageMusic(addMusicViewModel);
        Spacer(modifier = Modifier.height(20.dp));
        ButtomPublicarMusic(isLoading, nameMusic,addMusicViewModel);

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            if (!loadImgMsc) {
                CircularProgressIndicator()
            } else {
                Image(
                    painter = rememberAsyncImagePainter(model = addMusicViewModel._selectedIMsc.value),
                    contentDescription = "Imagen Para subir"
                )
            }

        }

    }
}

@Composable
fun ButtomPublicarMusic(isloading: Boolean, nameMusic: String,addMusicViewModel: AddMusicViewModel) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            onClick = { addMusicViewModel.publishImageMsc(nameMusic) }, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD54E17),
                contentColor = Color.White
            ),
            enabled = isloading
        ) {
            Text(text = "Publlicar", fontSize = 24.sp)
        }
    }
}

@Composable
fun IconImageMusic(addMusicViewModel: AddMusicViewModel) {
    val gaaleryLaunc = PickImageMusic(addMusicViewModel = addMusicViewModel)
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Icon(
            imageVector = Icons.Default.MusicNote,
            contentDescription = "Icono Musica",
            modifier = Modifier
                .size(120.dp)
                .clickable {
                    gaaleryLaunc.launch("image/*")
                },
            tint = Color(0xFF864908)
        )
    }
}

@Composable
fun VistaMusic() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Visibility,
            contentDescription = "IconoVisibility",
            modifier = Modifier.size(40.dp)
        );
        Spacer(modifier = Modifier.width(8.dp));
        Text(text = "0", fontSize = 24.sp)
    }
}

@ExperimentalMaterial3Api
@Composable
fun InsertDataMusic(nameMusic: String, setNameMusic: (String) -> Unit) {
    OutlinedTextField(
        value = nameMusic,
        onValueChange = { setNameMusic(it) },
        placeholder = { Text(text = "Nombre de la musica", fontSize = 18.sp) },
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
fun TitleAddM() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Text(text = "Nombre de la imagen", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
    }
}
