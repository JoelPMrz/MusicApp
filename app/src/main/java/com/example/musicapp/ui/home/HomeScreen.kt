package com.example.musicapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import com.example.musicapp.data.model.Music
import com.example.musicapp.ui.home.components.AudioPlayer
import com.example.musicapp.ui.viewmodel.AudioPlayerViewModel
import com.example.musicapp.ui.viewmodel.MusicViewModel

@androidx.annotation.OptIn(UnstableApi::class)
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(musicViewModel: MusicViewModel = viewModel(), audioPlayerViewModel: AudioPlayerViewModel = viewModel()) {
    val context = LocalContext.current
    val musicList = musicViewModel.musicList.observeAsState(initial = emptyList()).value
    val pagerState = rememberPagerState(pageCount = { musicList.size })

    val currentMusic = remember { mutableStateOf<Music?>(null) }

    LaunchedEffect(pagerState.currentPage) {

        currentMusic.value = musicList.getOrNull(pagerState.currentPage)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5072F8),
                        Color(0xFF5C278C)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                pageSize = PageSize.Fixed(280.dp),
                contentPadding = PaddingValues(horizontal = 85.dp)
            ) { pageIndex ->
                val music = musicList.getOrNull(pageIndex)
                music?.let {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(bottom = 24.dp)
                    ) {
                        MusicItem(music = it)
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))


            currentMusic.value?.let {

                audioPlayerViewModel.initPlayer(context, it.sound)
                AudioPlayer(song = it.sound)
            }
        }
    }
}

@Composable
fun MusicItem(music: Music) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.15f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = music.title,
                fontSize = 42.sp,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
            )
        }

        Card(
            modifier = Modifier
                .size(250.dp)
                .border(2.dp, Color.White, CircleShape)
                .padding(6.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = music.cover),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = music.artist,
            fontSize = 22.sp,
            color = Color(0xFFDCDCDC),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )
    }
}




