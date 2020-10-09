package com.shajt3ch.robi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shajt3ch.robi.model.*
import com.shajt3ch.robi.view.adapter.BaseAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var baseAdapter: BaseAdapter
    private lateinit var rows: MutableList<RowModel>
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        rows = mutableListOf()
        baseAdapter = BaseAdapter(this, rows)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        recyclerView.adapter = baseAdapter

        populateData()
    }


    private fun populateData() {

        val videoList1: MutableList<Video> = mutableListOf()
        videoList1.add(Video("Video 1_1"))
        videoList1.add(Video("Video 1_2"))

        val audioList1: MutableList<Audio> = mutableListOf()
        audioList1.add(Audio("Audio 1_1"))

        val documentList1: MutableList<Document> = mutableListOf()
        documentList1.add(Document("Document 1_1"))


        val videoList2: MutableList<Video> = mutableListOf()
        videoList2.add(Video("Vid 2_1"))
        videoList2.add(Video("Vid 2_2"))

        val audioList2: MutableList<Audio> = mutableListOf()
        audioList2.add(Audio("Audio 2_1"))

        val documentList2: MutableList<Document> = mutableListOf()
        documentList2.add(Document("Document 2_1"))


        rows.add(RowModel(RowModel.CHAPTER, Chapter("Chapter 1", videoList1, null, documentList1)))
        rows.add(RowModel(RowModel.CHAPTER, Chapter("Chapter 2", videoList2, audioList2, documentList2)))
        rows.add(RowModel(RowModel.VIDEO, Video("Video 3_0")))
        rows.add(RowModel(RowModel.DOCUMENT, Document("Document 4_0")))

        baseAdapter.notifyDataSetChanged()
    }


}