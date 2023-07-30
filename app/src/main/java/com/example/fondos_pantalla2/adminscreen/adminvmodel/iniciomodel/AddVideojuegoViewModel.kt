package com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fondos_pantalla2.adminscreen.data.registrarP.VideoJuego
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch

class AddVideojuegoViewModel:ViewModel() {

    private val _nameVideoGame = MutableLiveData<String>();
    private val _isVGload = MutableLiveData<Boolean>();
    private val _enableBtn = MutableLiveData<Boolean>();
    private val _rutaDbVg = "VIDEOJUEGOS";
    private val _rutaAlmacenamiento = "Videojuego_Subido/";

    private val _vgStorageRfs = Firebase.storage.reference;
    private val _dbVGRfs = FirebaseDatabase.getInstance().getReference(_rutaDbVg);

    var imageLink = MutableLiveData<String>()
    var selecImgVg = MutableLiveData<Uri>()

    val nameVideoGame:LiveData<String> = _nameVideoGame;
    val isVGload:LiveData<Boolean> = _isVGload;
    val enableBtn:LiveData<Boolean> = _enableBtn;

    fun setVideGameName(nameVG:String){
        _nameVideoGame.value = nameVG;
        _enableBtn.value = isEnabledBtn(nameVG);
    }

    private fun isEnabledBtn(nameVG: String): Boolean = nameVG.isNotEmpty();

    fun uploadVG(){
        viewModelScope.launch {
            val videogameSorage = _vgStorageRfs.child(
                _rutaAlmacenamiento + System.currentTimeMillis() + selecImgVg.value
            )

            videogameSorage.putFile(selecImgVg.value!!).addOnSuccessListener { Uri->
                videogameSorage.downloadUrl.addOnCompleteListener { downloadTask->
                    if (downloadTask.isSuccessful){
                        imageLink.value = downloadTask.result.toString();
                        _isVGload.value = true;
                    }
                }
            }
        }
    }


    fun publishVideoGame(nameVG:String){
        viewModelScope.launch {
            val videojuego = VideoJuego(
                image = imageLink.value!!,
                name = nameVG,
                views = 0,
            )

            val idImg = _dbVGRfs.push().key;
            _dbVGRfs.child(idImg!!).setValue(videojuego)
        }
    }

}