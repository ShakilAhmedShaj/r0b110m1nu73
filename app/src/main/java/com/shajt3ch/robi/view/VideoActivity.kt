package com.shajt3ch.robi.view

import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.shajt3ch.robi.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        this.title = "Video"

        val bundle: Bundle? = intent.extras
        val videoTitle = bundle?.getString("videoTitle")
        tv_video.text = videoTitle

        loadVideo()

    }

    private fun loadVideo() {
        vv_video.setVideoPath("android.resource://" + packageName + "/" + R.raw.robi_app_promo)
        val controller = MediaController(this)
        controller.setAnchorView(vv_video)
        vv_video.setMediaController(controller)
        vv_video.start()
    }
}