package com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Serie
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch

class AddSerieViewModel : ViewModel() {

    private val _nameSerie = MutableLiveData<String>();
    private val _enableBtn = MutableLiveData<Boolean>();
    private val _rutaBdSerie = "SERIE";
    private val _rutaAlmacenamiento = "Serie_Subida/";
    private val _serieLoad = MutableLiveData<Boolean>();

    private val sStorageRfs = Firebase.storage.reference;
    private val dbSerieRfs = FirebaseDatabase.getInstance().getReference(_rutaBdSerie);

    var selecImgSerie = MutableLiveData<Uri>();


    val nameSerie: LiveData<String> = _nameSerie;
    val enableBtn: LiveData<Boolean> = _enableBtn;
    val serieLoad: LiveData<Boolean> = _serieLoad;

    fun setNameSerie(name: String) {
        _nameSerie.value = name;
        _enableBtn.value = isEnabledBtn(name)
    }

    private fun isEnabledBtn(name: String): Boolean = name.isNotEmpty();

    fun uploadSerie() {
        viewModelScope.launch {
            try {
                val serieStorageRfs = sStorageRfs.child(
                    _rutaAlmacenamiento + System.currentTimeMillis() + selecImgSerie.value
                )

                serieStorageRfs.putFile(selecImgSerie.value!!).addOnSuccessListener { uri ->
                    serieStorageRfs.downloadUrl.addOnCompleteListener { downloadTask ->
                        if (downloadTask.isSuccessful) {
                            _serieLoad.value = true;
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("Error uploading", "${e.message}")
            }
        }
    }

    fun publishSerie(nameS: String) {
        viewModelScope.launch {
            val serie = Serie(
                image = selecImgSerie.value.toString(),
                name = nameS,
                views = 0,
            )

            val idSerie = dbSerieRfs.push().key;
            dbSerieRfs.child(idSerie!!).setValue(serie)
        }
    }

}