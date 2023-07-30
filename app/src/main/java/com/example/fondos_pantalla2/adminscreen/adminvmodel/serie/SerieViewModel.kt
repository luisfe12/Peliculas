package com.example.fondos_pantalla2.adminscreen.adminvmodel.serie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Serie
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SerieViewModel:ViewModel() {

    private val _dbSerieRfs = FirebaseDatabase.getInstance().getReference("SERIE");
    private val _series = MutableStateFlow<List<Serie>>(emptyList());
    private val _isLoasSDb = MutableLiveData<Boolean>();

    val series:StateFlow<List<Serie>> = _series;
    val isLoadSDB: LiveData<Boolean> = _isLoasSDb;

    fun liastenForSeries(){
        viewModelScope.launch {
            val valueEventListener = object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val serieList = mutableListOf<Serie>();
                    for (serieSnapShot in snapshot.children){
                        val image = serieSnapShot.child("image").getValue(String::class.java);
                        val name = serieSnapShot.child("name").getValue(String::class.java);
                        val views = serieSnapShot.child("views").getValue(Int::class.java);

                        if (image != null && name != null && views != null){
                            val serie = Serie(image, name, views);
                            serieList.add(serie);
                        }
                    }

                    _series.value = serieList;
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("SERIE ERROR", "${error.message}")
                }

            }

            _dbSerieRfs.addValueEventListener(valueEventListener);
            _isLoasSDb.value = true;
        }
    }
}