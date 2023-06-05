package com.example.fondos_pantalla2.clientescreen.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.fondos_pantalla2.clientescreen.clientvmodel.ClientModel
import com.example.fondos_pantalla2.clientescreen.ui.acercade.AcercaDeScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

val clientViewModel: ClientModel = ClientModel();

@ExperimentalMaterial3Api
@Composable
fun ScreenClientMain(navController: NavHostController) {
    val context = LocalContext.current

    val authFB: FirebaseAuth = FirebaseAuth.getInstance();
    val user: FirebaseUser? = authFB.currentUser;


    MainClientScreen(navController = navController)
    Log.i("VALLLL", "$user");
    
//    }else {
//        navController.navigate(AppScreen.AdminScreen.route)
//    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MainClientScreen(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val corutineScope = rememberCoroutineScope();
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed);

    //TODO valores par ael model
    val inicioScreen by clientViewModel.inicioState.observeAsState(true);
    val acerceScreen by clientViewModel.acercaState.observeAsState(false);
    val compositeScreen by clientViewModel.compartirState.observeAsState(false);

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ClientDrawer {
                corutineScope.launch {
                    drawerState.close();
                }
            }
        },
        gesturesEnabled = true,
        scrimColor = Color(0xB9FFFFFF),
        content = {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    MyTopBarCllient {
                        corutineScope.launch { drawerState.open() }
                    }
                },
                bottomBar = {},
                floatingActionButton = {}) { innerpadding ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerpadding)
                ) {
                    if (inicioScreen) {
                        Text(text = "Inicio cliente");
                    } else if (acerceScreen) {
                        AcercaDeScreen(navController);
                    } else if (compositeScreen) {
                        Text(text = "Compartir");
                    }
                }
            }
        }
    )
}


//@ExperimentalMaterial3Api
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ShowMainClienScreen() {
//    MainClientScreen();
//}