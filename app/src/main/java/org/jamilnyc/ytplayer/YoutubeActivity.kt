package org.jamilnyc.ytplayer

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "2ODNIsfIba0"
const val YOUTUBE_PLAYLIST = "PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "YoutubeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate() returns a View, that is cast into a ConstraintLayout
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        // Passing in this activity as an object of type YouTubePlayer.OnInitializedListener
        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: player is  is ${player?.javaClass}")
        Toast.makeText(this, "Initialized YouTube Player successfully", Toast.LENGTH_SHORT).show()

        if (!wasRestored) {
            player?.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        provier: YouTubePlayer.Provider?,
        youtubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if(youtubeInitializationResult?.isUserRecoverableError == true) {
            youtubeInitializationResult?.getErrorDialog(this, REQUEST_CODE)?.show()
        } else {
            val errorMessage = "There was an error initializing the YouTube Player ($youtubeInitializationResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()

        }
    }
}
