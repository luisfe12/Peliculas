package com.example.fondos_pantalla2.clientescreen.clientvmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ClientModel:ViewModel() {

    private val _inicioState = MutableLiveData<Boolean>();
    private val _acercaState = MutableLiveData<Boolean>();
    private val _compartirState = MutableLiveData<Boolean>();

    private val auth: FirebaseAuth = FirebaseAuth.getInstance();
    private val userC: FirebaseUser? = auth.currentUser;

    val inicioState:LiveData<Boolean> = _inicioState;
    val acercaState:LiveData<Boolean> = _acercaState;
    val compartirState:LiveData<Boolean> = _compartirState;

    private fun changeStateClient(
        inicio:Boolean = false,
        acerca:Boolean = false,
        compartir:Boolean = false,
    ){
        _inicioState.value = inicio;
        _acercaState.value = acerca;
        _compartirState.value = compartir;
    }


    fun currentOption(option:String){
        when (option) {
            "Inicio" -> changeStateClient(inicio = true);
            "Acerca de" -> changeStateClient(acerca = true);
            "Compartir" -> changeStateClient(compartir = true);
        }
    }

//    fun loginWithEmailAndPassword(email:String, password:String){
//        viewModelScope.launch {
//            try {
//                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task->
//                    if (task.isSuccessful){
//                        Log.i("exitos", "rEDIRECIONO a Admin")
//                    }else{
//                        Log.i("fallo", "Fallo al conectar ese user")
//                    }
//                }
//            }
//            catch (ex:Exception){
//                Log.d("error", "Error que ocurrio");
//            }
//        }
//    }

    fun currentUserLogin():Boolean = userC != null;
}