package com.example.fondos_pantalla2.adminscreen.adminvmodel.musica

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fondos_pantalla2.adminscreen.data.registrarP.Musica
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MusicaViewModel:ViewModel() {

    private val _dbMusicRfs = FirebaseDatabase.getInstance().getReference("MUSICA");
    private val _musicas = MutableStateFlow<List<Musica>>(emptyList());
    private val _isLoadMscImg = MutableLiveData<Boolean>();

    val musicas:StateFlow<List<Musica>> = _musicas;
    val isLoadMscImg:LiveData<Boolean> = _isLoadMscImg;

    fun loadForMusic(){
        val valueEventListener = object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val musicList = mutableListOf<Musica>();

                for (musicSnapshot in snapshot.children){
                    val image = musicSnapshot.child("image").getValue(String::class.java);
                    val name = musicSnapshot.child("name").getValue(String::class.java);
                    val views = musicSnapshot.child("views").getValue(Int::class.java);

                    if (image != null && name != null && views != null){
                        val musica = Musica(image, name, views);
                        musicList.add(musica);
                    }
                }

                _musicas.value = musicList;
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        _dbMusicRfs.addValueEventListener(valueEventListener);
        _isLoadMscImg.value = true;
    }

}