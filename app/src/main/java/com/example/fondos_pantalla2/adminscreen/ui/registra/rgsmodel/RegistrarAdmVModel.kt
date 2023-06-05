package com.example.fondos_pantalla2.adminscreen.ui.registra.rgsmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegistrarAdmVModel : ViewModel() {
    private val _email = MutableLiveData<String>();
    private val _password = MutableLiveData<String>();
    private val _name = MutableLiveData<String>();
    private val _lastName = MutableLiveData<String>();
    private val _age = MutableLiveData<String>();
    private val _visibilityPws = MutableLiveData<Boolean>();
    private val _isigninEnabled = MutableLiveData<Boolean>();

    private val auth: FirebaseAuth = Firebase.auth;
    private val _loadinSing = MutableLiveData<Boolean>(false);

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password;
    val name: LiveData<String> = _name;
    val lastName: LiveData<String> = _lastName;
    val age: LiveData<String> = _age;
    val visibilityPws: LiveData<Boolean> = _visibilityPws;
    val isigninEnabled: LiveData<Boolean> = _isigninEnabled;

    fun onLoginChanged(
        emailN: String,
        passwordN: String,
        nameN: String,
        lastNameN: String,
        ageN: String,
    ) {
        _email.value = emailN;
        _password.value = passwordN;
        _name.value = nameN;
        _lastName.value = lastNameN;
        _age.value = ageN;

        _isigninEnabled.value = enableSignin(
            emailN, passwordN, nameN, lastNameN, ageN
        );
    }


    fun changeVisibility(visibible: Boolean) {
        8
        _visibilityPws.value = visibible;
    }

    private fun enableSignin(
        email: String,
        password: String,
        name: String,
        lastNameN: String,
        age: String,
    ): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length >= 6 && name.isNotEmpty() && lastNameN.isNotEmpty()
                && age.isNotEmpty();
    }

    fun createUserEmail(
        email: String,
        password: String,
        name: String,
        lastName: String,
        age: String,
        returnInicio: () -> Unit
    ) {

        if (_loadinSing.value == false) {
            _loadinSing.value = true;
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("succeful", "POner las funcion que redirenciona");
                    createUser(
                        email = email,
                        password = password,
                        name = name,
                        lastName = lastName,
                        age = age,
                    )
                    returnInicio();
                } else {
                    Log.i("failed", "no se pudo, $email $password");
                }

                _loadinSing.value = false;
            }
        }

    }

    private fun createUser(
        email: String,
        password: String,
        name: String,
        lastName: String,
        age: String,
    ) {
        val userID = auth.currentUser!!.uid;

        val user = User(
            uid = userID.toString(),
            email = email,
            password = password,
            name = name,
            lastName = lastName,
            age = age,
        )

        FirebaseDatabase.getInstance()
            .getReference("BASE DE DATOS ADMINISTRADORES")
            .child(userID).setValue(user);
    }

}