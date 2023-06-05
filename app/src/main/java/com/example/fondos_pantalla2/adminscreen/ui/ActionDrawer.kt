package com.example.fondos_pantalla2.adminscreen.ui

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fondos_pantalla2.adminscreen.adminvmodel.AdminModel
import com.example.fondos_pantalla2.adminscreen.ui.cerrar.CerrarOptionScreen
import com.example.fondos_pantalla2.adminscreen.ui.inicio.InicioOptionScreen
import com.example.fondos_pantalla2.adminscreen.ui.listar.ListarOptionScreen
import com.example.fondos_pantalla2.adminscreen.ui.perfil.PerfilOptionScreen
import com.example.fondos_pantalla2.adminscreen.ui.registra.RegistrarOptionScreen

//@ExperimentalMaterial3Api
//@Composable
//fun DrawerMenuItemSelected(modifier:Modifier, adminModelView:AdminModel){
//    Log.i("Pantalla Opt", "Entro al scrren genral de admin");
//    Log.i("INICIO_OPT", "${adminModelView.getInicio()}");
//    Log.i("PERFIL_P[T", "${adminModelView.perfilState.value}")
//    Log.i("REGISTRAR_OPT", "${adminModelView.registrarState.value}");
//    Log.i("LISTAR_OPT", "${adminModelView.listarAdmnState.value}");
//    Log.i("CERRAR_OPT", "${adminModelView.cerrarScnState.value}");
//    if (adminModelView.getInicio() == true){
//        Log.i("inicio", "${adminModelView.getInicio()}");
//        InicioOptionScreen(modifier = modifier)
//    }
//
//    else if (adminModelView.perfilState.value == true){
//        Log.i("perfil", "${adminModelView.perfilState.value}");
//        PerfilOptionScreen(modifier = modifier);
//    }
//
//    else if (adminModelView.registrarState.value == true){
//        Log.i("registrar", "${adminModelView.registrarState.value}");
//        RegistrarOptionScreen(modifier = modifier);
//    }
//
//    else if (adminModelView.listarAdmnState.value == true){
//        Log.i("listar", "${adminModelView.listarAdmnState.value}");
//        ListarOptionScreen(modifier = modifier);
//    }
//
//    else if (adminModelView.cerrarScnState.value == true){
//        Log.i("cerrarSesion", "${adminModelView.cerrarScnState.value}");
//        CerrarOptionScreen(modifier = modifier);
//    }
//}