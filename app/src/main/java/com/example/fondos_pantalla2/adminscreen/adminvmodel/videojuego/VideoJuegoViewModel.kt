package com.example.fondos_pantalla2.adminscreen.adminvmodel.videojuego

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fondos_pantalla2.adminscreen.data.registrarP.VideoJuego
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoJuegoViewModel:ViewModel() {

    private val _dbVideoGameRfs = FirebaseDatabase.getInstance().getReference("VIDEOJUEGOS");
    private val _videojuegos = MutableStateFlow<List<VideoJuego>>(emptyList());

    private val _isVGLoad = MutableLiveData<Boolean>();

    val videojuegos: StateFlow<List<VideoJuego>> = _videojuegos;
    val isVGLoad: LiveData<Boolean> = _isVGLoad;


    fun loadForVideoGames(){
        val valueEventListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val videogamesList = mutableListOf<VideoJuego>();

                for (videoGamesnapShot in snapshot.children){
                    val image = videoGamesnapShot.child("image").getValue(String::class.java);
                    val name = videoGamesnapShot.child("name").getValue(String::class.java);
                    val views = videoGamesnapShot.child("views").getValue(Int::class.java);

                    if(image != null && name != null && views != null){
                        val videojuego = VideoJuego(image, name, views);
                        videogamesList.add(videojuego);
                    }
                }

                _videojuegos.value = videogamesList;
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Erroe", "${error.message}");
            }

        }

        _dbVideoGameRfs.addValueEventListener(valueEventListener)
        _isVGLoad.value = true;
    }


}