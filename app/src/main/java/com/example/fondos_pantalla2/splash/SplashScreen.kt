package com.example.fondos_pantalla2.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.R
import com.example.fondos_pantalla2.Routes.AppScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val authFB: FirebaseAuth = FirebaseAuth.getInstance();
    val user: FirebaseUser? = authFB.currentUser;

    LaunchedEffect(key1 = true){
        delay(2000)
        navController.popBackStack();//evita volver a esta pantalla
        navController.navigate(AppScreen.AdminScreen.route);
//        when (user != null){
//            true -> navController.navigate(AppScreen.AdminScreen.route);
//            false -> navController.navigate(AppScreen.ClientScreen.route);
//        }
    }

    Splash();
}


@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF1E3C53)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.jw4portada),
            contentDescription = "PortadaSS", modifier = Modifier.size(350.dp)
        )
        TextShowSS(text = "PelicalsGod")
        TextShowSS(text = "Luisfe")
    }
}

@Composable
fun TextShowSS(text: String) {
    Text(text = text, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
}


@Preview(showBackground = true)
@Composable
fun ShowSplash() {
    Splash();
}