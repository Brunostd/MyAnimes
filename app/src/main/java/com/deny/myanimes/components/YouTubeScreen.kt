package com.deny.myanimes.components

import android.view.View
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubeScreen(
    videoId: String
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var youtubePlayerView: YouTubePlayerView? by remember { mutableStateOf(null) }
    var player: YouTubePlayer? by remember { mutableStateOf(null) }
    var fullscreenViewContainer: FrameLayout? by remember { mutableStateOf(null) }
    AndroidView(
        factory = { context ->

            val view = YouTubePlayerView(context).apply {
                val self = this
                enableAutomaticInitialization = false
                addFullscreenListener(object : FullscreenListener {
                    override fun onEnterFullscreen(
                        fullscreenView: View,
                        exitFullscreen: () -> Unit
                    ) {
                        self.visibility = View.GONE
                        fullscreenViewContainer?.let {
                            it.visibility = View.VISIBLE
                            it.addView(fullscreenView)
                        }
                    }

                    override fun onExitFullscreen() {
                        self.visibility = View.VISIBLE
                        fullscreenViewContainer?.let {
                            visibility = View.GONE
                            removeAllViews()
                        }
                    }
                })

                initialize(
                    youTubePlayerListener = object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            player = youTubePlayer
                            super.onReady(youTubePlayer)
                            youTubePlayer.cueVideo(videoId, 0f)
                        }
                    },
                    playerOptions = IFramePlayerOptions
                        .Builder()
                        .controls(1)
                        .autoplay(0)
                        .fullscreen(1)
                        .build()
                )
                lifecycleOwner.lifecycle.addObserver(this)
            }
            youtubePlayerView = view
            view
        },
        update = {},
    )
    DisposableEffect(Unit) {
        onDispose {
            player?.pause()
            youtubePlayerView?.let {
                it.release()
                lifecycleOwner.lifecycle.removeObserver(it)
            }
        }
    }
}