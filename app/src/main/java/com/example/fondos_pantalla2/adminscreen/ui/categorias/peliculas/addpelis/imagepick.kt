package com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas.addpelis

import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddPeliculasViewModel

@Composable
fun PickImage(addPeliculasViewModel: AddPeliculasViewModel): ManagedActivityResultLauncher<String, Uri?> {

    val selecImage = remember {
        mutableStateOf<Uri?>(null)
    }
    val galleryLauncer = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        selecImage.value = it
    }

    addPeliculasViewModel._selectImage.value = selecImage.value;
    if (addPeliculasViewModel._selectImage.value !=null) {
        addPeliculasViewModel.publishImage();
    }
    Log.i("PICK LAUNG", "${addPeliculasViewModel._selectImage.value}");
    return galleryLauncer
}