package com.example.musicapp.data.repository

import com.example.musicapp.R
import com.example.musicapp.data.model.Music

class MusicRepository(){

    fun getMusicList(): List<Music>{
        return listOf(
            Music("The Hills", "The Weeknd", R.raw.the_hills, R.drawable.the_hills),
            Music("Algo mágico","Rauw Alejandro", R.raw.algo_magico, R.drawable.algo_magico),
            Music("Save your tears","The Weeknd", R.raw.save_your_tears,R.drawable.save_your_tears)
        )
    }
}