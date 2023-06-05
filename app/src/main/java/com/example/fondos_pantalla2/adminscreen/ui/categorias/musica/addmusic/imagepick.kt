package com.example.fondos_pantalla2.adminscreen.ui.categorias.musica.addmusic

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel.AddMusicViewModel

@Composable
fun PickImageMusic(addMusicViewModel: AddMusicViewModel): ManagedActivityResultLauncher<String, Uri?> {
    val selectMusicImage = remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLaunch = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        selectMusicImage.value = it!!;
    }

    if (selectMusicImage.value != null) {
        addMusicViewModel._selectedIMsc.value = selectMusicImage.value;
        addMusicViewModel.uploadImageMsc();
    }

    return galleryLaunch
}