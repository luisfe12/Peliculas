package com.example.fondos_pantalla2.adminscreen.adminvmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AdminModel: ViewModel(){

    private val _inicioState = MutableLiveData<Boolean>();
    private val _perfilState = MutableLiveData<Boolean>();
    private val _registrarState = MutableLiveData<Boolean>();
    private val _listarAdmnState = MutableLiveData<Boolean>();
    private val _cerrarScnState = MutableLiveData<Boolean>();

    //valores para comprobar sis e inicio sesion
    private val authFB:FirebaseAuth = FirebaseAuth.getInstance();
    private val user:FirebaseUser? = authFB.currentUser;

    val inicioState: LiveData<Boolean> = _inicioState;
    val perfilState: LiveData<Boolean> = _perfilState;
    val registrarState: LiveData<Boolean> = _registrarState;
    val listarAdmnState: LiveData<Boolean> = _listarAdmnState;
    val cerrarScnState: LiveData<Boolean> = _cerrarScnState;


    fun changeOption(
        inicio: Boolean = false,
        perfil: Boolean = false,
        registrar: Boolean = false,
        listar:Boolean = false,
        cerrar: Boolean = false,
        ) {

        _inicioState.value = inicio;
        _perfilState.value = perfil;
        _registrarState.value = registrar;
        _listarAdmnState.value = listar;
        _cerrarScnState.value = cerrar;

        Log.i("inicio", "${inicioState.value}");
        Log.i("perfil", "${perfilState.value}");
        Log.i("registrar", "${registrarState.value}");
        Log.i("listar", "${listarAdmnState.value}");
        Log.i("cerrar", "${cerrarScnState.value}");
    }

    fun getInicio(): Boolean? = _inicioState.value

    fun userAdminLogin():Boolean {
        Log.i("userAdminLogin", "${user?.email.toString()}")
        return user != null
    };

    fun closeSesion(){
        authFB.signOut();
    }
}