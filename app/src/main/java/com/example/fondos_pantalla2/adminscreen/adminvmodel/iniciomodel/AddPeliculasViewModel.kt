package com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Pelicula
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch

class AddPeliculasViewModel : ViewModel() {

    private val _nameMovie = MutableLiveData<String>();
    private val _isLoading = MutableLiveData<Boolean>();
    private val _isSearchinImage = MutableLiveData<Boolean>();
    private val _rutaAlmacenamiento = "Peliucla_Subida/";
    private val _rutaDataBase = "PELICULAS";

    private val mStorageReference = Firebase.storage.reference;
    private val dataBaseReference = FirebaseDatabase.getInstance().getReference(_rutaDataBase);

    var _selectImage = MutableLiveData<Uri>();

    val nameMovie: LiveData<String> = _nameMovie;
    val isLoading: LiveData<Boolean> = _isLoading;

    val isSearchinImage: LiveData<Boolean> = _isSearchinImage;

    fun setNameMoview(name: String) {
        _nameMovie.value = name;
        _isLoading.value = isAvaible(name);
    }

    private fun isAvaible(name: String): Boolean = name.isNotEmpty();

//    fun getUriIamge(uri:Uri){
//        _selectImage.value = uri;
//    }

//    fun changeLoadingImg(value:Boolean){
//        _isSearchinImage.value = value;
//    }

    fun publishImage() {

        Log.i("ENTRO publishImage", "${_selectImage.value}")

        viewModelScope.launch {
            try {
                Log.i("TRY publishImage", "${_selectImage.value}")
                val mStorageReference2 = mStorageReference.child(
                    _rutaAlmacenamiento +
                            System.currentTimeMillis() +
                            _selectImage.value
                );

                Log.i("MSTORAGE", "${mStorageReference2}")
                mStorageReference2.putFile(_selectImage.value!!).addOnSuccessListener { uri ->
//                    val imageUrl = uri.toString()
                    Log.i("URI-MSTORAGE", "${uri}")
                    mStorageReference2.downloadUrl.addOnCompleteListener { downloadtask ->
                        if (downloadtask.isSuccessful) {
                            Log.i("NOMBRE PELICULA", "${_nameMovie.value}");
                            Log.i("NOMBRE PELICULA ppp", "${_nameMovie.value}");
                            _isSearchinImage.value = true;
                        }

                    }

                }.addOnFailureListener { exception ->
                    Log.i(" IPELICULA EXCEPTIO", "${exception}")
                }
            } catch (e: Exception) {
                Log.i("Error PUBLICANDO", " Error en la solicitud${e.message}")
            }
        }

    }

    fun uploadImage(nameM:String){
        viewModelScope.launch {
            val pelicula = Pelicula(
                image = _selectImage.value.toString(),
                name = nameM,
                views = 0,
            )
            val idImg = dataBaseReference.push().key;
            dataBaseReference.child(idImg!!).setValue(pelicula)
        }
    }

}

/**
 * val pelicula = Pelicula(
image = _selectImage.value.toString(),
name = "hola",
views = 0,
)

val idImage = dataBaseReference.push().key
dataBaseReference.child(idImage!!).setValue(pelicula)
 */