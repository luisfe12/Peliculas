package com.example.fondos_pantalla2.adminscreen.ui.categorias.videojuegos.addvideoj

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddVideojuegoViewModel


@Composable
fun PickImageVG(addVideojuegoViewModel: AddVideojuegoViewModel): ManagedActivityResultLauncher<String, Uri?> {
    val selecImgVG = remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLaunch = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        selecImgVG.value = it!!
    }

    if (selecImgVG.value != null){
        addVideojuegoViewModel.selecImgVg.value = selecImgVG.value
        addVideojuegoViewModel.uploadVG();
    }

    return galleryLaunch;
}