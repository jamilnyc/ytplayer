package org.jamilnyc.ytplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*

class StandaloneActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        // Synthetic imported view elements
        btnPlayVideo.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)

        // Implement with a new class
//        btnPlayVideo.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
                // Implement code here
//            }
//        })

        // Implement with a lambda expression
//        btnPlayVideo.setOnClickListener(View.OnClickListener { v ->
            // Implement code here
//        })

        // Create an object (anonymous inner class) and assign it multiple times
//        val listener = View.OnClickListener { v ->
            // Implement code here
//        }
//        btnPlayVideo.setOnClickListener(listener)
//        btnPlaylist.setOnClickListener(listener)
    }

    override fun onClick(view: View) {
        val intent = when (view.id) {
            // Passing in the activity initiating the intent, API Key and Video to Play
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID
            )

            R.id.btnPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_PLAYLIST
            )

            else -> throw IllegalArgumentException("Undefined button clicked")
        }

        startActivity(intent)
    }
}