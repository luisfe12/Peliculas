package com.example.fondos_pantalla2.clientescreen.clientvmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AcercaDeViewModel : ViewModel() {

    private val _email = MutableLiveData<String>();
    private val _password = MutableLiveData<String>();
    private val _loginEnable = MutableLiveData<Boolean>();


    private val auth: FirebaseAuth = Firebase.auth;

    val email: LiveData<String> = _email;
    val password: LiveData<String> = _password;
    val loginEnable: LiveData<Boolean> = _loginEnable;

    fun acercaLoginClient(emailC: String, passwordC: String) {
        _email.value = emailC;
        _password.value = passwordC;
        _loginEnable.value = enableLoginClient(emailC, passwordC);
    }

    private fun enableLoginClient(email: String, password: String): Boolean =
        email.isNotEmpty() && password.isNotEmpty();

    fun loginWithEmailAndPassword(email: String,
                                  password: String,
                                  onchangeScreen: () -> Unit) {

            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onchangeScreen();
                        Log.i("exitos", "rEDIRECIONO a Admin")
                    } else {
                        Log.i("fallo", "Fallo al conectar ese user")
                    }
                }
            } catch (ex: Exception) {
                Log.d("error", "Error que ocurrio");
            }

    }
}