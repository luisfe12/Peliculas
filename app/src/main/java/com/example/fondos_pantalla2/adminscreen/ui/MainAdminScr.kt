package com.example.fondos_pantalla2.adminscreen


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
import com.example.fondos_pantalla2.Routes.AppScreen
import com.example.fondos_pantalla2.adminscreen.adminvmodel.AdminModel
import com.example.fondos_pantalla2.adminscreen.ui.AdminDrawer
import com.example.fondos_pantalla2.adminscreen.ui.MyTopBarAdmin
import com.example.fondos_pantalla2.adminscreen.ui.inicio.InicioOptionScreen
import com.example.fondos_pantalla2.adminscreen.ui.listar.ListarOptionScreen
import com.example.fondos_pantalla2.adminscreen.ui.perfil.PerfilOptionScreen
import com.example.fondos_pantalla2.adminscreen.ui.registra.RegistrarOptionScreen
import com.example.fondos_pantalla2.clientescreen.ui.MainClientScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

val adminModelView: AdminModel = AdminModel();

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterial3Api
@Composable
fun AdminScreenModel(navController: NavHostController) {

    val context = LocalContext.current;

    val authFB: FirebaseAuth = FirebaseAuth.getInstance();
    val user: FirebaseUser? = authFB.currentUser;
    val scope = rememberCoroutineScope()

    when (user == null) {
        true -> MainClientScreen(navController = navController)
        false -> AdminscreenM(navController = navController)
//         true ->LaunchedEffect(user == null) {
//            navController.navigate(AppScreen.ClientScreenPt2.route)
//        }
//                //Log.i("VALOR USER", "$user")
//        false ->        LaunchedEffect (user != null) {//usar esto cuando se trate de condicionales y ascreen
//            scope.launch {
//                navController.navigate(AppScreen.AdminScreenPt2.route);
//                Toast.makeText(
//                    context,
//                    "Que paso ${adminModelView.userAdminLogin().toString()}",
//                    Toast.LENGTH_LONG
//                ).show();
//                Log.i("entro", "HOLAAAAAAAAAAAAAAAAAA")
//            }
//        }
    }


//    when (adminModelView.userAdminLogin()){
//        true -> navController.navigate(AppScreen.AdminScreenPt2.route);
//        false -> navController.navigate(AppScreen.ClientScreen.route);
//    }


//    Toast.makeText(context, "inicio sesion ", Toast.LENGTH_LONG).show();
//    AdminscreenM(navController);
//    navController.navigate(AppScreen.AdminScreenPt2.route)


//    if (adminModelView.cerrarScnState.value == true){
//        adminModelView.closeSesion();
//        Toast.makeText(context, "Sesion cerrar", Toast.LENGTH_SHORT).show();
//        navController.navigate(AppScreen.ClientScreen.route);
//    }

}


@ExperimentalMaterial3Api
@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter",
    "CoroutineCreationDuringComposition"
)
@Composable
fun AdminscreenM(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }//no poner saveable
    val corutineScope = rememberCoroutineScope();
    val drawerState = rememberDrawerState(DrawerValue.Closed);

    //TODO valores de cada opcion
    val inicioState by adminModelView.inicioState.observeAsState(true);
    val perfilState by adminModelView.perfilState.observeAsState(false);
    val registrarState by adminModelView.registrarState.observeAsState(false);
    val listar by adminModelView.listarAdmnState.observeAsState(false);
    val cerrar by adminModelView.cerrarScnState.observeAsState(false);

    val authFB: FirebaseAuth = FirebaseAuth.getInstance();
    val user: FirebaseUser? = authFB.currentUser;


    Log.i("Entro", "que pasoooo")

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        AdminDrawer {
            corutineScope.launch {
                drawerState.close();
            }
        }
    }, gesturesEnabled = true,
        scrimColor = Color(0xB9FFFFFF),
        content = {

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    MyTopBarAdmin {
                        corutineScope.launch { drawerState.open() }
                    }

                },
                bottomBar = {},
                floatingActionButton = {},
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                ) {
                    if (inicioState) {
                        InicioOptionScreen(Modifier, navController);
                    } else if (perfilState) {
                        PerfilOptionScreen(Modifier);
                    } else if (registrarState) {
                        RegistrarOptionScreen(Modifier);
                    } else if (listar) {
                        ListarOptionScreen(Modifier)
                    } else if (cerrar) {

                        Log.i("CERRAR", "PORQUE ENTRO AQUI")
                        authFB.signOut();


                        adminModelView.changeOption(inicio = true);
                        navController.navigate(AppScreen.ClientScreen.route)

                        //CerrarOptionScreen(Modifier)
                    }
                }
            }

        })
}


//fun closeAdminSesion(navController: NavHostController){
//
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ShowMneuAdmin() {
//
//    AdminscreenM();
//}
