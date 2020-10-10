package com.shajt3ch.robi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shajt3ch.robi.model.*
import com.shajt3ch.robi.view.adapter.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateData()
    }


    private fun populateData() {

        val chapter1 = ArrayList<Item>()
        chapter1.add(Video(R.drawable.ic_video, "Video 1_1"))
        chapter1.add(Document(R.drawable.ic_doc, "Document 1_1", "Shakil"))
        chapter1.add(Video(R.drawable.ic_video, "Video 1_2"))

        val chapter2 = ArrayList<Item>()
        chapter2.add(Document(R.drawable.ic_doc, "Document 2_1", "Shaj"))
        chapter2.add(Video(R.drawable.ic_video, "Video 2_1"))
        chapter2.add(Video(R.drawable.ic_video, "Video 2_2"))
        chapter2.add(Audio(R.drawable.ic_audio, "Audio 2_1"))

        val video3 = Video(R.drawable.ic_video, "Video 3_0")
        val document4 = Document(R.drawable.ic_doc, "Document 4_0", "Shakil")

        val items = ArrayList<Item>()
        items.add(Chapter("Chapter 1", false, chapter1))
        items.add(Chapter("Chapter 2", false, chapter2))
        items.add(video3)
        items.add(document4)

        val adapter = BaseAdapter(items)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


    }


}