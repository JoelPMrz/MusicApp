package com.example.musicapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.data.model.Music
import com.example.musicapp.data.repository.MusicRepository

class MusicViewModel : ViewModel() {

    private val musicRepository = MusicRepository()

    private val _musicList = MutableLiveData<List<Music>>()
    val musicList: LiveData<List<Music>> = _musicList

    init {
        loadMusicList()
    }

    private fun loadMusicList() {
        _musicList.value = musicRepository.getMusicList()
    }
}
