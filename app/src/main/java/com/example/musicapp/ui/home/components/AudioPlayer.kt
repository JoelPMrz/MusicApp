package com.example.musicapp.ui.home.components

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import com.example.musicapp.R
import com.example.musicapp.ui.viewmodel.AudioPlayerViewModel

@OptIn(UnstableApi::class)
@Composable
fun AudioPlayer(song: Int) {
    val audioPlayerViewModel: AudioPlayerViewModel = viewModel()

    audioPlayerViewModel.initPlayer(context = LocalContext.current, sound = song)
    TimeBar()
    Spacer(modifier = Modifier.height(24.dp))
    Controls(audioPlayerViewModel = audioPlayerViewModel)
}

@Composable
fun TimeBar() {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "00:00",
            modifier = Modifier.width(55.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(10.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = .5f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xff414141))
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "03:45",
            modifier = Modifier.width(55.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(UnstableApi::class)
@Composable
fun Controls(
    audioPlayerViewModel: AudioPlayerViewModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ControlItem(icon = R.drawable.ic_rewind, size = 60.dp, onClick = {

        })

        ControlItem(
            icon = if (audioPlayerViewModel.isPlaying.value) R.drawable.ic_pause else R.drawable.ic_play,
            size = 80.dp,
            onClick = {
                audioPlayerViewModel.playPauseSong()
            }
        )

        ControlItem(icon = R.drawable.ic_forward, size = 60.dp, onClick = {
        })
    }
}

@Composable
fun ControlItem(icon: Int, size: Dp, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(size / 2.2f),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xff414141)
        )
    }
}
