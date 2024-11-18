package com.example.musicapp.ui.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.compose.runtime.State
import androidx.media3.common.util.UnstableApi

@UnstableApi
class AudioPlayerViewModel : ViewModel() {

    private var player: SimpleExoPlayer? = null
    private var _isPlaying = mutableStateOf(false)
    private var _currentSongId = mutableStateOf(-1)

    val isPlaying: State<Boolean> = _isPlaying

    fun initPlayer(context: Context, sound: Int) {
        if (_currentSongId.value != sound) {
            player?.stop()
            player?.release()
            _isPlaying.value = false
        }

        if (player == null || _currentSongId.value != sound) {
            val mediaItem = MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/$sound"))
            player = SimpleExoPlayer.Builder(context).build()
            player?.setMediaItem(mediaItem)
            player?.prepare()
            _currentSongId.value = sound
        }
    }

    fun playPauseSong() {
        if (_isPlaying.value) {
            player?.pause()
            _isPlaying.value = false
        } else {
            player?.play()
            _isPlaying.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        player?.release()
    }
}

