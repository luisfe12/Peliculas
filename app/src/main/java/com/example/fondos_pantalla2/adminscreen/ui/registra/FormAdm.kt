package com.example.fondos_pantalla2.adminscreen.ui.registra

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fondos_pantalla2.adminscreen.adminModelView
import com.example.fondos_pantalla2.adminscreen.ui.registra.rgsmodel.RegistrarAdmVModel


@ExperimentalMaterial3Api
@Composable
fun FormAdmin() {
    val registrarAdmVModel: RegistrarAdmVModel = RegistrarAdmVModel();

    val email by registrarAdmVModel.email.observeAsState("");
    val password by registrarAdmVModel.password.observeAsState("");
    val name by registrarAdmVModel.name.observeAsState("");
    val lastName by registrarAdmVModel.lastName.observeAsState("");
    val age by registrarAdmVModel.age.observeAsState("");
    val signinEanbled by registrarAdmVModel.isigninEnabled.observeAsState(false);

    Email(email) {
        registrarAdmVModel.onLoginChanged(
            emailN = it,
            passwordN = password, nameN = name, lastNameN = lastName, ageN = age
        )
    };
    SpaceForm(10);
    Password(password, registrarAdmVModel) {
        registrarAdmVModel.onLoginChanged(
            emailN = email,
            passwordN = it, nameN = name, lastNameN = lastName, ageN = age
        )
    };
    SpaceForm(10);
    NameUser(name) {
        registrarAdmVModel.onLoginChanged(
            emailN = email,
            passwordN = password, nameN = it, lastNameN = lastName, ageN = age
        )
    };
    SpaceForm(10);
    LastName(lastName) {
        registrarAdmVModel.onLoginChanged(
            emailN = email,
            passwordN = password, nameN = name, lastNameN = it, ageN = age
        )
    };
    SpaceForm(10);
    Age(age) {
        registrarAdmVModel.onLoginChanged(
            emailN = email,
            passwordN = password, nameN = name, lastNameN = lastName, ageN = it
        )
    }
    SpaceForm(size = 20);
    ButtomSigIn(signinEanbled, registrarAdmVModel, email, password, name, lastName, age);
}


@Composable
fun ButtomSigIn(
    signInEnabled: Boolean,
    registrarAdmVModel: RegistrarAdmVModel,
    email: String,
    password: String,
    name: String,
    lastName: String,
    age: String,
) {
    Button(
        onClick = {
            registrarAdmVModel.createUserEmail(
                email = email,
                password = password,
                name = name,
                lastName = lastName,
                age = age
            ) {
                adminModelView.changeOption(inicio = true);
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF044994)
        ),
        enabled = signInEnabled,
    ) {
        Text(
            text = "Registrar",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun Age(age: String, onAgeChange: (String) -> Unit) {
    OutlinedTextField(
        value = age,
        onValueChange = { onAgeChange(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text(text = "Age") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFE4DEDE),
            focusedBorderColor = Color(0xFF667460),
            ),
    )
}

@ExperimentalMaterial3Api
@Composable
fun LastName(lastName: String, onLastNameChanged: (String) -> Unit) {
    OutlinedTextField(
        value = lastName,
        onValueChange = { onLastNameChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text(text = "Apellido") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFE4DEDE),
            focusedBorderColor = Color(0xFF667460),
        ),
    )
}

@ExperimentalMaterial3Api
@Composable
fun NameUser(name: String, onNameChanged: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = { onNameChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text(text = "Nombre") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFE4DEDE),
            focusedBorderColor = Color(0xFF667460),
        ),
    )
}

@ExperimentalMaterial3Api
@Composable
fun Password(
    password: String,
    registrarAdmVModel: RegistrarAdmVModel,
    onPasswordChanged: (String) -> Unit
) {
    val passwordVisibility by registrarAdmVModel.visibilityPws.observeAsState(false);

    OutlinedTextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text(text = "Contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFE4DEDE),
            focusedBorderColor = Color(0xFF667460),
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.VisibilityOff;
            } else {
                Icons.Filled.Visibility;
            }

            IconButton(onClick = { registrarAdmVModel.changeVisibility(!passwordVisibility) }) {
                Icon(imageVector = image, contentDescription = "ixonovisibilidad")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation();
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun Email(email: String, onEmailChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onEmailChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFE4DEDE),
            focusedIndicatorColor = Color(0xFF667460),
        )
    )
}

@Composable
fun SpaceForm(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

 
