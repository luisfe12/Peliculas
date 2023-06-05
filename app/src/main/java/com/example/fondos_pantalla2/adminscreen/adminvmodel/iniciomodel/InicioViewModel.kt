package com.example.fondos_pantalla2.adminscreen.adminvmodel.iniciomodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.Routes.AppScreen

class InicioViewModel : ViewModel() {

    private val _peliculas = MutableLiveData<Boolean>();
    private val _series = MutableLiveData<Boolean>();
    private val _musica = MutableLiveData<Boolean>();
    private val _videojuegos = MutableLiveData<Boolean>();

    val peliculas: LiveData<Boolean> = _peliculas;
    val series: LiveData<Boolean> = _series;
    val musica: LiveData<Boolean> = _musica;
    val videojuegos: LiveData<Boolean> = _videojuegos;

    private fun chanceStateCategory(
        peliculasCtg: Boolean = false,
        seriesCtg: Boolean = false,
        musicaCtg: Boolean = false,
        videojuegosCtg: Boolean = false
    ) {
        _peliculas.value = peliculasCtg;
        _series.value = seriesCtg;
        _musica.value = musicaCtg;
        _videojuegos.value = videojuegosCtg

        Log.i("peliculas", "${peliculas.value}")
        Log.i("Series", "${series.value}")
        Log.i("Musica", "${musica.value}")
        Log.i("Videojuegos", "${videojuegos.value}")
    }

    fun categorySelected(category: String, navController:NavHostController) {
        when(category){
            "Peliculas" -> navController.navigate(AppScreen.PeliculasScreen.route);
            "Series" -> navController.navigate(AppScreen.SeriesScreen.route);
            "Musica" -> navController.navigate(AppScreen.MusicaScreen.route);
            "Videojuegos" -> navController.navigate(AppScreen.VideojuegosScreen.route);
        }
    }
}