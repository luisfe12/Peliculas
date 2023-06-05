package com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Musica
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch

class AddMusicViewModel: ViewModel() {
    private val _nameMusic = MutableLiveData<String>();
    private val _isLoadingIM = MutableLiveData<Boolean>();
    private val _loadImgMsc = MutableLiveData<Boolean>()
    private val _rutaAmacenamientoMsc ="Musica_Subida/"
    private val _rutaDataBase = "MUSICA"
    val nameMusic: LiveData<String> = _nameMusic;
    val isLoadingIM: LiveData<Boolean> = _isLoadingIM;
    val loadImgMsc: LiveData<Boolean> = _loadImgMsc;


    //valores firebase
    private val storageRfs = Firebase.storage.reference
    private val dataBaseRfs = FirebaseDatabase.getInstance().getReference(_rutaDataBase)

    var _selectedIMsc = MutableLiveData<Uri>()

    fun setNameMusic(nameMsc:String){
        _nameMusic.value = nameMsc;
        _isLoadingIM.value = enableBtnP(nameMsc)
    }

    private fun enableBtnP(nameMsc: String): Boolean = nameMsc.isNotEmpty()

    fun showImageMsc(state:Boolean){
        _loadImgMsc.value = state;
    }

    fun uploadImageMsc(){
        viewModelScope.launch {
            try {
                val mStorageRfs = storageRfs.child(
                    _rutaAmacenamientoMsc + System.currentTimeMillis() + _selectedIMsc.value
                )

                mStorageRfs.putFile(_selectedIMsc.value!!).addOnSuccessListener {uri ->

                    mStorageRfs.downloadUrl.addOnCompleteListener {downloadTask ->
                        if (downloadTask.isSuccessful){
                            _loadImgMsc.value = true;
                        }
                    }
                }
            }catch (e:Exception){

            }
        }
    }

    fun publishImageMsc(nameMsc:String){
        viewModelScope.launch {
            try {
                val musica = Musica(
                    image = _selectedIMsc.value.toString(),
                    name = nameMsc,
                    views = 0,
                )
                val idImg = dataBaseRfs.push().key
                dataBaseRfs.child(idImg!!).setValue(musica);
            }catch (e:Exception){
                Log.i("ERROR P IMG MSC","${e.message}")
            }
        }
    }
}