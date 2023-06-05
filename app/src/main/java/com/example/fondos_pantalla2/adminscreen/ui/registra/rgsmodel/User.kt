package com.example.fondos_pantalla2.adminscreen.ui.registra.rgsmodel

data class User(
    val uid:String,
    val email:String,
    val password:String,
    val name:String,
    val lastName:String,
    val age:String,
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf<String, Any>(
            "UID" to this.uid,
            "CORREO" to this.email,
            "PASSWORD" to this.password,
            "NAME" to this.name,
            "LAST_NAME" to this.lastName,
            "AGE" to this.age,
        )
    }
}
