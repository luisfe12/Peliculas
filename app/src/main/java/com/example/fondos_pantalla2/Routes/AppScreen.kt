package com.example.fondos_pantalla2.Routes

sealed class AppScreen(val route:String){

    object SplashScreen : AppScreen("splash_screen");
    object MainScreen : AppScreen("main_screen");
    object AdminScreen : AppScreen("admin_screen");
    object AdminScreenPt2:AppScreen("admin_screen_Pt2")
    object ClientScreen : AppScreen("client_screen");
    object ClientScreenPt2:AppScreen("client_screen_Pt2")
    object PeliculasScreen : AppScreen("peliculas_screen")
    object SeriesScreen : AppScreen("series_screen")
    object MusicaScreen : AppScreen("musica_screen")
    object VideojuegosScreen : AppScreen("videojuegos_screen")

    object AddMoviewScr:AppScreen("add_moview_scr");
    object AddSerieScr:AppScreen("add_serie_scr");
    object AddMusicScr:AppScreen("add_music_scr");
    object AddVideoGameScr:AppScreen("add_videogame_scr");
}
