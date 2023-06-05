package com.example.fondos_pantalla2.adminscreen.adminvmodel.pelicula

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Pelicula
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PeliculasViewModel: ViewModel() {

    private val dbPeliculasRfs = FirebaseDatabase.getInstance().getReference("PELICULAS");
    private val _pelisLoad = MutableLiveData<Boolean>();

    private val _peliculas = MutableStateFlow<List<Pelicula>>(emptyList());
    val peliculas: StateFlow<List<Pelicula>> = _peliculas;
    val pelisLoad:LiveData<Boolean> = _pelisLoad;

    init {
        listenForMovies()
    }

    private fun listenForMovies() {
        viewModelScope.launch {
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val peliculaList = mutableListOf<Pelicula>()
                    for (peliculasSnapshot in snapshot.children) {
                        val image = peliculasSnapshot.child("image").getValue(String::class.java);
                        val name = peliculasSnapshot.child("name").getValue(String::class.java);
                        val views = peliculasSnapshot.child("views").getValue(Int::class.java);

                        val pelicula = Pelicula(image!!, name!!, views!!)
                        Log.i("PELICULA DB", "${pelicula}");
                        peliculaList.add(pelicula);
                    }

                    _peliculas.value = peliculaList;
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("LISTEN DATA Error", "$error")
                }

            }

            dbPeliculasRfs.addValueEventListener(valueEventListener);
            _pelisLoad.value = true;
        }
    }
}