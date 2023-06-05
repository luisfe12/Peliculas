package com.example.fondos_pantalla2.adminscreen.ui.categorias.series.addserie

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddSerieViewModel

@Composable
fun PickImageSerie(addSerieViewModel: AddSerieViewModel): ManagedActivityResultLauncher<String, Uri?> {
    val selectImageSerie = remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLaunch = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        selectImageSerie.value = it!!
    }

    if (selectImageSerie.value != null){
        addSerieViewModel.selecImgSerie.value = selectImageSerie.value
        addSerieViewModel.uploadSerie()
    }

    return galleryLaunch
}