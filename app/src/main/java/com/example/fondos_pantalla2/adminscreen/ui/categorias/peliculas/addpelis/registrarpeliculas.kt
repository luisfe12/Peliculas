package com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddPeliculasViewModel
import com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas.addpelis.PickImage

@ExperimentalMaterial3Api
@Composable
fun RegistrarPelicula(navController: NavHostController) {

    val addPeliculasViewModel: AddPeliculasViewModel = AddPeliculasViewModel();
    val nameM by addPeliculasViewModel.nameMovie.observeAsState("");
    val isloading by addPeliculasViewModel.isLoading.observeAsState(false);
    val isloadinImage by addPeliculasViewModel.isSearchinImage.observeAsState(false);
    val clickBtnState by addPeliculasViewModel.clickBtnPublish.observeAsState(false);
    val peliculaimgUpload by addPeliculasViewModel.peliculaImgUpload.observeAsState(false);


    val contex = LocalContext.current;

//    if (clickBtnState == true && isloadinImage == true) {
//        Log.i("ENTRO AL ALERT",
//            "${addPeliculasViewModel.clickBtnPublish.value} ${isloadinImage}")
//        AlertDialogPublish(mesagge = "Publicando Pelicula", addPeliculasViewModel)
//
//    }

    if (peliculaimgUpload == true) {
        Toast.makeText(contex, "Se subio correctamen el valor", Toast.LENGTH_SHORT).show();
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TexttileAddM()
        InsertDataMoview(nameTF = nameM, labelTF = "Nombre pelicula", onChangeFiel = {
            addPeliculasViewModel.setNameMoview(name = it);
        })
        Spacer(modifier = Modifier.height(15.dp));
        VistasMoview();
        Spacer(modifier = Modifier.height(20.dp));
        IconsImageM(addPeliculasViewModel = addPeliculasViewModel);
        Spacer(modifier = Modifier.height(20.dp));
        ButtonPublicar(isload = isloading, nameM, addPeliculasViewModel);

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Log.i("VALUE BOX", "${isloadinImage}");
            if (isloadinImage == false) {
                CircularProgressIndicator()
                Log.i("Cirular", "Que paso en la Image")
            } else {
                Log.i("TRUEEE IMAGE", "${addPeliculasViewModel._selectImage.value}");
//                val inputStream = requireContext().contentResolver.openInputStream(addPeliculasViewModel._selectImage.value!!)
//                BitmapFactory.decodeStream(inputStream)
//                Log.i("TRUEEE IMAGE", "${BitmapFactory.decodeStream(inputStream)}")
                Image(
                    painter = rememberAsyncImagePainter(addPeliculasViewModel._selectImage.value),
                    contentDescription = "Imagen"
                )
            }
        }
    }
}

@Composable
fun TexttileAddM() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(12.dp), contentAlignment = Alignment.CenterStart
    ) {
        Text(text = "Nombre de la imagen", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
    }
}

@ExperimentalMaterial3Api
@Composable
fun InsertDataMoview(nameTF: String, labelTF: String, onChangeFiel: (String) -> Unit) {
    OutlinedTextField(
        value = nameTF,
        onValueChange = { onChangeFiel(it) },
        placeholder = { Text(text = labelTF) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFF12303F),
            containerColor = Color(0xFFEEE1E1),
            focusedBorderColor = Color(0xFF12303F),
        )
    )
}

@Composable
fun VistasMoview() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Visibility,
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "0", fontSize = 24.sp)
    }
}

@Composable
fun IconsImageM(addPeliculasViewModel: AddPeliculasViewModel) {
    val galleryLauncher = PickImage(addPeliculasViewModel = addPeliculasViewModel);
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Icon(
            imageVector = Icons.Default.Image,
            contentDescription = "Icono imagen",
            modifier = Modifier
                .size(120.dp)
                .clickable {
                    galleryLauncher.launch("image/*");
                    Log.i("URI DE IMAGE", "${addPeliculasViewModel._selectImage.value}");
                },
            tint = Color(0xFF15506D),
        )
    }

}

@Composable
fun ButtonPublicar(isload: Boolean, nameM: String, addPeliculasViewModel: AddPeliculasViewModel) {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            onClick = {
                addPeliculasViewModel.uploadImage(nameM)
                addPeliculasViewModel.clickBtnState(true)
                Log.i("Boton Publicar","${addPeliculasViewModel.clickBtnPublish.value}")
            },
            enabled = isload,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF167B9B),
                contentColor = Color.White,
            )
        )
        {
            Text(text = "Publicar", fontSize = 25.sp);
        }
    }
}

