package org.jamilnyc.ytplayer

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
                this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID, 0, true, true
            )

            R.id.btnPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_PLAYLIST, 0, 0, true, false
            )

            else -> throw IllegalArgumentException("Undefined button clicked")
        }

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Please enable the YouTube App in Settings to continue.", Toast.LENGTH_SHORT).show()
        }
    }
}