package com.example.fondos_pantalla2.Routes

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fondos_pantalla2.adminscreen.AdminScreenModel
import com.example.fondos_pantalla2.adminscreen.AdminscreenM
import com.example.fondos_pantalla2.adminscreen.ui.categorias.MoviesScreen
import com.example.fondos_pantalla2.adminscreen.ui.categorias.MusicScreen
import com.example.fondos_pantalla2.adminscreen.ui.categorias.SeriesScreen
import com.example.fondos_pantalla2.adminscreen.ui.categorias.VideojuegosScreen
import com.example.fondos_pantalla2.adminscreen.ui.categorias.musica.addmusic.RegistrarMusica
import com.example.fondos_pantalla2.adminscreen.ui.categorias.peliculas.RegistrarPelicula
import com.example.fondos_pantalla2.adminscreen.ui.categorias.series.addserie.RegistrarSerie
import com.example.fondos_pantalla2.adminscreen.ui.categorias.videojuegos.addvideoj.RegistrarVideoGame
import com.example.fondos_pantalla2.clientescreen.ui.MainClientScreen
import com.example.fondos_pantalla2.clientescreen.ui.ScreenClientMain
import com.example.fondos_pantalla2.mainscreen.MainScreen
import com.example.fondos_pantalla2.splash.SplashScreen

@ExperimentalMaterial3Api
@Composable
fun AppNavigation() {
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController = navController);
        }

        composable(AppScreen.MainScreen.route) {
            MainScreen(navContrller = navController)
        }

        composable(AppScreen.AdminScreen.route){
            AdminScreenModel(navController = navController);
        }

        composable(AppScreen.ClientScreen.route){
            ScreenClientMain(navController = navController);
        }
        composable(AppScreen.AdminScreenPt2.route){
            AdminscreenM(navController = navController);
        }
        composable(AppScreen.ClientScreenPt2.route){
            MainClientScreen(navController = navController);
        }

        composable(AppScreen.PeliculasScreen.route){
            MoviesScreen(navController = navController);
        }
        composable(AppScreen.SeriesScreen.route){
            SeriesScreen(navController = navController)
        }
        composable(AppScreen.MusicaScreen.route){
            MusicScreen(navController = navController);
        }
        composable(AppScreen.VideojuegosScreen.route){
            VideojuegosScreen(navController = navController);
        }

        composable(AppScreen.AddMoviewScr.route){
            RegistrarPelicula(navController = navController);
        }

        composable(AppScreen.AddSerieScr.route){
            RegistrarSerie(navController = navController);
        }

        composable(AppScreen.AddMusicScr.route){
            RegistrarMusica(navController = navController);
        }

        composable(AppScreen.AddVideoGameScr.route){
            RegistrarVideoGame(navController = navController);
        }
    }
}